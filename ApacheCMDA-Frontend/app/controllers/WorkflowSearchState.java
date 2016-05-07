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

public class WorkflowSearchState implements SearchState<Workflow>{

    @Override
    public ArrayList<Workflow> doSearch(JsonNode wfresponset){
        System.out.println("Searching users");
        ArrayList<Workflow> wfArrt = new ArrayList<Workflow>();

        for (JsonNode n: wfresponset) {
            Workflow wf = new Workflow(n);
            wfArrt.add(wf);
        }
        return wfArrt;

    }
}