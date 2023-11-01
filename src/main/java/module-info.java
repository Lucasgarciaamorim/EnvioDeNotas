module com.borges.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.services.sheets;
    requires com.google.api.client.json.gson;


    opens com.borges to javafx.fxml;
    exports com.borges.view;
    opens com.borges.view to javafx.fxml;
}