package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.FileIO;
import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryStructureFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Michael Teasdale on 7/15/2020.
 */
public class RootWinController {
    @FXML
    private TreeView<StoryNode> storyTreeView;

    @FXML
    private AnchorPane viewAnchorPane;

    private StoryNodeController storyNodeController;
    private final FileIO fileIO = new FileIO();

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
        storyTreeView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null)
                storyNodeController.empty(oldValue.getValue());
            if (newValue != null)
            storyNodeController.fill(newValue.getValue());
        });
    }

    @FXML
    private void newTree() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Story Tree");
        alert.setHeaderText("Press OK to create a Full Story Tree.\nPress CANCEL to create an Empty Story Tree.");
        alert.showAndWait().ifPresent(buttonType ->
            storyTreeView.setRoot((buttonType == ButtonType.OK) ?
                    StoryStructureFactory.getStoryStructure(StoryStructureFactory.Structures.THREE_ACT).getStoryTree() :
                    StoryStructureFactory.getStoryStructure(StoryStructureFactory.Structures.EMPTY).getStoryTree()));
    }

    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TreeWrite", "*.twr"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showSaveDialog(viewAnchorPane.getScene().getWindow());
        if (file != null) {
            fileIO.save(file.toPath(), storyTreeView.getRoot());
        }
    }

    @FXML
    private void save() {
        if (fileIO.getFilePath() == null)
            saveAs();
        else {
            fileIO.save(null, storyTreeView.getRoot());
        }
    }

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TreeWrite", "*.twr"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        if (fileIO.getFilePath() != null)
            fileChooser.setInitialDirectory(fileIO.getFilePath().getParent().toFile());
        Path path = fileChooser.showOpenDialog(viewAnchorPane.getScene().getWindow()).toPath();
        storyTreeView.setRoot(fileIO.open(path));
    }

    @FXML
    private void close() {
        storyTreeView.getRoot().getChildren().clear();
        storyTreeView.setRoot(null);
    }
}
