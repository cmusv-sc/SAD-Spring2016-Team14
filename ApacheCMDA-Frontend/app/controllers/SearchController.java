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


public class SearchController extends Controller{
    public static boolean notpass() {
        if (session("id") == null) {
            return true;
        }
        return false;
    }

    public static Result index() {
        if (notpass()) return redirect(routes.Application.login());
        System.out.println("In search Index");
        return ok(search.render(session("username"), session("id"), null, null, null));
    }

    public static Result search(String category, String keywd) {
        if (notpass()) return redirect(routes.Application.login());
        String path = null;
        ArrayList<User> userArr = new ArrayList<User>();
        System.out.println("In search function");
        SearchContext searchContext = new SearchContext();
        switch (category) {
            case "user":
                System.out.println("Searching users");
                path = "users";
                JsonNode response = APICall.callAPI(Constants.NEW_BACKEND + path + "/search/" + keywd + "/json");
                if (response == null || response.has("error")) {
                    flash("error", response.get("error").textValue());
                    return ok(search.render(session("username"), session("id"), null, null, null));
                }
//                for (JsonNode n: response) {
//                    User obj = new User();
//                    obj.setUserName(n.get("userName").textValue());
//                    try {
//                        obj.setEmail(n.get("email").textValue());
//                    } catch (Exception e){
//                        obj.setEmail("");
//                    }
//                    obj.setUserName(n.get("userName").textValue()); obj.setId(Long.parseLong(n.get("id").toString()));
//                    obj.setAvatar(n.get("avatar").textValue());
//
//                    userArr.add(obj);
//
//                    //USE CRITERIA DESIGN PATTERN. FILTER ONLY GMAIL EMAILS.
//                    Criteria emailCriteria1 = new UserEmailGmailCriteria();
//                    userArr = emailCriteria1.meetCriteria(userArr);
//                }
                SearchState userSearchState = new UserSearchState();
                searchContext.setState(userSearchState);

                userArr = searchContext.getState().doSearch(response);
                return ok(search.render(session("username"), session("id"), category, userArr, null));
            case "tag":
                ArrayList<Workflow> wfArr = new ArrayList<Workflow>();
                JsonNode wfresponse = APICall.callAPI(Constants.NEW_BACKEND + "workflow/getByTag/tag/" + keywd);
                for (JsonNode n: wfresponse) {
                    Workflow wf = new Workflow(n);
                    wfArr.add(wf);
                }
                return ok(search.render(session("username"), session("id"), category, null, wfArr));
            case "workflow":
                ArrayList<Workflow> wfArrt = new ArrayList<Workflow>();
                JsonNode wfresponset = APICall.callAPI(Constants.NEW_BACKEND + "workflow/getByTitle/title/" + keywd);
//                for (JsonNode n: wfresponset) {
//                    Workflow wf = new Workflow(n);
//                    wfArrt.add(wf);
//                }
                SearchState workflowSearchState = new WorkflowSearchState();
                searchContext.setState(workflowSearchState);

                wfArrt = searchContext.getState().doSearch(wfresponset);

                return ok(search.render(session("username"), session("id"), category, null, wfArrt));
        }

        return ok(search.render(session("username"), session("id"), category, null, null));
    }
}

