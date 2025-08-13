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

    private final File userFile = new File("users.txt");

    @FXML
    public void initialize() {
        userTypeComboBox.setItems(FXCollections.observableArrayList(
                "Readers (Online and Printed)",
                "Advertisement",
                "Journalist",
                "Reporter",
                "Editor-in-chief",
                "Security and System Administrator",
                "Subscription Manager",
                "Payment Gateway Representative"
        ));
        userTypeComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void registerOnAction() {
        String username = usernameField.getText().trim();
        String name = nameField.getText().trim();
        String password = passwordField.getText();
        String userType = userTypeComboBox.getValue();
        String address = addressField.getText().trim();
        LocalDate dob = dobPicker.getValue();
        LocalDate joiningDate = joiningDatePicker.getValue();

        if (username.isEmpty() || name.isEmpty() || password.isEmpty() || userType == null ||
                address.isEmpty() || dob == null || joiningDate == null) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        // Check duplicate username from file
        try {
            List<String> existingUsernames = getAllUsernames();
            if (existingUsernames.contains(username.toLowerCase())) {
                messageLabel.setText("Username already exists!");
                return;
            }
        } catch (IOException e) {
            messageLabel.setText("Error reading user data.");
            e.printStackTrace();
            return;
        }

        // Save to file
        // Save to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true))) {
            // Format: username;password;userType;name;address;dob;joiningDate
            String line = String.join(";",
                    username,
                    password,
                    userType,
                    name,
                    address,
                    dob.toString(),
                    joiningDate.toString()
            );
            bw.write(line);
            bw.newLine();
            messageLabel.setText("Registration successful!");
            clearFields();
        } catch (IOException e) {
            messageLabel.setText("Failed to save user.");
            e.printStackTrace();
        }

    }

    private List<String> getAllUsernames() throws IOException {
        List<String> usernames = new ArrayList<>();
        if (!userFile.exists()) return usernames;

        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length >= 1) {
                    usernames.add(parts[0].toLowerCase());
                }
            }
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
        // close window
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
