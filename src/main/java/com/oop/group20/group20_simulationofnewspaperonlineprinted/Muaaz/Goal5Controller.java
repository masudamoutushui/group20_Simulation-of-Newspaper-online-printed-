package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Goal5Controller {

    @FXML
    private TableView<EditorialMember> teamMemberTable;
    @FXML
    private TableColumn<EditorialMember, String> nameCol;
    @FXML
    private TableColumn<EditorialMember, String> roleCol;
    @FXML
    private TableColumn<EditorialMember, String> statusCol;
    @FXML
    private TableColumn<EditorialMember, String> assignmentsCol;

    @FXML
    private Label detailsNameLabel;
    @FXML
    private Label detailsIdLabel;
    @FXML
    private Label detailsRoleLabel;
    @FXML
    private Label detailsEmailLabel;
    @FXML
    private Label detailsHireDateLabel;

    @FXML
    private ListView<String> memberArticlesList;

    @FXML
    private Button sendMessageButton;
    @FXML
    private Button updateStatusButton;
    @FXML
    private Button backButton;

    private ObservableList<EditorialMember> members = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Setup TableView columns
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        roleCol.setCellValueFactory(cell -> cell.getValue().roleProperty());
        statusCol.setCellValueFactory(cell -> cell.getValue().statusProperty());
        assignmentsCol.setCellValueFactory(cell -> cell.getValue().activeAssignmentsProperty());

        // Load dummy data
        loadDummyData();

        // Bind data to TableView
        teamMemberTable.setItems(members);

        // Disable buttons initially
        sendMessageButton.setDisable(true);
        updateStatusButton.setDisable(true);

        // Clear details initially
        clearDetails();

        // Add selection listener
        teamMemberTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                updateDetails(newSel);
                sendMessageButton.setDisable(false);
                updateStatusButton.setDisable(false);
            } else {
                clearDetails();
                sendMessageButton.setDisable(true);
                updateStatusButton.setDisable(true);
            }
        });
    }

    private void loadDummyData() {
        members.clear();
        members.addAll(
                new EditorialMember("E001", "Alice Johnson", "Reporter", "Active", "alice.johnson@example.com", "2022-01-15", "3"),
                new EditorialMember("E002", "Bob Smith", "Journalist", "On Leave", "bob.smith@example.com", "2021-05-30", "0"),
                new EditorialMember("E003", "Cathy Lee", "Editor", "Active", "cathy.lee@example.com", "2019-09-20", "5"),
                new EditorialMember("E004", "David Kim", "Reporter", "Active", "david.kim@example.com", "2020-11-11", "2")
        );
    }

    private void updateDetails(EditorialMember member) {
        detailsNameLabel.setText(member.getName());
        detailsIdLabel.setText(member.getId());
        detailsRoleLabel.setText(member.getRole());
        detailsEmailLabel.setText(member.getEmail());
        detailsHireDateLabel.setText(member.getHireDate());

        // Dummy current assignments — replace with real data loading
        ObservableList<String> assignments = FXCollections.observableArrayList(
                "Article 1: Climate Change Impact",
                "Article 2: Election Coverage",
                "Article 3: Technology Advances"
        );

        // Show fewer or empty assignments if "0"
        if ("0".equals(member.getActiveAssignments())) {
            memberArticlesList.setItems(FXCollections.observableArrayList());
        } else {
            memberArticlesList.setItems(assignments);
        }
    }

    private void clearDetails() {
        detailsNameLabel.setText("Select a Team Member");
        detailsIdLabel.setText("N/A");
        detailsRoleLabel.setText("N/A");
        detailsEmailLabel.setText("N/A");
        detailsHireDateLabel.setText("N/A");
        memberArticlesList.getItems().clear();
    }

    @FXML
    private void handleSendMessage() {
        EditorialMember selected = teamMemberTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Send Message");
        dialog.setHeaderText("Send Message to " + selected.getName());
        dialog.setContentText("Enter your message:");

        dialog.showAndWait().ifPresent(message -> {
            if (!message.trim().isEmpty()) {
                // Simulate sending the message, e.g., print or call backend
                System.out.println("Message sent to " + selected.getEmail() + ": " + message);
                // You can add real sending logic here
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message Sent");
                alert.setHeaderText(null);
                alert.setContentText("Message successfully sent to " + selected.getName());
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void handleUpdateStatus() {
        EditorialMember selected = teamMemberTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        ChoiceDialog<String> dialog = new ChoiceDialog<>(selected.getStatus(), "Active", "On Leave", "Inactive");
        dialog.setTitle("Update Status");
        dialog.setHeaderText("Update Status for " + selected.getName());
        dialog.setContentText("Select new status:");

        dialog.showAndWait().ifPresent(newStatus -> {
            if (!newStatus.equals(selected.getStatus())) {
                // Update status in model
                selected.statusProperty().set(newStatus);
                // Refresh table and details pane
                teamMemberTable.refresh();
                updateDetails(selected);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Status Updated");
                alert.setHeaderText(null);
                alert.setContentText(selected.getName() + "'s status updated to " + newStatus);
                alert.showAndWait();
            }
        });
    }


    @FXML
    private void handleBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal4.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void NextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal6.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}