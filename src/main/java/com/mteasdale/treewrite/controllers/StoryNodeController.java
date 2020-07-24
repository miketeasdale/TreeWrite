package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    public TextField getTitleField() {
        return titleField;
    }

    public ChoiceBox<?> getClassifierSelector() {
        return classifierSelector;
    }

    public ChoiceBox<?> getSubclassifierSelector() {
        return subclassifierSelector;
    }

    public TextField getGoalField() {
        return goalField;
    }

    public TextField getConflictField() {
        return conflictField;
    }

    public TextField getResolutionField() {
        return resolutionField;
    }

    public TextArea getSummaryArea() {
        return summaryArea;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    @FXML
    public void initialize() {
        classifierSelector.setItems(FXCollections.observableList(Arrays.asList(StoryNode.classifiers)));
    }

    public void unbindBiDirectional(StoryNode storyNode) {
        titleField.textProperty().unbindBidirectional(storyNode.titleProperty());
        goalField.textProperty().unbindBidirectional(storyNode.goalProperty());
        conflictField.textProperty().unbindBidirectional(storyNode.conflictProperty());
        resolutionField.textProperty().unbindBidirectional(storyNode.resolutionProperty());
        summaryArea.textProperty().unbindBidirectional(storyNode.summaryProperty());
    }

    public void bindBiDirectional(StoryNode storyNode) {
        titleField.textProperty().bindBidirectional(storyNode.titleProperty());
        goalField.textProperty().bindBidirectional(storyNode.goalProperty());
        conflictField.textProperty().bindBidirectional(storyNode.conflictProperty());
        resolutionField.textProperty().bindBidirectional(storyNode.resolutionProperty());
        summaryArea.textProperty().bindBidirectional(storyNode.summaryProperty());
    }

    @FXML
    private void cancel(ActionEvent event) {

    }

    @FXML
    private void save(ActionEvent event) {

    }
}
