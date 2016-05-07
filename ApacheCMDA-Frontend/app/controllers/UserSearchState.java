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

public class UserSearchState implements SearchState<User>{

    @Override
    public ArrayList<User> doSearch(JsonNode response){
        System.out.println("Searching users");
        ArrayList<User> userArr = new ArrayList<User>();

        for (JsonNode n: response) {
            User obj = new User();
            obj.setUserName(n.get("userName").textValue());
            try {
                obj.setEmail(n.get("email").textValue());
            } catch (Exception e){
                obj.setEmail("");
            }
            obj.setUserName(n.get("userName").textValue()); obj.setId(Long.parseLong(n.get("id").toString()));
            obj.setAvatar(n.get("avatar").textValue());

            userArr.add(obj);

            //USE CRITERIA DESIGN PATTERN. FILTER ONLY GMAIL EMAILS.
            Criteria emailCriteria1 = new UserEmailGmailCriteria();
            userArr = emailCriteria1.meetCriteria(userArr);
        }
        return userArr;
    }
}