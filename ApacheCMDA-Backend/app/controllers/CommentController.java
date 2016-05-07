package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import util.Common;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by baishi on 11/24/15.
 */
@Named
@Singleton
public class CommentController extends Controller {

//    private final AbstractController commentRepositoryController;
//    private final AbstractController userRepositoryController;
//    private final AbstractController replyRepositoryController;

    @Inject
    public CommentController(final CommentRepository commentRepository,
                             UserRepository userRepository, ReplyRepository replyRepository){
//        this.commentRepositoryController = new CommentRepositoryController(commentRepository);
//        this.userRepositoryController = new UserRepositoryController(userRepository);
//        this.replyRepositoryController = new ReplyRepositoryController(replyRepository);
        RepositoryFactory.getRepositoryController("comment", commentRepository);
        RepositoryFactory.getRepositoryController("user", userRepository);
        RepositoryFactory.getRepositoryController("reply", replyRepository);
    }

    public Result addReply() {
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode == null){
            System.out.println("Reply not added, expecting Json data");
            return Common.badRequestWrapper("Reply not added, expecting Json data");
        }

        long commentId = jsonNode.path("commentId").asLong();
        long fromUserId = jsonNode.path("fromUserId").asLong();
        long toUserId = jsonNode.path("toUserId").asLong();
        long timestamp = jsonNode.path("timestamp").asLong();
        String content = jsonNode.path("content").asText();
//        Comment comment = (Comment)commentRepositoryController.findOne(commentId);
        Comment comment = (Comment)RepositoryFactory.getRepositoryController("comment", null).findOne(commentId);
        if(comment==null){
            System.out.println("Cannot find comment!");
            return Common.badRequestWrapper("Cannot find comment!");
        }
//        User fromUser = (User)userRepositoryController.findOne(fromUserId);
        User fromUser = (User)RepositoryFactory.getRepositoryController("user", null).findOne(fromUserId);
        if(fromUser==null){
            System.out.println("Cannot find fromUser!");
            return Common.badRequestWrapper("Cannot find fromUser!");
        }
//        User toUser = (User)userRepositoryController.findOne(toUserId);
        User toUser = (User)RepositoryFactory.getRepositoryController("user", null).findOne(fromUserId);
        if(toUser==null){
            System.out.println("Cannot find toUser!");
            return Common.badRequestWrapper("Cannot find toUser!");
        }

//        Reply reply = new Reply(fromUser, toUser, timestamp, content);
        AttachmentFactory af= new AttachmentFactory();
        Reply reply= (Reply)af.getAttachment("reply");
        reply.setFromUser(fromUser);
        reply.setToUser(toUser);
        reply.setTimestamp(timestamp);
        reply.setContent(content);
//        Reply savedReply = (Reply)replyRepositoryController.save(reply);
        Reply savedReply = (Reply)RepositoryFactory.getRepositoryController("reply", null).save(reply);
//        List<Reply> replyList = comment.getReplies(); 
        List<Reply> replyList = new ArrayList();
        Iterator iter = comment.getIterator();
        while (iter.hasNext()) {
            replyList.add((Reply)iter.next());
        }
        replyList.add(reply);
        comment.setReplies(replyList);
//        commentRepositoryController.save((Object)comment);
        RepositoryFactory.getRepositoryController("comment", null).save((Object)comment);

        return ok(new Gson().toJson(savedReply.getId()));
    }

    public Result getReply(Long commentId) {
        try{
            if(commentId==null){
                System.out.println("Expecting comment id");
                return Common.badRequestWrapper("Expecting comment id");
            }
//            ReplyRepository replyRepository = (ReplyRepository)replyRepositoryController.getRepository();
            ReplyRepository replyRepository = (ReplyRepository)RepositoryFactory.getRepositoryController("reply", null)
                                              .getRepository();
            List<Reply> replies = replyRepository.findByCommentId(commentId);

            Collections.sort(replies);

            return ok(new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED).create().toJson(replies));
        } catch (Exception e){
            e.printStackTrace();
            return Common.badRequestWrapper("Fail to fetch replies");
        }
    }

    public Result thumbUp(Long commentId) {
        try{
            if(commentId==null){
                System.out.println("Expecting comment id");
                return Common.badRequestWrapper("Expecting comment id");
            }
//            Comment comment = (Comment)commentRepositoryController.findOne(commentId);
            Comment comment = (Comment)RepositoryFactory.getRepositoryController("comment", null).findOne(commentId);
            comment.setThumb(comment.getThumb() + 1);
//            commentRepositoryController.save((Object)comment);
            RepositoryFactory.getRepositoryController("comment", null).save((Object)comment);
            return ok("{\"success\":\"Success!\"}");
        }catch (Exception e){
            e.printStackTrace();
            return Common.badRequestWrapper("Fail to fetch replies");
        }
    }

    public Result thumbDown(Long commentId) {
        try{
            if(commentId==null){
                System.out.println("Expecting comment id");
                return Common.badRequestWrapper("Expecting comment id");
            }
//            Comment comment = (Comment)commentRepositoryController.findOne(commentId);
            Comment comment = (Comment)RepositoryFactory.getRepositoryController("comment", null).findOne(commentId);
            comment.setThumb(comment.getThumb() - 1);
//            commentRepositoryController.save((Object)comment);
            RepositoryFactory.getRepositoryController("comment", null).save((Object)comment);
            return ok("{\"success\":\"Success!\"}");
        }catch (Exception e){
            e.printStackTrace();
            return Common.badRequestWrapper("Fail to fetch replies");
        }   
    }
}
