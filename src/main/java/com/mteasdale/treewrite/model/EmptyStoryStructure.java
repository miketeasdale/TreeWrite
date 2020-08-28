package com.mteasdale.treewrite.model;

import javafx.scene.control.TreeItem;

import java.util.HashMap;

/**
 * Created by Michael Teasdale on 8/18/2020.
 */
public class EmptyStoryStructure implements StoryStructure {

    @Override
    public HashMap<String, String[]> getClassifierMap() {
        return null;
    }

    public TreeItem<StoryNode> getStoryTree() {
        return new TreeItem<>(new StoryNode("Pitch", "None"));
    }
}
