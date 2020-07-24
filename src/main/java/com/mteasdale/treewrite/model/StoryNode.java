package com.mteasdale.treewrite.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNode {
    public static final String[] classifiers = {"Pitch", "Act", "Plot", "Scene", "Other"};

    private long id;
    private long parent;
    private StringProperty classifier = new SimpleStringProperty();
    private StringProperty subclassifier = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty summary = new SimpleStringProperty();
    private StringProperty povChar = new SimpleStringProperty();
    private StringProperty goal = new SimpleStringProperty();
    private StringProperty conflict = new SimpleStringProperty();
    private StringProperty resolution = new SimpleStringProperty();
    private ArrayList<StoryNode> childList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getClassifier() {
        return classifier.get();
    }

    public StringProperty classifierProperty() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier.set(classifier);
    }

    public String getSubclassifier() {
        return subclassifier.get();
    }

    public StringProperty subclassifierProperty() {
        return subclassifier;
    }

    public void setSubclassifier(String subclassifier) {
        this.subclassifier.set(subclassifier);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getSummary() {
        return summary.get();
    }

    public StringProperty summaryProperty() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary.set(summary);
    }

    public String getPovChar() {
        return povChar.get();
    }

    public StringProperty povCharProperty() {
        return povChar;
    }

    public void setPovChar(String povChar) {
        this.povChar.set(povChar);
    }

    public String getGoal() {
        return goal.get();
    }

    public StringProperty goalProperty() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal.set(goal);
    }

    public String getConflict() {
        return conflict.get();
    }

    public StringProperty conflictProperty() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict.set(conflict);
    }

    public String getResolution() {
        return resolution.get();
    }

    public StringProperty resolutionProperty() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution.set(resolution);
    }

    public ArrayList<StoryNode> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<StoryNode> childList) {
        this.childList = childList;
    }
}
