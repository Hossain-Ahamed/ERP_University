package com.example.erp_university;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc {
    static Connection c1; static Statement s;
    static  String semesterNameSession,min_credit,max_credit;
    jdbc(Connection c1, Statement s,String semesterNameSession,String min_credit,String max_credit){
        this.c1 = c1;
        this.s = s;
        this.semesterNameSession = semesterNameSession;
        this.min_credit = min_credit;
        this.max_credit = max_credit;
    }


}
