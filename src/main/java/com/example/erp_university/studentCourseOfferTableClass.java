package com.example.erp_university;

public class studentCourseOfferTableClass {
    private  String courseCode;
    private  String courseName;
    private  String courseCredit;
    private  String courseUniqueID;
    public studentCourseOfferTableClass(String courseCode, String courseName, String courseCredit, String courseUniqueID) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.courseUniqueID = courseUniqueID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseUniqueID() {
        return courseUniqueID;
    }

    public void setCourseUniqueID(String courseUniqueID) {
        this.courseUniqueID = courseUniqueID;
    }
}
