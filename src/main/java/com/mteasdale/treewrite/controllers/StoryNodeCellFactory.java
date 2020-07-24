package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryNodeImageViews;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * Created by Michael Teasdale on 7/22/2020.
 */
public class StoryNodeCellFactory implements Callback<TreeView<StoryNode>, TreeCell<StoryNode>> {
    private ContextMenu contextMenu = new ContextMenu();

    @Override
    public TreeCell<StoryNode> call(TreeView<StoryNode> storyNodeTreeView) {
        TreeCell<StoryNode> cell = new TreeCell<>() {
            @Override
            protected void updateItem(StoryNode item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) return;

                ImageView iv1 = StoryNodeImageViews.getPitchIcon();
                if (item.getClassifier().equals(StoryNode.classifiers[1])) {
                    iv1 = StoryNodeImageViews.getPlotIcon();
                }
                if (item.getClassifier().equals(StoryNode.classifiers[2])) {
                    iv1 = StoryNodeImageViews.getSceneIcon();
                }
                setGraphic(iv1);
                setText(item.getTitle());
            }
        };
        return cell;
    }
}
