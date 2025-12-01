module com.example.cityeventsjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    opens model to javafx.fxml;

    exports app;
    exports controller;
    exports model;

    requires junit;
    opens test to junit;


}
