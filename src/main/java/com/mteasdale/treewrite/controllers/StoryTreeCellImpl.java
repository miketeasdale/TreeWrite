package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryNodeImageViews;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

/**
 * Created by Michael Teasdale on 7/23/2020.
 */
public class StoryTreeCellImpl extends TreeCell<StoryNode> {

    private final ContextMenu storyItemMenu = new ContextMenu();

    public StoryTreeCellImpl() {
        MenuItem addMenuItem = new MenuItem("Add Child");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        storyItemMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        addMenuItem.setOnAction((ActionEvent t) -> {
            StoryNode storyNode = new StoryNode();
            TreeItem<StoryNode> treeItem =
                    new TreeItem<>(storyNode);
            getTreeItem().getChildren().add(treeItem);
        });
        deleteMenuItem.setOnAction((ActionEvent t) -> {

        });
    }

    @Override
    protected void updateItem(StoryNode storyNode, boolean empty) {
        super.updateItem(storyNode, empty);
        if (storyNode == null || empty) {
            setGraphic(null);
            setText(null);
        } else {
            ImageView iv1 = StoryNodeImageViews.getPitchIcon();
            if (storyNode.getClassifier().equals(StoryNode.classifiers[1])) {
                iv1 = StoryNodeImageViews.getPlotIcon();
            }
            if (storyNode.getClassifier().equals(StoryNode.classifiers[2])) {
                iv1 = StoryNodeImageViews.getSceneIcon();
            }
            setGraphic(iv1);
            setText(storyNode.getTitle());
            setContextMenu(storyItemMenu);
        }
    }



}
