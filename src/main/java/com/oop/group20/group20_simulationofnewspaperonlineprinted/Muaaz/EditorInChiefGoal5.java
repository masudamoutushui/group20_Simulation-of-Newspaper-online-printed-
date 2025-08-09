package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditorInChiefGoal5 {

    @FXML private TableView<TeamMember> teamTable;
    @FXML private TableColumn<TeamMember, String> idCol;
    @FXML private TableColumn<TeamMember, String> nameCol;
    @FXML private TableColumn<TeamMember, String> roleCol;
    @FXML private TableColumn<TeamMember, String> contactCol;

    @FXML private TextField nameField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private TextField contactField;

    @FXML private Label messageLabel;

    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;

    private ObservableList<TeamMember> teamList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        roleCol.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        contactCol.setCellValueFactory(cellData -> cellData.getValue().contactProperty());

        // Load team members (dummy data for now)
        loadTeamMembers();

        teamTable.setItems(teamList);

        // Restrict roles in ComboBox to only "Editor"
        roleComboBox.setItems(FXCollections.observableArrayList("Editor"));

        // Selection listener to update fields and enable/disable controls based on role
        teamTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);

                boolean isEditor = "Editor".equalsIgnoreCase(newSelection.getRole());

                // Disable fields and buttons if not Editor
                nameField.setDisable(!isEditor);
                roleComboBox.setDisable(!isEditor);
                contactField.setDisable(!isEditor);

                addBtn.setDisable(false); // Always enabled to add Editors
                updateBtn.setDisable(!isEditor);
                deleteBtn.setDisable(!isEditor);

                if (!isEditor) {
                    messageLabel.setText("You can only manage Editors.");
                    messageLabel.setStyle("-fx-text-fill: orange;");
                } else {
                    messageLabel.setText("");
                }
            } else {
                clearFields();
                addBtn.setDisable(false);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                messageLabel.setText("");
                // Enable fields for adding new Editor
                nameField.setDisable(false);
                roleComboBox.setDisable(false);
                contactField.setDisable(false);
            }
        });

        // Initially disable update/delete buttons since no selection
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private void loadTeamMembers() {
        // TODO: Replace with DB/file loading
        teamList.clear();
        teamList.addAll(
                new TeamMember("001", "John Doe", "Editor", "john@example.com"),
                new TeamMember("002", "Jane Smith", "Journalist", "jane@example.com"),
                new TeamMember("003", "Alice Brown", "Reporter", "alice@example.com"),
                new TeamMember("004", "Mark Taylor", "Editor", "mark@example.com")
        );
    }

    private void populateFields(TeamMember member) {
        nameField.setText(member.getName());
        roleComboBox.getSelectionModel().select(member.getRole());
        contactField.setText(member.getContact());
        messageLabel.setText("");
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText().trim();
        String role = roleComboBox.getSelectionModel().getSelectedItem();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || role == null || contact.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!"Editor".equalsIgnoreCase(role)) {
            messageLabel.setText("You can only add Editors.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        String newId = generateNewId();
        TeamMember newMember = new TeamMember(newId, name, role, contact);
        teamList.add(newMember);

        messageLabel.setText("Editor added.");
        messageLabel.setStyle("-fx-text-fill: green;");
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        TeamMember selected = teamTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Please select a member to update.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!"Editor".equalsIgnoreCase(selected.getRole())) {
            messageLabel.setText("You can only update Editors.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        String name = nameField.getText().trim();
        String role = roleComboBox.getSelectionModel().getSelectedItem();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || role == null || contact.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        selected.setName(name);
        selected.setRole(role);
        selected.setContact(contact);

        teamTable.refresh();
        messageLabel.setText("Editor updated.");
        messageLabel.setStyle("-fx-text-fill: green;");
        clearFields();
    }

    @FXML
    private void handleDelete() {
        TeamMember selected = teamTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Please select a member to delete.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!"Editor".equalsIgnoreCase(selected.getRole())) {
            messageLabel.setText("You can only delete Editors.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        teamList.remove(selected);
        messageLabel.setText("Editor deleted.");
        messageLabel.setStyle("-fx-text-fill: green;");
        clearFields();
    }

    @FXML
    private void handleClear() {
        clearFields();
        messageLabel.setText("");
        teamTable.getSelectionModel().clearSelection();

        // Enable fields for adding new Editor
        nameField.setDisable(false);
        roleComboBox.setDisable(false);
        contactField.setDisable(false);

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        addBtn.setDisable(false);
    }

    private void clearFields() {
        nameField.clear();
        roleComboBox.getSelectionModel().clearSelection();
        contactField.clear();
    }

    private String generateNewId() {
        int maxId = 0;
        for (TeamMember m : teamList) {
            try {
                int idNum = Integer.parseInt(m.getId());
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException ignored) {}
        }
        return String.format("%03d", maxId + 1);
    }
}
