package controllers;

/**
 * Created by runyang on 4/26/16.
 */

import models.Group;

public abstract class GroupBuilder {
    protected Group group = new Group();

    protected abstract void buildId(Long id);

    protected abstract void buildCreatorUser(Long create);

    protected abstract void buildGroupName(String name);

    protected abstract void buildGroupDescription(String description);

    protected abstract void buildGroupUrl(String url);

    public Group getGroup() {
        return group;
    }
}
