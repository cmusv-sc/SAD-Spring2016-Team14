package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.SearchResult;
import models.Workflow;
import play.api.mvc.*;
import play.mvc.Result;
import util.APICall;
import util.Constants;
import views.html.*;
import play.mvc.Controller;
import java.util.ArrayList;
import java.util.List;
import models.User;


public class UserEmailGmailCriteria implements Criteria{
    @Override
    public ArrayList<User> meetCriteria(ArrayList<User> userList){
        ArrayList<User> userArr = new ArrayList<User>();
        for (User u1:userList){
            if (u1.getEmail().contains("@gmail.com")) {
                System.out.println(u1.getEmail());
                userArr.add(u1);
            }
            else
                System.out.println("not gmail ".concat(u1.getEmail()));
        }
        return userArr;
    }
}