package com.example.movieticketbookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MovieTicket extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTicket.class.getResource("MovieTicketFXML.fxml"));

        javafx.scene.Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 600, 409 );
        stage.setTitle("Movie Ticket Booking");

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getScreenX();
            y = event.getScreenY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
