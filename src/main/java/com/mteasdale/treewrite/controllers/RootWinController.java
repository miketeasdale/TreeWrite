package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Michael Teasdale on 7/15/2020.
 */
public class RootWinController {
    @FXML
    private TreeView<StoryNode> storyTreeView;

    @FXML
    private AnchorPane viewAnchorPane;

    private final String[] acts = {"Act 1", "Act 2", "Act 3"};
    private final String[] act1 = {"Introduction", "Inciting Incident", "Setup", "TP1(10%): Opportunity"};
    private final String[] act2 = {"New Situation", "TP2(25%): Change of Plans", "Progress",
            "TP3(50%): Point of No Return", "Complications and Higher Stakes", "TP4(75%): Major Setback"};
    private final String[] act3 = {"Final Push", "TP5(90-99%): Climax", "Denoument"};


    private StoryNodeController storyNodeController;

    @FXML
    public void initialize() {
        try {
            // Load story node editor and add it to view pane.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/storynode.fxml"));
            VBox storyNodeEditorPane = loader.load();
            storyNodeController = loader.getController();
            viewAnchorPane.getChildren().add(storyNodeEditorPane);
        } catch (IOException ioe) {
            System.out.println("Unable to load story node editor GUI.");
            ioe.printStackTrace();
            System.exit(-1);
        }
        storyTreeView.setCellFactory(new StoryNodeCellFactory());
        storyTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<StoryNode>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<StoryNode>> observableValue, TreeItem<StoryNode> oldValue, TreeItem<StoryNode> newValue) {
                if (oldValue != null) {
                    storyNodeController.unbindBiDirectional(oldValue.getValue());
                }
                storyNodeController.bindBiDirectional(newValue.getValue());
            }
        });
    }

    @FXML
    void newTree(ActionEvent event) {
        StoryNode rootNode = new StoryNode();
        rootNode.setClassifier(StoryNode.classifiers[0]);
        rootNode.setTitle("Story title");
        storyTreeView.setRoot(new TreeItem<>(rootNode));

    }
}
