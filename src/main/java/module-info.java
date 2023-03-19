module ch.aelgict.easychatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;
    requires java.net.http;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires json.simple;

    opens ch.aelgict.easychatapp.view to javafx.fxml;
    exports ch.aelgict.easychatapp;
}