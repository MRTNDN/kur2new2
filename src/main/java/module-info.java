module com.westernyey.kur2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;

    opens com.westernyey.kur2.controllers to javafx.fxml;

    exports com.westernyey.kur2;
    opens com.westernyey.kur2.models to javafx.base;
}
