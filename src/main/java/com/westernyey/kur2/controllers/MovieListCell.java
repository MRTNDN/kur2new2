package com.westernyey.kur2.controllers;

import com.westernyey.kur2.models.Movie;
import javafx.scene.control.ListCell;

public class MovieListCell extends ListCell<Movie> {

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
        } else {
            setText(movie.getTitle() + " - " + movie.getDescription());
        }
    }
}
