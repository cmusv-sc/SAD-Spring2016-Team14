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
import com.fasterxml.jackson.databind.node.ObjectNode;
import util.APICall;
import util.Constants;

public class CommentWorkflowDecorator extends WorkflowDecorator{

    private final static String CREATE = Constants.NEW_BACKEND + "workflow/addComment";


    public static JsonNode create(ObjectNode node) {
        JsonNode response = APICall.postAPI(CREATE, node);
        return response;
    }
}