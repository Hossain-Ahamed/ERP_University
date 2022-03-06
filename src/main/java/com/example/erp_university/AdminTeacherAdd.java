package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminTeacherAdd {
    Connection c1;
    Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Teacher_Address_txtField;

    @FXML
    private TextField Teacher_Dept_textField;

    @FXML
    private TextField Teacher_ID_TxtField;

    @FXML
    private TextField Teacher_Name_textField;

    @FXML
    private TextField Teacher_edu_textField;

    @FXML
    private TextField teacher_Gender_textfield;

    @FXML
    private TextField teacherAge_textfield;

    @FXML
    private TextField teacher_Mob_textfield;

    @FXML
    private TextField teacher_des_textfield;
    @FXML
    private PasswordField c_pass;

    @FXML
    private PasswordField pass;
    @FXML
    private Label msg;
    @FXML
    void submit(ActionEvent event) throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        String order = "\"Name :"+Teacher_Name_textField.getText().trim()+",ID :"+Teacher_ID_TxtField.getText().trim()+",Department :"+Teacher_Dept_textField.getText().trim()+",Gender :"+teacher_Gender_textfield.getText().trim()+",Designation :"+teacher_des_textfield.getText().trim()+",Mobile :"+teacher_Mob_textfield.getText().trim()+",Age :"+teacherAge_textfield.getText().trim()+",Address :"+Teacher_Address_txtField.getText().trim()+",Education :"+Teacher_edu_textField.getText().trim()+"\"";
        //System.out.println(order);
        String updateorder ="INSERT INTO `teacher_info` (`ID`, `information`) VALUES ('"+Teacher_ID_TxtField.getText().trim()+"', '"+order+"')";
        String updateorder2 ="INSERT INTO `teacher_pass` (`userID`, `pass`) VALUES ('"+Teacher_ID_TxtField.getText().trim()+"', '"+pass.getText()+"');";
        s.executeUpdate(updateorder);
        s.executeUpdate(updateorder2);
        Teacher_Name_textField.setText("");
        Teacher_ID_TxtField.setText("");
        Teacher_Dept_textField.setText("");
        teacher_Gender_textfield.setText("");
        teacher_des_textfield.setText("");
        teacher_Mob_textfield.setText("");
        teacherAge_textfield.setText("");
        Teacher_Address_txtField.setText("");
        Teacher_edu_textField.setText("");
        pass.setText("");
        c_pass.setText("");
        msg.setText("successful");
    }

    @FXML
    void initialize() {

    }

}
