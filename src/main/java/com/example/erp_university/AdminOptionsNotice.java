package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AdminOptionsNotice {

    Connection c1 ;
    Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label s_msg;

    @FXML
    private TextArea student_notice;

    @FXML
    private Label t_msg;

    @FXML
    private TextArea teacher_notice;

    @FXML
    void student_notice_upload(ActionEvent event) throws SQLException {

        String order ="UPDATE notice set message= '"+student_notice.getText().trim()+"' where type= 'toStudent'";
        s.executeUpdate(order);
        student_notice.setText("");
        s_msg.setText("Successful");


    }

    @FXML
    void teacher_notice_upload(ActionEvent event) throws SQLException {
        String order ="UPDATE notice set message= '"+teacher_notice.getText().trim()+"' where type= 'toTeachers'";
        s.executeUpdate(order);
        teacher_notice.setText("");
        t_msg.setText("Successful");
    }

    @FXML
    void initialize() {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;

    }

}
