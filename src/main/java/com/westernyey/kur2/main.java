// Main.java
package com.westernyey.kur2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class  main extends Application {

    // Main.java
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/sample.fxml")));

        primaryStage.setTitle("Кино для вас");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
