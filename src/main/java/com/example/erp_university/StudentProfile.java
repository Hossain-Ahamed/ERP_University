package com.example.erp_university;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentProfile {
    static  String userIDFromServer;


    public static  void getIDFromServer(String id) throws SQLException {
        StudentProfile.userIDFromServer = id;

    }

    @FXML
    private Label ID;

    @FXML
    private Label address;

    @FXML
    private Label batch;

    @FXML
    private Label dept;

    @FXML
    private Label dob;

    @FXML
    private Label father_name;

    @FXML
    private Label gender;

    @FXML
    private Label mother_name;

    @FXML
    private Label name;

    @FXML
    private Label phone_number;

    @FXML
    private Label reg_num;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        Connection c1;
        Statement s;
        String information = "";
        String regex = "\"Name :(.*?),Father Name :(.*?),Mother Name :(.*?),ID :(.*?),Registration Number :(.*?),Batch :(.*?),Department :(.*?),Date of birth :(.*?),Gender :(.*?),Phone number :(.*?),Address :(.*?)\"";
        String order = "SELECT information FROM `student_info` WHERE ID=" + StudentProfile.userIDFromServer;
        //  to show data from databaase
        c1 = jdbc.c1;
        s = jdbc.s;
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            information = r.getString("information");
        }

        //  System.out.println(information);   //got the data from database
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(information);
        matcher.find();
//        boolean m;
//        int a = matcher.groupCount();
//        System.out.println(a);
//        int i = 1;
//
//        while (i <= matcher.groupCount()) {
//            System.out.println(i + " : " + matcher.group(i));
//            i++;
//        }

        //to print in the fx file
        name.setText(matcher.group(1));
        father_name.setText(matcher.group(2));
        mother_name.setText(matcher.group(3));
        ID.setText(matcher.group(4));
        reg_num.setText(matcher.group(5));
        batch.setText(matcher.group(6));
        dept.setText(matcher.group(7));
        dob.setText(matcher.group(8));
        gender.setText(matcher.group(9));
        phone_number.setText(matcher.group(10));
        address.setText(matcher.group(11));
    }





}
