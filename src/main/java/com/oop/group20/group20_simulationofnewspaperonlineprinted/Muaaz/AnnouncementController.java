package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.concurrent.atomic.AtomicInteger;

public class AnnouncementController {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea messageArea;
    @FXML
    private ComboBox<String> towhomCombo;
    @FXML
    private ComboBox<String> urgencyCombo;
    @FXML
    private Button sendButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<Announcement> historyTable;
    @FXML
    private TableColumn<Announcement, Number> AnnouncementId;
    @FXML
    private TableColumn<Announcement, String> MessageCol;
    @FXML
    private TableColumn<Announcement, String> urgencycol;
    @FXML
    private TableColumn<Announcement, String> towhomcol;
    @FXML
    private ComboBox<String> towhomCombofil;

    private ObservableList<Announcement> announcements = FXCollections.observableArrayList();
    private FilteredList<Announcement> filteredAnnouncements;
    private AtomicInteger idCounter = new AtomicInteger(1);

    @FXML
    public void initialize() {
        // Setup combo box options
        towhomCombo.setItems(FXCollections.observableArrayList(
                "All Users", "Journalists", "Editors", "Subscription Managers", "Editor-in-chief"
        ));
        urgencyCombo.setItems(FXCollections.observableArrayList("Low", "Medium", "High", "Critical"));

        // Setup table columns
        AnnouncementId.setCellValueFactory(data -> data.getValue().idProperty());
        MessageCol.setCellValueFactory(data -> data.getValue().messageProperty());
        urgencycol.setCellValueFactory(data -> data.getValue().urgencyProperty());
        towhomcol.setCellValueFactory(data -> data.getValue().toWhomProperty());

        // Add dummy announcements
        announcements.addAll(
                new Announcement(idCounter.getAndIncrement(), "Server maintenance tonight at 11 PM", "High", "All Users"),
                new Announcement(idCounter.getAndIncrement(), "Monthly budget report available", "Medium", "Subscription Managers"),
                new Announcement(idCounter.getAndIncrement(), "Urgent: Breaking news policy update", "Critical", "Journalists"),
                new Announcement(idCounter.getAndIncrement(), "Weekly editorial meeting moved to Friday", "Low", "Editors")
        );

        // Wrap list in a filtered list
        filteredAnnouncements = new FilteredList<>(announcements, p -> true);
        historyTable.setItems(filteredAnnouncements);

        // Setup filter combo
        towhomCombofil.setItems(FXCollections.observableArrayList(
                "All", "All Users", "Journalists", "Editors", "Subscription Managers"
        ));
        towhomCombofil.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.equals("All")) {
                filteredAnnouncements.setPredicate(a -> true);
            } else {
                filteredAnnouncements.setPredicate(a -> a.getToWhom().equals(newVal));
            }
        });

        // Row click listener to load data into input form
        historyTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Split title and message if they were stored together
                String fullMessage = newSelection.getMessage();
                String title = fullMessage;
                String body = "";

                if (fullMessage.contains(":")) {
                    int idx = fullMessage.indexOf(":");
                    title = fullMessage.substring(0, idx).trim();
                    body = fullMessage.substring(idx + 1).trim();
                }

                titleField.setText(title);
                messageArea.setText(body);
                towhomCombo.setValue(newSelection.getToWhom());
                urgencyCombo.setValue(newSelection.getUrgency());
            }
        });
    }

    @FXML
    private void handleSendAnnouncement() {
        String title = titleField.getText().trim();
        String message = messageArea.getText().trim();
        String toWhom = towhomCombo.getValue();
        String urgency = urgencyCombo.getValue();

        if (title.isEmpty() || message.isEmpty() || toWhom == null || urgency == null) {
            showAlert(Alert.AlertType.ERROR, "Missing Data", "Please fill out all fields.");
            return;
        }

        Announcement newAnnouncement = new Announcement(
                idCounter.getAndIncrement(),
                title + ": " + message,
                urgency,
                toWhom
        );
        announcements.add(newAnnouncement);
        clearForm();
    }

    @FXML
    private void handleClearForm() {
        clearForm();
    }

    private void clearForm() {
        titleField.clear();
        messageArea.clear();
        towhomCombo.getSelectionModel().clearSelection();
        urgencyCombo.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
