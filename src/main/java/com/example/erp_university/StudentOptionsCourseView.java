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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StudentOptionsCourseView {
  static Connection c1;
  static Statement s;
  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TableView<studentCourseOfferTableClass> courseOfferTable;

  @FXML
  private TableColumn<?, ?> courseOfferTable_column_courseCode;

  @FXML
  private TableColumn<?, ?> courseOfferTable_column_courseCredit;

  @FXML
  private TableColumn<?, ?> courseOfferTable_column_courseName;

  @FXML
  private TableColumn<?, ?> courseOfferTable_column_courseUniqueID;
  @FXML
  private Label msg;

  private String totalCourse = "", totalCredit = "", courses = "";
  String courseCodeFromServer, courseNameFromServer , courseCreditFromServer , courseUUIDFromServer;

  @FXML
  void initialize() throws SQLException {

    //column for course offer table
    courseOfferTable_column_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
    courseOfferTable_column_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
    courseOfferTable_column_courseCredit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));
    courseOfferTable_column_courseUniqueID.setCellValueFactory(new PropertyValueFactory<>("CourseUniqueID"));


    this.c1 = jdbc.c1;
    this.s = jdbc.s;

    String order = "SELECT courses,totalCredit,CourseCount FROM `student_course_data` where studentID=" + OptionsWindow.userIDFromServer + " AND semesterName='" + jdbc.semesterNameSession + "';";

    ResultSet r = s.executeQuery(order);


      while (r.next()) {
        courses = r.getString("courses");
        totalCredit = r.getString("totalCredit");
        totalCourse = r.getString("CourseCount");

        if(courses==null){
          msg.setText("You are not registered for "+jdbc.semesterNameSession);
        }else{
          printTotable();
        }


      }

  }

  private void printTotable() {

    int count = Integer.parseInt(totalCourse);
    String regex = "\"";


    for (int i = 1; i <= count; i++) {

      regex += "\"Course Code :(.*?),Course Name :(.*?),Course Credit:(.*?),Course Unique ID :(.*?)\"";

    }
    regex += "\"";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(courses);
    matcher.find();

    int number = 1;
    for (int k = 1; k <= count; k++) {


      this.courseCodeFromServer = matcher.group(number);
      number++;
      this.courseNameFromServer = matcher.group(number);
      number++;
      this.courseCreditFromServer =matcher.group(number);
      number++;
      this.courseUUIDFromServer = matcher.group(number);
      number++;


       studentCourseOfferTableClass obj = new studentCourseOfferTableClass(this.courseCodeFromServer, this.courseNameFromServer, this.courseCreditFromServer, this.courseUUIDFromServer);
      courseOfferTable.getItems().add(obj);
    }
  }
}


