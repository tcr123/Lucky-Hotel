module com.example.fop_hotel_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires
            jfoenix;
    requires java.sql;
    requires mysql.connector.java;
    requires java.sql.rowset;
    requires fontawesomefx;
    requires java.desktop;
    requires java.mail;
    opens com.example.fop_hotel_gui to javafx.fxml;
    exports com.example.fop_hotel_gui;
    exports com.example.fop_hotel_gui.Table;

}