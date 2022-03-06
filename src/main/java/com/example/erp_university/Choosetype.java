package com.example.erp_university;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Choosetype {


    @FXML
    private Button Admin_Button;

    @FXML
    private Label Msg_Lable;

    @FXML
    private Button Student_Button;

    @FXML
    private Button Teacher_Button;

    @FXML
    void Admin_Button_Click(ActionEvent event) throws IOException {
        login.typeOfUser("admin_pass");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 900, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        System.gc();
    }

    @FXML
    void Student_Button_Click(ActionEvent event) throws IOException {
        login.typeOfUser("student_pass");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 900, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        System.gc();
    }
    @FXML
    void Teacher_button_Click(ActionEvent event) throws IOException {
        login.typeOfUser("teacher_pass");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 900, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        System.gc();
    }



}
