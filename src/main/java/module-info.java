module lk.ijse.bsms.layered {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;
    requires javafx.base;
    requires jdk.jdi;
    requires javafx.graphics;
//    requires lk.ijse.bsms.layered;
//    requires lk.ijse.bsms.layered;
//    requires lk.ijse.bsms.layered;

//    requires lk.ijse.bsms.layered;
//    requires lk.ijse.bsms.layered;
//    requires lk.ijse.bsms.layered;

    opens lk.ijse.bsms.layered to javafx.fxml;
    exports lk.ijse.bsms.layered;

    opens lk.ijse.bsms.layered.controller to javafx.fxml;
    exports lk.ijse.bsms.layered.dto to javafx.fxml;
    opens lk.ijse.bsms.layered.dto to javafx.base;
    exports lk.ijse.bsms.layered.controller;

    opens lk.ijse.bsms.layered.view.tdm to javafx.base, javafx.fxml;
}
