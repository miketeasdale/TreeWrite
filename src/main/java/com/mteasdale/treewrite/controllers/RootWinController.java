package com.mteasdale.treewrite.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryStructure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael Teasdale on 7/15/2020.
 */
public class RootWinController {
    @FXML
    private TreeView<StoryNode> storyTreeView;

    @FXML
    private AnchorPane viewAnchorPane;

    private static Path savePath;
    private final Gson gson = new Gson();

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
            storyTreeView.setRoot((buttonType == ButtonType.OK) ? createFullStoryTree() : createEmptyStoryTree()));
    }

    private TreeItem<StoryNode> createFullStoryTree() {
        return null;
    }

    private TreeItem<StoryNode> createEmptyStoryTree() {
        StoryNode rootNode = new StoryNode(new StoryStructure().getClassifiers().get(0), "Story Title" );
        return new TreeItem<>(rootNode);
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
            savePath = file.toPath();
            save();
        }
    }

    @FXML
    private void save() {
        if (savePath == null)
            saveAs();
        else {
            String output = gson.toJson(getStoryNodeList());
            try {
                Files.writeString(savePath, output, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ioe) {
                System.out.println("File save error.");
            }
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
        if (savePath != null) fileChooser.setInitialDirectory(savePath.getParent().toFile());
        File file = fileChooser.showOpenDialog(viewAnchorPane.getScene().getWindow());
        if (file != null) {
            savePath = file.toPath();
            try {
                String result = Files.readString(savePath);
                getStoryNodeList(result);
            } catch (IOException ioe) {
                System.out.println("File read error.");
            }
        }
    }

    @FXML
    private void close() {
        storyTreeView.getRoot().getChildren().clear();
        storyTreeView.setRoot(null);
    }

    private ArrayList<StoryNode> getStoryNodeList() {
        ArrayList<StoryNode> storyNodeArrayList = new ArrayList<>();
        treeToList(storyNodeArrayList, storyTreeView.getRoot());
        return storyNodeArrayList;
    }

    // Recursive tree traversal. Hard on the stack, but it should be a shallow list.
    private void treeToList(ArrayList<StoryNode> list, TreeItem<StoryNode> item) {
        if (item != null) {
            StoryNode storyNode = item.getValue();
            list.add(storyNode);
            storyNode.setId(list.size());
            if (item.getParent() != null)
                storyNode.setParent(item.getParent().getValue().getId());
            if (item.getChildren() != null) {
                for (TreeItem<StoryNode> child : item.getChildren())
                    treeToList(list, child);
            }
        }
    }

    private void getStoryNodeList(String jsonString) {
        Type listType = new TypeToken<ArrayList<StoryNode>>(){}.getType();
        ArrayList<StoryNode> storyNodeList = gson.fromJson(jsonString, listType);
        HashMap<Integer, TreeItem<StoryNode>> treeItemMap = new HashMap<>();
        for (StoryNode s : storyNodeList) treeItemMap.put(s.getId(), new TreeItem<>(s));
        TreeItem<StoryNode> root = null;
        for (Map.Entry<Integer, TreeItem<StoryNode>> current : treeItemMap.entrySet()) {
            TreeItem<StoryNode> treeItem = current.getValue();
            if (treeItem.getValue().getParent() == 0) root = treeItem;
            else treeItemMap.get(treeItem.getValue().getParent()).getChildren().add(treeItem);
        }
        storyTreeView.setRoot(root);
    }
}
