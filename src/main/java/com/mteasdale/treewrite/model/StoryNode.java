package com.mteasdale.treewrite.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNode implements Serializable {
    private Integer id;
    private Integer parent;
    private String classifier = "";
    private String subclassifier = "";
    private String title = "Story Node";
    private String summary = "";
    private String povChar = "";
    private String goal = "";
    private String conflict = "";
    private String resolution = "";
    private transient ArrayList<StoryNode> childList;

    public StoryNode() {}

    public StoryNode(String classifier, String title) {
        this.classifier = classifier;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getSubclassifier() {
        return subclassifier;
    }

    public void setSubclassifier(String subclassifier) {
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

    public ArrayList<StoryNode> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<StoryNode> childList) {
        this.childList = childList;
    }

}
