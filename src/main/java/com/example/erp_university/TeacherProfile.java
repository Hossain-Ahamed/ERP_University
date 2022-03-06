package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherProfile {

    Connection c1;
    Statement s;
    static  String userIDFromServer;
    public static  void getIDFromServer(String id){
        TeacherProfile.userIDFromServer = id;
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label address;

    @FXML
    private Label age;

    @FXML
    private Label dept;

    @FXML
    private Label designition;

    @FXML
    private Label edu_status;

    @FXML
    private Label gender;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label phone_number;

    @FXML
    void initialize() throws SQLException {
         this.c1 = jdbc.c1;
         this.s = jdbc.s;
        String teacherIDFromServer ="";
        String teacherInfoFromServer ="";
        ///load data from server
        String regex = "\"Name :(.*?),ID :(.*?),Department :(.*?),Gender :(.*?),Designation :(.*?),Mobile :(.*?),Age :(.*?),Address :(.*?),Education :(.*?)\"";
        String order = "SELECT ID,information FROM `teacher_info` where ID  ="+TeacherProfile.userIDFromServer+"";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            teacherIDFromServer = r.getString("ID");
            teacherInfoFromServer = r.getString("information");

            //match to find the teacher info and load into table
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(teacherInfoFromServer);
            matcher.find();

            name.setText(matcher.group(1));
            id.setText(matcher.group(2));
            dept.setText(matcher.group(3));
            gender.setText(matcher.group(4));
            designition.setText(matcher.group(5));
            phone_number.setText(matcher.group(6));
            age.setText(matcher.group(7));
            address.setText(matcher.group(8));
            edu_status.setText(matcher.group(9));



        }



    }

}
