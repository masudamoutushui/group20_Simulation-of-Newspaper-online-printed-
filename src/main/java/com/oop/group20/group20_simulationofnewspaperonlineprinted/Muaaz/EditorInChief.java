package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class EditorInChief {

    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Editor-in-Chief Goal View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoal1(ActionEvent event) {
        loadScene("Goal1_ReviewArticles.fxml", event);
    }

    @FXML
    private void handleGoal2(ActionEvent event) {
        loadScene("Goal2_ScheduleArticles.fxml", event);
    }

    @FXML
    private void handleGoal3(ActionEvent event) {
        loadScene("Goal3_EthicalCheck.fxml", event);
    }

    @FXML
    private void handleGoal4(ActionEvent event) {
        loadScene("Goal4_EditorialCalendar.fxml", event);
    }

    @FXML
    private void handleGoal5(ActionEvent event) {
        loadScene("Goal5_HandleRevisions.fxml", event);
    }

    @FXML
    private void handleGoal6(ActionEvent event) {
        loadScene("Goal6_ApprovalHistory.fxml", event);
    }

    @FXML
    private void handleGoal7(ActionEvent event) {
        loadScene("Goal7_Communicate.fxml", event);
    }

    @FXML
    private void handleGoal8(ActionEvent event) {
        // You can redirect to login or close the stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
