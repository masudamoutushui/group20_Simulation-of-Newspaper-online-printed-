package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorInChief {

    /**
     * Loads the given FXML scene when a goal button is clicked.
     */
    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            System.out.println("Loading FXML: /" + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Editor-in-Chief Goal View");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML: /" + fxmlPath);
            e.printStackTrace();
        }
    }


    // 🔹 Goal 1: Review Submitted Articles
    @FXML
    private void handleGoal1(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml", event);
    }

    // 🔹 Goal 2: Schedule Articles
    @FXML
    private void handleGoal2(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal2_ScheduleArticles.fxml", event);
    }

    // 🔹 Goal 3: Ethical Checks
    @FXML
    private void handleGoal3(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal3_EthicalCheck.fxml", event);
    }

    // 🔹 Goal 4: Editorial Calendar
    @FXML
    private void handleGoal4(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal4_EditorialCalendar.fxml", event);
    }

    // 🔹 Goal 5: Handle Revisions
    @FXML
    private void handleGoal5(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal5_HandleRevisions.fxml", event);
    }

    // 🔹 Goal 6: Approval History
    @FXML
    private void handleGoal6(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal6_ApprovalHistory.fxml", event);
    }

    // 🔹 Goal 7: Communicate with Team
    @FXML
    private void handleGoal7(ActionEvent event) {
        loadScene("com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Goal7_Communicate.fxml", event);
    }

    // 🔹 Goal 8: Logout or Close
    @FXML
    private void handleGoal8(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close the application window
    }
}
