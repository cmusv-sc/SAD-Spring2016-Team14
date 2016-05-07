package controllers;

import models.*;

public class ReplyRepositoryController extends AbstractController {
    private final ReplyRepository replyRepository;
    
    public ReplyRepositoryController (ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }
    
    public Object findOne (long id) {
        return (Object)this.replyRepository.findOne(id);
    }
    
    public Object save (Object reply) {
        return (Object)replyRepository.save((Reply)reply);
    }
    
    public Object getRepository() {
        return (Object)this.replyRepository;
    }
}