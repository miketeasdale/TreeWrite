package com.mteasdale.treewrite;/**
 * Created by Michael Teasdale on 7/15/2020.
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/rootwin.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("TreeWrite");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            System.out.println("Unable to load UI file.");
            ioe.printStackTrace();
        }
    }
}
