package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.NodeClassifier;
import com.mteasdale.treewrite.model.StoryNode;
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

    @FXML
    public void initialize() {
        try {
            VBox storyNodeEditorPane = FXMLLoader.load(getClass().getResource("/views/storynode.fxml"));
            viewAnchorPane.getChildren().add(storyNodeEditorPane);
            storyTreeView.setCellFactory(new StoryNodeCellFactory());
        } catch (IOException ioe) {
            System.out.println("Unable to load story node editor GUI.");
            ioe.printStackTrace();
        }
    }

    @FXML
    void newTree(ActionEvent event) {
        StoryNode rootNode = StoryNode.StoryNodeFactory();
        rootNode.setClassifier(NodeClassifier.PITCH);
        rootNode.setTitle("Story title");
        storyTreeView.setRoot(new TreeItem<>(rootNode));
    }
}
