package com.example.erp_university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class START extends Application {
    Stage primaryStage;
    @Override
    public void start(Stage window) throws IOException, ClassNotFoundException,SQLException {
        //jdbc driver path connect
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/erp-management-system?severTimezone=UTC";
        String user = "root";
        String password = "";
        //connection with driver
        Connection c1 = DriverManager.getConnection(url, user, password);
        //creaate a statement
        Statement s = c1.createStatement();
        new login().connects(c1,s);


        //semester name load
        String semNameOrder ="SELECT semesterName,min_credit,max_credit FROM `semname`;";
        ResultSet q = s.executeQuery(semNameOrder);
        String semesterNameSession ="";
        String min_credit="",max_credit="";
        while (q.next()) {
            semesterNameSession = q.getString("semesterName");
            min_credit = q.getString("min_credit");
            max_credit = q.getString("max_credit");
        }

        //thorugh the connection, so it can be used later
        new jdbc(c1,s,semesterNameSession,min_credit,max_credit);
        String[] pc_infos = pc_info.getInforamtion();

        primaryStage = window;
        primaryStage.setTitle("PC: "+pc_infos[0]+"--IP: "+pc_infos[2]);
      //  primaryStage.getIcons().add(new Image("/Image/iconshare.png"));
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("choosetype.fxml"));
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {


        launch();
    }
}