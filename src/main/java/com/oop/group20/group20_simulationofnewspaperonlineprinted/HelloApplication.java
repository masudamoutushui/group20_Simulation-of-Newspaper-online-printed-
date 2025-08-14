package com.oop.group20.group20_simulationofnewspaperonlineprinted;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/AnalyticsDashboardAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

