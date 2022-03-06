package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherMessages {
    Connection c1;
    Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label academicNotice;

    @FXML
    void initialize() throws SQLException {
        academicNotice.setText("");
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        String order ="Select message from notice where type= 'toTeachers'";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            String msg = r.getString("message");
            academicNotice.setText(msg);
        }

    }
    }

