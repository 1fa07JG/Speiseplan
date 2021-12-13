module com.example.speiseplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires layout;
    requires io;
    requires java.desktop;


    opens com.example.speiseplan to javafx.fxml;
    //exports com.example.speiseplan.Playground;

    exports com.example.speiseplan.logic;
    opens com.example.speiseplan.logic to javafx.fxml;

    exports com.example.speiseplan.output;
    opens com.example.speiseplan.output to javafx.fxml;

    exports com.example.speiseplan.playground;
    opens com.example.speiseplan.playground to javafx.fxml;
}