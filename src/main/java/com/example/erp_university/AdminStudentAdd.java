package com.example.erp_university;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminStudentAdd {
    Connection c1;
    Statement s;
    @FXML
    private TextField address;

    @FXML
    private TextField batch;

    @FXML
    private PasswordField c_pass;

    @FXML
    private TextField dept;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField father_name;

    @FXML
    private TextField gender;

    @FXML
    private TextField id;

    @FXML
    private TextField mother_name;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phone_number;

    @FXML
    private TextField reg_num;
    @FXML
    private Label msg;
    @FXML
    void Submit(ActionEvent event) throws SQLException {
         this.c1 = jdbc.c1;
         this.s = jdbc.s;

        String order = "\"Name :"+name.getText().trim()+",Father Name :"+father_name.getText().trim()+",Mother Name :"+mother_name.getText().trim()+",ID :"+id.getText().trim()+",Registration Number :"+reg_num.getText().trim()+",Batch :"+batch.getText().trim()+",Department :"+dept.getText().trim()+",Date of birth :"+dob.getValue().toString()+",Gender :"+gender.getText().trim()+",Phone number :"+phone_number.getText().trim()+",Address :"+address.getText().trim()+"\"";


        String updateorder ="INSERT INTO `student_info` (`ID`, `information`) VALUES ('"+id.getText().trim()+"', '"+order+"')";
        String updateorder2 ="INSERT INTO `student_pass` (`userID`, `pass`) VALUES ('"+id.getText().trim()+"', '"+pass.getText()+"');";
        s.executeUpdate(updateorder);
        s.executeUpdate(updateorder2);
        name.setText("");
        father_name.setText("");
        mother_name.setText("");
        id.setText("");
        reg_num.setText("");

        address.setText("");
        batch.setText("");
        dept.setText("");
        pass.setText("");
        c_pass.setText("");
        gender.setText("");
        phone_number.setText("");
        msg.setText("successful");
    }

}
