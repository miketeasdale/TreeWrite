package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.NodeClassifier;
import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryNodeImageViews;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * Created by Michael Teasdale on 7/22/2020.
 */
public class StoryNodeCellFactory implements Callback<TreeView<StoryNode>, TreeCell<StoryNode>> {

    @Override
    public TreeCell<StoryNode> call(TreeView<StoryNode> storyNodeTreeView) {
        TreeCell<StoryNode> cell = new TreeCell<>() {
            @Override
            protected void updateItem(StoryNode item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) return;

                ImageView iv1 = StoryNodeImageViews.getPitchIcon();
                if (item.getClassifier().equals(NodeClassifier.PLOT)) {
                    iv1 = StoryNodeImageViews.getPlotIcon();
                }
                if (item.getClassifier().equals(NodeClassifier.SCENE)) {
                    iv1 = StoryNodeImageViews.getSceneIcon();
                }
                setGraphic(iv1);
                setText(item.getTitle());
            }
        };
        return cell;
    }
}
