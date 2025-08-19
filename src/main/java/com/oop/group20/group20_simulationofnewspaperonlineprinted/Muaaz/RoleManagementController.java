package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoleManagementController {

    // Table and columns (matching your FXML IDs exactly)
    @FXML
    private TableView<RegisteredUser> userTableView;
    @FXML
    private TableColumn<RegisteredUser, String> usernameCol;
    @FXML
    private TableColumn<RegisteredUser, String> passwordCol;
    @FXML
    private TableColumn<RegisteredUser, String> userTypeCol;
    @FXML
    private TableColumn<RegisteredUser, String> nameCol;
    @FXML
    private TableColumn<RegisteredUser, String> Address;
    @FXML
    private TableColumn<RegisteredUser, LocalDate> dateOfbirthCol;
    @FXML
    private TableColumn<RegisteredUser, LocalDate> joiningDateCol;

    // Form fields
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> usertype;
    @FXML
    private TextField address;
    @FXML
    private TextField name;
    @FXML
    private DatePicker DateOfBirth;
    @FXML
    private DatePicker JoiningDate;







    private final File userFile = new File("users.bin");
    private ObservableList<RegisteredUser> userList = FXCollections.observableArrayList();

    // Logged-in user reference
    private RegisteredUser user;

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

    @Deprecated
    public void NextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/settings.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void PreviousOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/IT Admin.fxml"));
        Parent root = loader.load();

        // Pass the current logged-in user to ITAdminController
        ITAdminController controller = loader.getController();
        controller.setUser(this.user); // pass the logged-in user

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    // Setter for logged-in user (so ITAdminController can pass it here)
    public void setUser(RegisteredUser user) {
        this.user = user;
    }

}
