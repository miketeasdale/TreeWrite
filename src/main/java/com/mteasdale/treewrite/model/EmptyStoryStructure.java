package com.mteasdale.treewrite.model;

import javafx.scene.control.TreeItem;

/**
 * Created by Michael Teasdale on 8/18/2020.
 */
public class EmptyStoryStructure implements StoryStructure {

    public TreeItem<StoryNode> getStoryTree() {
        return new TreeItem<>(new StoryNode("Pitch", "None"));
    }
}
