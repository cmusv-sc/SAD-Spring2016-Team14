package controllers;

import models.*;
import java.util.HashMap;

public class RepositoryFactory {
    public static final HashMap<String, AbstractController> map = new HashMap<>();
    
    public static AbstractController getRepositoryController (String type, Object injectObject) {
        AbstractController controller = map.get(type);
        if (controller == null) {
            controller = createNewObject(type, injectObject);
            map.put(type, controller);
        }
        return controller;
    }
    
    private static AbstractController createNewObject(String type, Object injectObject) {
        switch(type) {
            case "comment": return new CommentRepositoryController((CommentRepository)injectObject);
            case "reply": return new ReplyRepositoryController((ReplyRepository)injectObject);
            case "user": return new UserRepositoryController((UserRepository)injectObject);
            default: return null;
        }
    }
}