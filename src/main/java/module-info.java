module com.example.speiseplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires layout;


    opens com.example.speiseplan to javafx.fxml;
    //exports com.example.speiseplan.Playground;
    exports com.example.speiseplan.playground;
    opens com.example.speiseplan.playground to javafx.fxml;
}