package com.westernyey.kur2.controllers;

import com.westernyey.kur2.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = AuthService.authenticate(username, password);

        if (isAuthenticated) {
            try {
                // загрузка baseScene.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/baseScene.fxml"));
                Parent root = loader.load();

                // создание новой сцены
                Scene scene = new Scene(root);

                // получение текущего stage
                Stage stage = (Stage) loginButton.getScene().getWindow();

                // установка новой сцены
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText(" не правильно!");
        }
    }
    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isRegistered = AuthService.register(username, password);

        if (isRegistered) {
            statusLabel.setText("Registered successfully!");
        } else {
            statusLabel.setText("Registration failed!");
        }
    }

}