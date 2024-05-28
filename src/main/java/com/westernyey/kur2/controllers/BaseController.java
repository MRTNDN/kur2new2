
package com.westernyey.kur2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {

    @FXML
    private VBox mainScene;

    @FXML
    private VBox menu;

    @FXML
    private Button goToExitButton;

    @FXML
    private AnchorPane centerPane; // Добавляем AnchorPane для центральной области

   
    private void loadScene(String fxmlFilePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
        Parent root = loader.load();
        centerPane.getChildren().setAll(root); // Заменяем содержимое центральной области новым FXML
    }



    @FXML
    private void showMovieList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MovieList.fxml"));
            Parent root = loader.load();
            centerPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

