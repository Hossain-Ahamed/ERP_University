package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminOptionsCourseOffer {

    Connection c1;
    Statement s;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label msg;

    @FXML
    private Label selected_teacher_department;

    @FXML
    private Label selected_teacher_id;

    @FXML
    private Label selected_teacher_name;

    @FXML
    private TableView<teacherInfoFromServerClass> teacherNametable;

    @FXML
    private TableColumn<?, ?> teacher_table_column_ID;

    @FXML
    private TableColumn<?, ?> teacher_table_column_dept;

    @FXML
    private TableColumn<?, ?> teacher_table_column_tName;

    @FXML
    private TextField textfield_course_Name;
    @FXML
    private TextField textfield_course_code;
    @FXML
    private TextField textfield_course_credit;
    @FXML
    private TextField textfield_semester_name;

    private String addedCourse="";
    int i = 0;
    @FXML
    void add_course_button(ActionEvent event) throws SQLException {
        i++;
        String tablename = textfield_semester_name.getText().trim()+""+textfield_course_code.getText().trim()+""+textfield_course_Name.getText().trim();
        //provided course
        String updateorder ="INSERT INTO `courseoffer` (`courseCode`, `courseName`, `tableName`, `courseCredit`) VALUES ('"+textfield_course_code.getText().trim()+"', '"+textfield_course_Name.getText().trim()+"','"+tablename+"','"+textfield_course_credit.getText().trim()+"')";
        System.out.println(updateorder);
        s.executeUpdate(updateorder);
        String tablecreate = "CREATE TABLE `"+tablename+"` ( studentID varchar(10) , result varchar(100));";
        System.out.println(tablecreate);
         s.executeUpdate(tablecreate);
        //add to teacher
        addedCourse += "\"course No :"+i+",course code :"+textfield_course_code.getText().trim()+",course name :"+textfield_course_Name.getText().trim()+",table Name :"+tablename+"\"";
        textfield_course_code.setText("");
        textfield_course_Name.setText("");
        textfield_course_credit.setText("");
    }

    @FXML
    void submit_button(ActionEvent event) throws SQLException {

        //semseterNo - teacher name - teacher id - teacher department - course -course count
        selected_teacher_department.getText();
        String order1 = "\""+addedCourse+"\"";
        String updateorder ="INSERT INTO `teacher_courses` (`teacherID`, `teacherName`, `teacherDept`, `course`, `courseCount`, `semesterName`) VALUES ('"+selected_teacher_id.getText()+"', '"+selected_teacher_name.getText()+"', '"+selected_teacher_department.getText()+"', '"+order1+"','"+i+"','"+textfield_semester_name.getText().trim()+"');";
        System.out.println(updateorder);
        s.executeUpdate(updateorder);
        textfield_course_code.setText("");
        textfield_course_Name.setText("");
        textfield_semester_name.setText("");
        selected_teacher_name.setText("");
        selected_teacher_id.setText("");
        selected_teacher_department.setText("");
        msg.setText("Successful");
        i =0;
    }

    private String semesterNameSession="",teacherIDFromServer = "", teacherInfoFromServer = "", teacherNameFromServer = "", teacherDeptFromServer = "";

    @FXML
    void initialize() throws SQLException {
        this.c1 = jdbc.c1;
        this.s = jdbc.s;
        this.semesterNameSession = jdbc.semesterNameSession;


        textfield_semester_name.setText(semesterNameSession);



        //column
        teacher_table_column_ID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        teacher_table_column_tName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        teacher_table_column_dept.setCellValueFactory(new PropertyValueFactory<>("teacherDepartment"));

        //load data from server
        String regex = "\"Name :(.*?),ID :(.*?),Department :(.*?),Gender :(.*?),Designation :(.*?),Mobile :(.*?),Age :(.*?),Address :(.*?),Education :(.*?)\"";
        String order = "SELECT ID,information FROM `teacher_info`";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            this.teacherIDFromServer = r.getString("ID");
            this.teacherInfoFromServer = r.getString("information");

            //match to find the teacher info and load into table
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(this.teacherInfoFromServer);
            matcher.find();

            //got the name,dept from the server and mattch with regex and get "name,department"
            this.teacherNameFromServer = matcher.group(1);
            this.teacherDeptFromServer = matcher.group(3);

            //add to table ::  step 1 : constructor class call ,,,, step 2 : add to table
            teacherInfoFromServerClass obj = new teacherInfoFromServerClass(teacherIDFromServer, teacherNameFromServer, teacherDeptFromServer);
            teacherNametable.getItems().add(obj);
    }



    }


    @FXML
    void selectTable(MouseEvent event) {


        ObservableList<teacherInfoFromServerClass> choosedItem;
        choosedItem = teacherNametable.getSelectionModel().getSelectedItems();
        selected_teacher_department.setText("");
        selected_teacher_name.setText("");
        selected_teacher_id.setText("");
        //teacher data load from the column

        String selectedTeacherID = choosedItem.get(0).getTeacherID();
        String selectedTeacherName = choosedItem.get(0).getTeacherName();
        String selectedTeacherdepartment = choosedItem.get(0).getTeacherDepartment();


        //print the data into the label(name,id,dept)
        selected_teacher_id.setText(selectedTeacherID);
        selected_teacher_name.setText(selectedTeacherName);
        selected_teacher_department.setText(selectedTeacherdepartment);
    }

}
