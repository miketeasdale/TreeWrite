package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 * Created by Michael Teasdale on 7/22/2020.
 */
public class StoryNodeCellFactory implements Callback<TreeView<StoryNode>, TreeCell<StoryNode>> {
    @Override
    public TreeCell<StoryNode> call(TreeView<StoryNode> storyNodeTreeView) {
        return new StoryTreeCellImpl();
    }
}
