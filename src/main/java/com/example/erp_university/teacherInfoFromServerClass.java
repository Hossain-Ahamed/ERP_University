package com.example.erp_university;

public class teacherInfoFromServerClass {
    private  String teacherID;
    private  String teacherName;
    private  String teacherDepartment;
    public teacherInfoFromServerClass(String teacherID, String teacherName, String teacherDepartment) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherDepartment = teacherDepartment;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }
}
