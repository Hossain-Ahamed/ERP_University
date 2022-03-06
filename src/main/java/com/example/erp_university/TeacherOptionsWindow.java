package com.example.erp_university;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherOptionsWindow {
    static  String userIDFromServer;
    public static  void getIDFromServer(String id){
        TeacherOptionsWindow.userIDFromServer = id;
    }
    @FXML
    private BorderPane userborderpane;

    @FXML
    void classroom(ActionEvent event) {

    }

    @FXML
    void messages(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("teacher_messages.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        TeacherProfile.getIDFromServer(TeacherOptionsWindow.userIDFromServer);
        Parent root = FXMLLoader.load(getClass().getResource("teacher_Profile.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);

    }

    @FXML
    void resultSubmission(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("teacher_option_add_result.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void salary(ActionEvent event) {

    }

    @FXML
    void signout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("choosetype.fxml"));
        Scene scene = new Scene(root, 900, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        System.gc();
    }

}
