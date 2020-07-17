package com.mteasdale.treewrite.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Michael Teasdale on 7/15/2020.
 */
public class RootWinController {
    @FXML
    private TreeView<String> storyTreeView;

    private VBox storyNodeEditor;

    private final String[] acts = {"Act 1", "Act 2", "Act 3"};
    private final String[] act1 = {"Introduction", "Inciting Incident", "Setup", "TP1(10%): Opportunity"};
    private final String[] act2 = {"New Situation", "TP2(25%): Change of Plans", "Progress",
            "TP3(50%): Point of No Return", "Complications and Higher Stakes", "TP4(75%): Major Setback"};
    private final String[] act3 = {"Final Push", "TP5(90-99%): Climax", "Denoument"};

    @FXML
    public void initialize() {
        try {
            storyNodeEditor = FXMLLoader.load(getClass().getResource("/storynode.fxml"));
        } catch (IOException ioe) {
            System.out.println("Unable to load story node editor GUI.");
            ioe.printStackTrace();
        }
    }

    @FXML
    void newTree(ActionEvent event) {
        TreeItem<String> concept = new TreeItem<>("Story");
        TreeItem<String> act1 = new TreeItem<>("Act 1");
        TreeItem<String> act1_1 = new TreeItem<>("Introduction");
        TreeItem<String> act1_2 = new TreeItem<>("Inciting Incident");
        act1.getChildren().addAll(act1_1, act1_2);
        TreeItem<String> act2 = new TreeItem<>("Act 2");
        TreeItem<String> act3 = new TreeItem<>("Act 3");
        concept.getChildren().addAll(act1, act2, act3);
        storyTreeView.setRoot(concept);
    }
}
