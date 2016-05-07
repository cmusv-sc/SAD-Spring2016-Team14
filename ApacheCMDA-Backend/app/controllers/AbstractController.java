package controllers;

import models.*;

public abstract class AbstractController {
    
    public abstract Object findOne(long id);
    
    public abstract Object save (Object object);
    
    public abstract Object getRepository();
}