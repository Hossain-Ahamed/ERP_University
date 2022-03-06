package com.example.erp_university;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionsWindow {
    static  String userIDFromServer;
    static String coursechooseType ="null";
    
    public static  void getIDFromServer(String id){
        OptionsWindow.userIDFromServer = id;
    }

    @FXML
    private BorderPane userborderpane;
    @FXML
    void account(ActionEvent event) {

    }

    @FXML
    void classroom(ActionEvent event) {

    }



    @FXML
    void messages(ActionEvent event) throws IOException {

        //StudentMessages.getIDFromServer(userIDFromServer);
        Parent root = FXMLLoader.load(getClass().getResource("student_messages.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void profile(ActionEvent event) throws IOException, SQLException {
        StudentProfile.getIDFromServer(userIDFromServer);
        Parent root = FXMLLoader.load(getClass().getResource("student_profile.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void course(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("student_options_course_entry.fxml"));
      Pane pane = new Pane(root);
        userborderpane.setCenter(pane);

    }
    @FXML
    void courseView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("student_options_course_view.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
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
