package com.mteasdale.treewrite.model;

import javafx.scene.control.TreeItem;

import java.util.HashMap;

/**
 * Created by Michael Teasdale on 8/18/2020.
 */
public interface StoryStructure {

    HashMap<String, String[]> getClassifierMap();

    TreeItem<StoryNode> getStoryTree();

}
