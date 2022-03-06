package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TeacherOptionAddResult {

    Connection c1;
    Statement s;

    @FXML
    private TableView<teacherAddresultTableClass> studentIDTable;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> selectCourseChoiceBox;

    @FXML
    private Label selectdCourse;

    @FXML
    private Label studentID;

    @FXML
    private TableColumn<?, ?> studentIDColumn;

    @FXML
    private Label selectdCourseCode;



    @FXML
    private TextField textarea_result;

    private String[][] choicBoxData;
    private String[] getIndex;
    private  String TeacherCoursesFromDatabase, TeachercourseCountFromDatabase;
    private  String studentIDFromServer;


    @FXML
    void initialize() throws SQLException {
        //column make
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        this.c1 = jdbc.c1;
        this.s = jdbc.s;

      String fstorder="SELECT courseCount,course FROM `teacher_courses` WHERE teacherID ='"+TeacherOptionsWindow.userIDFromServer+"' AND  semesterName='"+jdbc.semesterNameSession+"';";
        ResultSet r = s.executeQuery(fstorder);

 int l =0;
        while (r.next()) {
            TeacherCoursesFromDatabase = r.getString("course");
            TeachercourseCountFromDatabase = r.getString("courseCount");
            if(l==0){
                choiceBoxsize(Integer.parseInt(TeachercourseCountFromDatabase));
                l=100 ; // change the value ;; so it wont be able to execute again
            }
            printToTable();
        }
        selectCourseChoiceBox.setOnAction(event -> {
            try {
                getData(event);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }
    private void choiceBoxsize(int size){
        choicBoxData = new String[(size+1)][4];
        getIndex = new String[(size+1)];
    }

    @FXML
    private Label msg;

    private void printToTable() throws SQLException{

        int count = Integer.parseInt(TeachercourseCountFromDatabase);
        String regex = "\"";


        for (int i = 1; i <= count; i++) {

            regex += "\"course No :"+i+",course code :(.*?),course name :(.*?),table Name :(.*?)\"";

        }
        regex += "\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(TeacherCoursesFromDatabase);
        matcher.find();

        int number = 1;
        for (int k = 1; k <= count; k++) {


            choicBoxData[k][1] = matcher.group(number); ///course code
            number++;
            choicBoxData[k][2] = matcher.group(number); //course name
            getIndex[k] =matcher.group(number);
            number++;

            choicBoxData[k][3] = matcher.group(number); ///table name insql
            number++;

            selectCourseChoiceBox.getItems().add(choicBoxData[k][2]);
        }
    }

    private int a;
    private  void getData(ActionEvent event) throws SQLException {
        for (int i = 0; i<studentIDTable.getItems().size(); i++) {
            studentIDTable.getItems().clear();
        }
        String data = selectCourseChoiceBox.getValue();
        selectdCourse.setText(data);

         a=Arrays.asList(getIndex).indexOf(data);
        selectdCourseCode.setText(choicBoxData[a][1]);

        loadToTable(a); //to show stduent id in table

    }

    private  void loadToTable(int a) throws SQLException {

        String tableOrder="SELECT studentID FROM `"+choicBoxData[a][3]+"`;";
        ResultSet r = s.executeQuery(tableOrder);
        while (r.next()){
            studentIDFromServer = r.getString("studentID");

            teacherAddresultTableClass obj= new teacherAddresultTableClass(studentIDFromServer);

            studentIDTable.getItems().add(obj);

        }

    }

    @FXML
    void selections(MouseEvent event) {

        ObservableList<teacherAddresultTableClass> choosedItem;
        choosedItem = studentIDTable.getSelectionModel().getSelectedItems();
        String selectedID = choosedItem.get(0).getStudentID();
        studentID.setText(selectedID);
    }


    @FXML
    void submit(ActionEvent event) throws SQLException {

        String updateOrder ="UPDATE `"+choicBoxData[a][3]+"` SET `result` = '"+textarea_result.getText()+"' WHERE `studentID` = '"+studentID.getText()+"';";
        s.executeUpdate(updateOrder);
        msg.setText("Successfull");
        studentID.setText("");
        textarea_result.setText("");

    }
}
