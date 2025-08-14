package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin; // Make sure this package is correct

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class NewspaperSubscriptionSimulation extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // This should point to the FXML file that uses your LogInController
        FXMLLoader fxmlLoader = new FXMLLoader(NewspaperSubscriptionSimulation.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Newspaper System Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}