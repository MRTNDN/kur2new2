package com.westernyey.kur2.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.westernyey.kur2.models.Movie;
import com.westernyey.kur2.controllers.DatabaseConnector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class MovieListController {

    @FXML
    private ListView<Movie> movieListView;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Инициализация списка фильмов
        movieListView.setItems(movies);
        movieListView.setCellFactory(param -> new MovieListCell()); // Класс для отображения ячейки списка
    }

    @FXML
    public void openAddMovieDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Добавление нового фильма");
        dialog.setHeaderText("Введите данные о новом фильме");

        // Создаем текстовые поля для ввода данных о фильме
        TextField titleTextField = new TextField();
        TextField dateTextField = new TextField();
        TextField timeTextField = new TextField();
        titleTextField.setPromptText("Название фильма");
        dateTextField.setPromptText("Дата фильма");
        timeTextField.setPromptText("Время фильма");

        // Создаем сетку для размещения элементов в диалоговом окне
        GridPane gridPane = new GridPane();
        gridPane.add(titleTextField, 0, 0);
        gridPane.add(dateTextField, 0, 1);
        gridPane.add(timeTextField, 0, 2);
        dialog.getDialogPane().setContent(gridPane);

        // Добавляем кнопки OK и Cancel
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Обработчик нажатия кнопки OK
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String title = titleTextField.getText();
                String date = dateTextField.getText();
                String time = timeTextField.getText();

                // Добавляем новый фильм в базу данных и список
                addMovieToDatabase(title, date, time);
            }
            return null;
        });

        // Отображаем диалоговое окно и ждем результата
        Optional<ButtonType> result = dialog.showAndWait();
    }

    private void addMovieToDatabase(String title, String date, String time) {
        Connection connection = null;
        try {
            // Получаем соединение с базой данных
            connection = DatabaseConnector.connect();

            // Подготовка SQL запроса для вставки данных о фильме
            String sql = "INSERT INTO movies(title, date, time) VALUES (?, ?, ?)";

            // Подготовка оператора PreparedStatement для выполнения SQL запроса
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, time);

            // Выполнение SQL запроса
            preparedStatement.executeUpdate();

            // Закрытие оператора PreparedStatement
            preparedStatement.close();

            // Добавление фильма в список movies
            movies.add(new Movie(title, date + " " + time));
        } catch (SQLException e) {
            System.out.println("Error adding movie to database: " + e.getMessage());
        } finally {
            try {
                // В любом случае закрываем соединение с базой данных
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    @FXML
    public void deleteSelectedMovieFromDatabase() {
        // Ваш код для удаления выбранного фильма из базы данных
        // После удаления фильма из базы данных, удалите его из списка movies
        Movie selectedMovie = movieListView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            movies.remove(selectedMovie);
        }
    }
}
