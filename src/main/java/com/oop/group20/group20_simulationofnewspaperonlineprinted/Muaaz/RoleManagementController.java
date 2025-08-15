package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoleManagementController {

    // Table and columns (matching your FXML IDs exactly)
    @FXML private TableView<RegisteredUser> userTableView;
    @FXML private TableColumn<RegisteredUser, String> usernameCol;
    @FXML private TableColumn<RegisteredUser, String> passwordCol;
    @FXML private TableColumn<RegisteredUser, String> userTypeCol;
    @FXML private TableColumn<RegisteredUser, String> nameCol;
    @FXML private TableColumn<RegisteredUser, String> Address;
    @FXML private TableColumn<RegisteredUser, LocalDate> dateOfbirthCol;
    @FXML private TableColumn<RegisteredUser, LocalDate> joiningDateCol;

    // Form fields
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private ComboBox<String> usertype;
    @FXML private TextField address;
    @FXML private TextField name;
    @FXML private DatePicker DateOfBirth;
    @FXML private DatePicker JoiningDate;

    private final File userFile = new File("users.bin");
    private ObservableList<RegisteredUser> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Link table columns to RegisteredUser fields
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        dateOfbirthCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        joiningDateCol.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));

        // Populate ComboBox for user types
        usertype.setItems(FXCollections.observableArrayList(
                "Readers (Online and Printed)",
                "Advertisement",
                "Journalist",
                "Reporter",
                "Editor-in-chief",
                "Security and System Administrator",
                "Subscription Manager",
                "Payment Gateway Representative"
        ));

        // Load data from file
        loadUsersFromFile();
        userTableView.setItems(userList);

        // When selecting a row, fill the form fields
        userTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                username.setText(newSel.getUsername());
                password.setText(newSel.getPassword());
                usertype.setValue(newSel.getUserType());
                address.setText(newSel.getAddress());
                name.setText(newSel.getName());
                DateOfBirth.setValue(newSel.getDob());
                JoiningDate.setValue(newSel.getJoiningDate());
            }
        });
    }

    private void loadUsersFromFile() {
        try {
            if (!userFile.exists()) return;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                List<RegisteredUser> users = (List<RegisteredUser>) ois.readObject();
                userList.setAll(users);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load users from file.");
        }
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            oos.writeObject(new ArrayList<>(userList));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save users to file.");
        }
    }

    @FXML
    private void editOnAction() {
        RegisteredUser selected = userTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a user to edit.");
            return;
        }

        RegisteredUser updatedUser = new RegisteredUser(
                username.getText(),
                password.getText(),
                usertype.getValue(),
                name.getText(),
                address.getText(),
                DateOfBirth.getValue(),
                JoiningDate.getValue()
        );

        int index = userList.indexOf(selected);
        userList.set(index, updatedUser);
        saveUsersToFile();
    }

    @FXML
    private void RemoveOnAction() {
        RegisteredUser selected = userTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a user to remove.");
            return;
        }

        userList.remove(selected);
        saveUsersToFile();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
