package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditorInChiefGoal3 {

    @FXML
    private TableView<PublishingSchedule> scheduleTable;

    @FXML
    private TableColumn<PublishingSchedule, String> scheduleArticleCol;
    @FXML
    private TableColumn<PublishingSchedule, String> scheduleDateCol;
    @FXML
    private TableColumn<PublishingSchedule, String> scheduleEditionCol;
    @FXML
    private TableColumn<PublishingSchedule, String> scheduleStatusCol;

    @FXML
    private TextField articleTitleInput;

    @FXML
    private DatePicker publishDatePicker;

    @FXML
    private ComboBox<String> editionComboBox;

    @FXML
    private Label scheduleMessage;

    private ObservableList<PublishingSchedule> scheduleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns
        scheduleArticleCol.setCellValueFactory(cellData -> cellData.getValue().articleTitleProperty());
        scheduleDateCol.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());
        scheduleEditionCol.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
        scheduleStatusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Load existing schedule data (dummy for now)
        loadSchedule();

        scheduleTable.setItems(scheduleList);
    }

    private void loadSchedule() {
        // TODO: fetch from DB; dummy data for now
        scheduleList.clear();
        scheduleList.addAll(
                new PublishingSchedule("Climate Change Impact", "2025-08-15", "Print", "Scheduled"),
                new PublishingSchedule("Tech Advances in AI", "2025-08-16", "Online", "Scheduled"),
                new PublishingSchedule("Global Economy Trends", "2025-08-17", "Print", "Scheduled")
        );
    }

    @FXML
    private void handleSaveSchedule() {
        String articleTitle = articleTitleInput.getText();
        if (publishDatePicker.getValue() == null || articleTitle == null || articleTitle.isEmpty() || editionComboBox.getValue() == null) {
            scheduleMessage.setText("Please fill all fields.");
            scheduleMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        String date = publishDatePicker.getValue().toString();
        String edition = editionComboBox.getValue();

        // For simplicity, status always Scheduled
        PublishingSchedule newSchedule = new PublishingSchedule(articleTitle, date, edition, "Scheduled");

        // TODO: Add DB save logic here

        scheduleList.add(newSchedule);
        scheduleMessage.setText("Schedule saved successfully!");
        scheduleMessage.setStyle("-fx-text-fill: green;");

        // Clear inputs
        articleTitleInput.clear();
        publishDatePicker.setValue(null);
        editionComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        // TODO: Implement navigation to previous scene or dashboard
        System.out.println("Back clicked");
    }
}
