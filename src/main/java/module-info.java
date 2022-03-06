/**
 *
 */
module com.example.erp_university {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.erp_university to javafx.fxml;
    exports com.example.erp_university;
}