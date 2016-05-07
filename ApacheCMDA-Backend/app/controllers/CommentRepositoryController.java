package controllers;

import models.*;

public class CommentRepositoryController extends AbstractController {
    private final CommentRepository commentRepository;
    
    public CommentRepositoryController (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public Object findOne (long id) {
        return (Object)this.commentRepository.findOne(id);
    }
    
    public Object save (Object comment) {
        return (Object)commentRepository.save((Comment)comment);
    }
    
    public Object getRepository() {
        return (Object)this.commentRepository;
    }
}