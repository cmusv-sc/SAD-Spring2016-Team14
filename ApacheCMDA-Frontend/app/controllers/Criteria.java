package controllers;


import com.fasterxml.jackson.databind.node.ArrayNode;
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

public interface Criteria{
    public ArrayList<User> meetCriteria(ArrayList<User> userList);
}