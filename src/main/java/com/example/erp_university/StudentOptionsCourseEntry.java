package com.example.erp_university;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StudentOptionsCourseEntry {

     private String userIDFromserver ="",semeseterName ="";
     int min_credit,max_credit;
     private Connection c1;
     private Statement s;
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
    private Label label_choosedCredit;

    @FXML
    private Label label_maxCredit;

    @FXML
    private Label label_minCredit;

    @FXML
    private Label msg;
    @FXML
    private TextArea sleected_course;


    private String courseCodeFromServer="",courseNameFromServer="",courseCreditFromServer="",courseUUIDFromServer="";
    @FXML
    void initialize() throws SQLException {
        sleected_course.setEditable(false);
        sleected_course.setText("");
        this.userIDFromserver = OptionsWindow.userIDFromServer;
        this.c1 = jdbc.c1;
        this.s = jdbc.s;


        String checkOrder = "SELECT * FROM `student_course_data` WHERE studentID='"+OptionsWindow.userIDFromServer+"' AND semesterName='"+jdbc.semesterNameSession+"'";
        ResultSet r = s.executeQuery(checkOrder);
        if (r.next()) {
            String show = "You have already registered for "+jdbc.semesterNameSession+" !!";
            msg.setText(show);
        }else{

            //column for course offer table
            courseOfferTable_column_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
            courseOfferTable_column_courseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
            courseOfferTable_column_courseCredit.setCellValueFactory(new PropertyValueFactory<>("CourseCredit"));
            courseOfferTable_column_courseUniqueID.setCellValueFactory(new PropertyValueFactory<>("CourseUniqueID"));

            call();
        }

    }
    private  void call() throws SQLException {
        this.min_credit = Integer.parseInt(jdbc.min_credit);
        this.max_credit = Integer.parseInt(jdbc.max_credit);

        label_minCredit.setText(jdbc.min_credit);
        label_maxCredit.setText(jdbc.max_credit);

        //sql
        String order ="SELECT courseCode,courseName,courseCredit,tableName FROM `courseoffer`";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            this.courseCodeFromServer= r.getString("courseCode");
            this.courseNameFromServer= r.getString("courseName");
            this.courseCreditFromServer= r.getString("courseCredit");
            this.courseUUIDFromServer= r.getString("tableName");

            studentCourseOfferTableClass obj = new studentCourseOfferTableClass(courseCodeFromServer,courseNameFromServer,courseCreditFromServer,courseUUIDFromServer);
            courseOfferTable.getItems().add(obj);
        }


    }
    private String courseCodeEnlisted = "";
    ArrayList<String> selectedCourseList = new ArrayList<>();

    int total=0;
   @FXML
    void addButton(MouseEvent event) {

        ObservableList<studentCourseOfferTableClass> choosedItem;
        choosedItem = courseOfferTable.getSelectionModel().getSelectedItems();
        String selectedCode = choosedItem.get(0).getCourseCode();
        String selectedName = choosedItem.get(0).getCourseName();
        String selectedCredit = choosedItem.get(0).getCourseCredit();
        String selectedUUID = choosedItem.get(0).getCourseUniqueID();


         if(courseCodeEnlisted.contains(selectedCode)){
            //already selected this course
             msg.setText("Exists already");
         }else{

             total+= Integer.parseInt(selectedCredit);
             courseCodeEnlisted += "\" "+selectedCode+" \"";   //check using this;



             String temp = "\"Course Code :"+selectedCode+",Course Name :"+selectedName+",Course Credit:"+selectedCredit+",Course Unique ID :"+selectedUUID+"\"";
             selectedCourseList.add(temp);
             sleected_course.appendText(temp+"\n");
         }


    }
    @FXML
    void submit(ActionEvent event) throws SQLException {

       if(total>18 ){
           label_choosedCredit.setText(String.valueOf(total));
           sleected_course.setText("");
           msg.setText("More than 18 credit.Choose again");
           total = 0;
           courseCodeEnlisted ="";
           selectedCourseList.clear();
       }else if(total<13){
           label_choosedCredit.setText(String.valueOf(total));
           msg.setText("less than 13.choose more");
       }else{
            String order = "\"";
           for (int i = 0; i < selectedCourseList.size(); i++) {
               order+=selectedCourseList.get(i);
               String regex ="\"Course Code :(.*?),Course Name :(.*?),Course Credit:(.*?),Course Unique ID :(.*?)\"";
               Pattern pattern = Pattern.compile(regex);
               Matcher matcher = pattern.matcher(selectedCourseList.get(i));
               matcher.find();
               String insertToDatabase ="INSERT INTO `"+matcher.group(4)+"` (`studentID`) VALUES ('"+OptionsWindow.userIDFromServer+"');";
               s.executeUpdate(insertToDatabase);
           }
           order+="\"";

           int count =selectedCourseList.size();
           String finalOrder = "INSERT INTO `student_course_data` (`studentID`, `semesterName`, `courses`, `totalCredit`, `CourseCount`) VALUES ('"+OptionsWindow.userIDFromServer+"', '"+jdbc.semesterNameSession+"', '"+order+"', '"+total+"', '"+count+"')";
           s.executeUpdate(finalOrder);
           sleected_course.setText("");
           msg.setText("Submitted");
           total = 0;
           for (int i = 0; i<courseOfferTable.getItems().size(); i++) {
               courseOfferTable.getItems().clear();
           }
           courseCodeEnlisted ="";
           selectedCourseList.clear();

       }

    }


}
