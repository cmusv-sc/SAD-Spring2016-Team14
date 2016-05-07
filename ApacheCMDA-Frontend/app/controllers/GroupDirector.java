package controllers;

/**
 * Created by runyang on 4/26/16.
 */

import models.Group;
import com.fasterxml.jackson.databind.JsonNode;

public class GroupDirector {
    private GroupBuilder builder;

    public GroupDirector(GroupBuilder builder) {
        this.builder = builder;
    }

    public Group construct(JsonNode node) {

        if (node.get("id")!=null)
            builder.buildId(node.get("id").asLong());
        if (node.get("creatorUser")!=null)
            builder.buildCreatorUser(node.get("creatorUser").asLong());
        if (node.get("groupName")!=null)
            builder.buildGroupName(node.get("groupName").asText());
        if (node.get("groupUrl")!=null)
            builder.buildGroupUrl(node.get("groupUrl").asText());
        if (node.get("groupDescription")!=null)
            builder.buildGroupDescription(node.get("groupDescription").asText());

        return builder.getGroup();
    }

}
