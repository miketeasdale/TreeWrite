package com.mteasdale.treewrite.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNodeController {
    @FXML
    private TextField titleField;

    @FXML
    private ChoiceBox<?> typeSelector;

    @FXML
    private ChoiceBox<?> subtypeSelector;

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
    void cancel(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}
