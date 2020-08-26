package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import com.mteasdale.treewrite.model.ThreeActStoryStructure;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

    private final ThreeActStoryStructure structure = new ThreeActStoryStructure();
    private final ChangeListener<String> classifierListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (newValue != null) {
                ThreeActStoryStructure structure = new ThreeActStoryStructure();
                subclassifierSelector.setItems(FXCollections.observableList(
                        new ArrayList<>(Arrays.asList(structure.CLASSIFIER_MAP.get(newValue)))));
            }
        }
    };

    @FXML
    public void initialize() {
        classifierSelector.setItems(FXCollections.observableList(new ArrayList<>(structure.CLASSIFIER_MAP.keySet())));
        classifierSelector.getSelectionModel().selectedItemProperty().
                addListener(((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        subclassifierSelector.setItems(FXCollections.observableList(
                                new ArrayList<>(Arrays.asList(structure.CLASSIFIER_MAP.get(newValue)))));
                    }
                }));
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
