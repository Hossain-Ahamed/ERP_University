package com.example.erp_university;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class login {
    String userIDFromServer = "", passwordFromServer = "";
    static  String type;
    static  Connection c1; static Statement s;
    @FXML
    private AnchorPane Sign_in_button;

  public void connects(Connection c1, Statement s){
      this.c1 = c1;
      this.s=s;
  }

  public static void typeOfUser(String type){
      login.type = type;
  }

    @FXML
    private PasswordField pass_field;

    @FXML
    private TextField userID_field;

    @FXML
    private TextField errormsg;


    @FXML
    void sign_in_button(ActionEvent event) throws SQLException, IOException {



        String order = "SELECT * FROM `"+login.type+"` WHERE userID="+userID_field.getText().trim();
        //  to show data from databaase

        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            userIDFromServer = r.getString("userID");
            passwordFromServer = r.getString("pass");
        }

        if(userID_field.getText().trim()!="" || pass_field.getText().trim()!=""){
        if (userID_field.getText().trim().equals(userIDFromServer) ) {
            if (pass_field.getText().equals(passwordFromServer)) {
                //unlock succefull
                errormsg.setText("Access Request Successful");
                if(login.type=="admin_pass"){
                    AdminOptionsWindow.getIDFromServer(userID_field.getText().trim());

                    toDatabase();
                    Parent root = FXMLLoader.load(getClass().getResource("admin_optionsWindow.fxml"));
                    Scene scene = new Scene(root, 900, 600);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    System.gc();
                }else if(login.type=="teacher_pass"){


                    TeacherOptionsWindow.getIDFromServer(userID_field.getText().trim());
                    toDatabase();
                    Parent root = FXMLLoader.load(getClass().getResource("teacher_optionsWindow.fxml"));
                    Scene scene = new Scene(root, 900, 600);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    System.gc();
                }else if(login.type=="student_pass"){

                   toDatabase();
                    OptionsWindow.getIDFromServer(userID_field.getText().trim());
                    Parent root = FXMLLoader.load(getClass().getResource("optionsWindow.fxml"));
                    Scene scene = new Scene(root, 900, 600);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    System.gc();
                }

            } else {
                errormsg.setText("Incorrect Password !!!");
            }
        } else {
            errormsg.setText("Incorrect UserName or Password !!!");
        }
    } else {
        errormsg.setText("  Invalid !!!");
    }
    }

    @FXML
    void keypress(KeyEvent event) throws IOException {

        if (event.getCode().toString().equals("ENTER")) {

        }



    }
    void toDatabase() throws IOException, SQLException {
        String[] pc_infos = pc_info.getInforamtion();
        LocalDate myObj = LocalDate.now();
        //log in info to server
        String order1 = "INSERT INTO `logininfo` (`userID`, `devicename`, `ip`, `mac`, `times`) VALUES ('"+userID_field.getText().trim()+"', '"+pc_infos[0]+"', '"+pc_infos[2]+"', '"+pc_infos[1]+"', '"+myObj+"');";
        s.executeUpdate(order1);
    }

}
