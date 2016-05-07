package controllers;

import models.*;

public class UserRepositoryController extends AbstractController {
    private final UserRepository userRepository;
    
    public UserRepositoryController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Object findOne (long id) {
        return (Object)this.userRepository.findOne(id);
    }
    
    public Object save (Object user) {
        return (Object)userRepository.save((User)user);
    }
    
    public Object getRepository() {
        return this.userRepository;
    }
}