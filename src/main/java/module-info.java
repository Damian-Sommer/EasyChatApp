module ch.aelgict.easychatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens ch.aelgict.easychatapp.view to javafx.fxml;
    exports ch.aelgict.easychatapp;
}