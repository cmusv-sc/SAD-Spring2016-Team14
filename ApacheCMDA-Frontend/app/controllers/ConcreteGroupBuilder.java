package controllers;

/**
 * Created by runyang on 4/26/16.
 */
public class ConcreteGroupBuilder extends GroupBuilder {
    @Override
    protected void buildId(Long id) {
        group.id = id;
    }

    protected void buildCreatorUser(Long user) {
        group.creatorUser = user;
    }

    protected void buildGroupName(String name) {
        group.groupName = name;
    }

    protected void buildGroupDescription(String description) {
        group.groupDescription = description;
    }

    protected void buildGroupUrl(String url) {
        group.groupUrl = url;
    }
}
