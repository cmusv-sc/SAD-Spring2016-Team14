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

public class SearchContext {

    private SearchState state;
    public SearchContext(){
        this.state = null;
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    public SearchState getState() {
        return this.state;
    }
}