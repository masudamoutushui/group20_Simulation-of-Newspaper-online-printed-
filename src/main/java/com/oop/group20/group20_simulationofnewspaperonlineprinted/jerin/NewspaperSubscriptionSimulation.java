package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; // 1. Add the missing import for Scene
import javafx.stage.Stage;
import java.io.IOException;

public class NewspaperSubscriptionSimulation extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Use the absolute path to your main login FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(NewspaperSubscriptionSimulation.class.getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/LogIn.fxml"));

        // 2. Use the fxmlLoader to load the FXML and create the root element
        Parent root = fxmlLoader.load();

        // 3. Create the Scene using the root element
        Scene scene = new Scene(root);

        stage.setTitle("Newspaper System Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}