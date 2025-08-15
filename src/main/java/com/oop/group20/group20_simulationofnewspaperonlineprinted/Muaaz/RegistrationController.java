package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {

    @FXML private TextField usernameField;
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> userTypeComboBox;
    @FXML private TextField addressField;
    @FXML private DatePicker dobPicker;
    @FXML private DatePicker joiningDatePicker;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        userTypeComboBox.setItems(FXCollections.observableArrayList(
                "Readers (Online and Printed)",
                "Advertisement",
                "Journalist",
                "Reporter",
                "Editor-in-chief",
                "Security and System Administrator",
                "Subscriber Manager",
                "Subscription Manager",
                "Payment Gateway Representative"
        ));
        userTypeComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private final File userFile = new File("users.bin");

    @FXML
    public void registerOnAction() {
        String username = usernameField.getText().trim();
        String name = nameField.getText().trim();
        String password = passwordField.getText();
        String userType = userTypeComboBox.getValue();
        String address = addressField.getText().trim();
        LocalDate dob = dobPicker.getValue();
        LocalDate joiningDate = joiningDatePicker.getValue();

        // ------------------ VALIDATION ------------------
        if (username.isEmpty() || name.isEmpty() || password.isEmpty() || userType == null ||
                address.isEmpty() || dob == null || joiningDate == null) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if (username.length() < 4 || username.length() > 15) {
            messageLabel.setText("Username must be 4-15 characters.");
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            messageLabel.setText("Name can only contain letters and spaces.");
            return;
        }

        if (password.length() < 6) {
            messageLabel.setText("Password must be at least 6 characters.");
            return;
        }

        if (!address.matches("[a-zA-Z0-9 ,.-]+")) {
            messageLabel.setText("Address contains invalid characters.");
            return;
        }

        if (dob.isAfter(LocalDate.now())) {
            messageLabel.setText("Date of birth cannot be in the future.");
            return;
        }

        if (joiningDate.isAfter(LocalDate.now())) {
            messageLabel.setText("Joining date cannot be in the future.");
            return;
        }
        // ------------------------------------------------

        try {
            List<RegisteredUser> users = readAllUsers();
            List<String> usernames = getAllUsernames();

            if (usernames.contains(username.toLowerCase())) {
                messageLabel.setText("Username already exists!");
                return;
            }

            // Save new user
            users.add(new RegisteredUser(username, password, userType, name, address, dob, joiningDate));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
                oos.writeObject(users);
            }

            // DEBUG: Verify saved users
            System.out.println("=== Saved Users ===");
            List<RegisteredUser> savedUsers = readAllUsers();
            for (RegisteredUser u : savedUsers) {
                System.out.println("Username: " + u.getUsername() + ", UserType: " + u.getUserType());
            }
            System.out.println("==================");

            messageLabel.setText("Registration successful!");
            clearFields();
        } catch (IOException | ClassNotFoundException e) {
            messageLabel.setText("Failed to save user.");
            e.printStackTrace();
        }
    }

    private List<RegisteredUser> readAllUsers() throws IOException, ClassNotFoundException {
        if (!userFile.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            List<RegisteredUser> users = (List<RegisteredUser>) ois.readObject();
            // DEBUG: print all users when reading
            System.out.println("=== Reading Users from File ===");
            for (RegisteredUser u : users) {
                System.out.println(u.getUsername() + " | " + u.getUserType());
            }
            System.out.println("==============================");
            return users;
        }
    }

    private List<String> getAllUsernames() throws IOException, ClassNotFoundException {
        List<String> usernames = new ArrayList<>();
        if (!userFile.exists()) return usernames;

        List<RegisteredUser> users = readAllUsers();
        for (RegisteredUser user : users) {
            usernames.add(user.getUsername().toLowerCase());
        }
        return usernames;
    }

    private void clearFields() {
        usernameField.clear();
        nameField.clear();
        passwordField.clear();
        userTypeComboBox.getSelectionModel().selectFirst();
        addressField.clear();
        dobPicker.setValue(null);
        joiningDatePicker.setValue(null);
    }

    @FXML
    public void cancelOnAction() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
