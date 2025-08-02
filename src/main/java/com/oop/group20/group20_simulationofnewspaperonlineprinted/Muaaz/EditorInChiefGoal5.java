

package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class EditorInChiefGoal5 {
    @FXML private TextArea announcementTextArea;
    @FXML private ComboBox<String> audienceComboBox;
    @FXML private VBox announcementsList;

    @FXML
    private void initialize() {
        audienceComboBox.getItems().addAll("All", "Editors", "Reporters");
        loadAnnouncements();
    }

    private void loadAnnouncements() {

        System.out.println("Loading announcements...");
    }

    @FXML
    private void handlePostAnnouncement() {
        String message = announcementTextArea.getText();
        String audience = audienceComboBox.getValue();

        if (message == null || message.trim().isEmpty() || audience == null) {
            // Show alert
            System.out.println("Please complete all fields");
            return;
        }
        // TODO: Save announcement to DB and refresh list
        System.out.println("Posting announcement to " + audience);
    }
}
