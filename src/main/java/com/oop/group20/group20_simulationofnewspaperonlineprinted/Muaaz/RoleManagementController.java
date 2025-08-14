package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RoleManagementController {

    // TableView and columns
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> fullNameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Role> roleColumn;
    @FXML
    private TableColumn<User, Boolean> activeColumn;

    // Form fields
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<Role> roleComboBox;
    @FXML
    private CheckBox activeCheckBox;

    // Data list
    private final ObservableList<User> users = FXCollections.observableArrayList();

    private int nextId = 1;

    @FXML
    public void initialize() {
        // Configure table columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        activeColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty());

        // Populate role ComboBox
        roleComboBox.setItems(FXCollections.observableArrayList(Role.values()));

        // Bind table to data list
        userTableView.setItems(users);

        // Listener to populate form when a user is selected
        userTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) populateForm(newSelection);
        });

        // Add some dummy users
        users.addAll(
                new User(nextId++, "Alice Reporter", "alice", "pass123", Role.REPORTER, true),
                new User(nextId++, "Bob Sub Manager", "bob", "pass123", Role.SUBSCRIPTION_MANAGER, true),
                new User(nextId++, "Carol Editor-in-Chief", "carol", "pass123", Role.EDITOR_IN_CHIEF, true)
        );
    }

    private void populateForm(User user) {
        fullNameField.setText(user.getFullName());
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        roleComboBox.setValue(user.getRole());
        activeCheckBox.setSelected(user.isActive());
    }

    @FXML
    private void handleSaveUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            // Create new user
            User user = new User(nextId++, fullNameField.getText(), usernameField.getText(),
                    passwordField.getText(), roleComboBox.getValue(), activeCheckBox.isSelected());
            users.add(user);
        } else {
            // Edit existing user
            selectedUser.setFullName(fullNameField.getText());
            selectedUser.setUsername(usernameField.getText());
            selectedUser.setPassword(passwordField.getText());
            selectedUser.setRole(roleComboBox.getValue());
            selectedUser.setActive(activeCheckBox.isSelected());
        }
        clearForm();
    }

    @FXML
    private void handleDeactivateUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setActive(false);
            clearForm();
        }
    }

    private void clearForm() {
        fullNameField.clear();
        usernameField.clear();
        passwordField.clear();
        roleComboBox.setValue(null);
        activeCheckBox.setSelected(true);
        userTableView.getSelectionModel().clearSelection();
    }
}
