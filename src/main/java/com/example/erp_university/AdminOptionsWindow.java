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
import java.util.Scanner;

public class AdminOptionsWindow {
    static  String userIDFromServer;
    public static  void getIDFromServer(String id){
        AdminOptionsWindow.userIDFromServer = id;
    }
    @FXML
    private BorderPane userborderpane;

    @FXML
    void courses(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_options_courseOffer.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void notice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_options_notice.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void admit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_options_salary.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void result_publish(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_options_salary.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void salary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_options_salary.fxml"));
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

    @FXML
    void teacher_add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_teacher_add.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);
    }

    @FXML
    void student_add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_student_add.fxml"));
        Pane pane = new Pane(root);
        userborderpane.setCenter(pane);

    }

}
