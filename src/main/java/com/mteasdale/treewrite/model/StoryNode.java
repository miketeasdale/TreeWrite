package com.mteasdale.treewrite.model;

import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNode {
    private long id;
    private long parent;
    private String classifier;
    private String title;
    private String summary;
    private String povChar;
    private String goal;
    private String conflict;
    private String resolution;
    private ArrayList<StoryNode> childList;

    public static StoryNode getNewStoryNode() {
        return new StoryNode();
    }
}
