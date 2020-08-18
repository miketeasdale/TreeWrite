package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.StoryStructure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNodeController {
    @FXML
    private TextField titleField;

    @FXML
    private ChoiceBox<String> classifierSelector;

    @FXML
    private ChoiceBox<String> subclassifierSelector;

    @FXML
    private TextField goalField;

    @FXML
    private TextField conflictField;

    @FXML
    private TextField resolutionField;

    @FXML
    private TextArea summaryArea;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        classifierSelector.setItems(FXCollections.observableList(Arrays.asList(StoryStructure.THREE_ACT_CLASSIFIERS)));
        classifierSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            subclassifierSelector.setItems(FXCollections.observableList(structure.getSubclassifiers(newValue)));
        });
    }

    @FXML
    private void cancel(ActionEvent event) {

    }

    @FXML
    private void save(ActionEvent event) {

    }

    public void fill(StoryNode storyNode) {
        classifierSelector.setValue(storyNode.getClassifier());
        subclassifierSelector.setValue(storyNode.getSubclassifier());
        titleField.setText(storyNode.getTitle());
        summaryArea.setText(storyNode.getSummary());
        goalField.setText(storyNode.getGoal());
        conflictField.setText(storyNode.getConflict());
        resolutionField.setText(storyNode.getResolution());
    }

    public void empty(StoryNode storyNode) {
        storyNode.setClassifier(classifierSelector.getValue());
        storyNode.setSubclassifier(subclassifierSelector.getValue());
        storyNode.setTitle(titleField.getText());
        storyNode.setSummary(summaryArea.getText());
        storyNode.setGoal(goalField.getText());
        storyNode.setConflict(conflictField.getText());
        storyNode.setResolution(resolutionField.getText());
    }
}
