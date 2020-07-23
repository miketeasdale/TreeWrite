package com.mteasdale.treewrite.model;

import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNode {
    private long id;
    private long parent;
    private NodeClassifier classifier;
    private NodeSubclassifier subclassifier;
    private String title;
    private String summary;
    private String povChar;
    private String goal;
    private String conflict;
    private String resolution;
    private ArrayList<StoryNode> childList;

    public static StoryNode StoryNodeFactory() {
        return new StoryNode();
    }

    public static StoryNode getNewPitchNode() {
        StoryNode node = new StoryNode();
        node.classifier = NodeClassifier.PITCH;
        node.title = "Story title.";
        node.summary = "A quick summmary which can read like a jacked blurb, or like a query lead.";
        node.povChar = "The story's main character.";
        node.goal = "What the main character hopes to achieve.";
        node.conflict = "Forces that must be overcome for success (situation or antagonist)";
        node.resolution = "How the story ends.";
        return node;
    }

    private StoryNode() {
    }

    public NodeClassifier getClassifier() {
        return classifier;
    }

    public void setClassifier(NodeClassifier classifier) {
        this.classifier = classifier;
    }

    public NodeSubclassifier getSubclassifier() {
        return subclassifier;
    }

    public void setSubclassifier(NodeSubclassifier subclassifier) {
        this.subclassifier = subclassifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPovChar() {
        return povChar;
    }

    public void setPovChar(String povChar) {
        this.povChar = povChar;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getConflict() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict = conflict;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
