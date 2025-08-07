package com.oop.group20.group20_simulationofnewspaperonlineprinted;

import com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz.UserDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogInController {

    @javafx.fxml.FXML
    private TextField passwordInpu;

    @javafx.fxml.FXML
    private TextField usernameInput;

    @javafx.fxml.FXML
    private ComboBox<String> userTypeinpu;

    private ArrayList<User> userList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        userTypeinpu.getItems().addAll(
                "Readers (Online and Printed)",
                "Advertisement",
                "Journalist",
                "Reporter",
                "Editor-in-chief",
                "Security and System Administrator",
                "Subscription Manager",
                "Payment Gateway Representative"
        );
    }

    @javafx.fxml.FXML
    // Change registerOnAction to open the registration scene:
    public void registerOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("User Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open registration form.");
        }
    }

    @javafx.fxml.FXML
    public void loginOnAction(ActionEvent actionEvent) {
        String username = usernameInput.getText().trim();
        String password = passwordInpu.getText().trim();
        String userType = userTypeinpu.getValue();

        if (username.isEmpty() || password.isEmpty() || userType == null) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in all fields.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7) continue; // skip malformed lines

                String name = parts[0].trim();
                String fileUsername = parts[1].trim();
                String filePassword = parts[2].trim();
                String fileUserType = parts[3].trim();
                String address = parts[4].trim();
                String dob = parts[5].trim();
                String joiningDate = parts[6].trim();

                if (fileUsername.equals(username) &&
                        filePassword.equals(password) &&
                        fileUserType.equalsIgnoreCase(userType.trim())) {

                    // Match found – load user details
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/UserDetails.fxml"));
                    Parent root = loader.load();

                    UserDetailsController controller = loader.getController();
                    controller.setUserData(name, fileUsername, fileUserType, dob, joiningDate, address);

                    Stage stage = (Stage) usernameInput.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("User Details");
                    stage.show();
                    return;
                }
            }

            // No match found
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, password, or user type.");
            usernameInput.clear();
            passwordInpu.clear();
            userTypeinpu.setValue(null);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "File Error", "Failed to read user data.");
        }
    }


    @javafx.fxml.FXML
    public void logoutOnAction(ActionEvent actionEvent) {
        // Implement if needed
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadDashboardScene(String userType) {
        String fxmlFile;

        switch (userType) {
            case "Readers (Online and Printed)":
                fxmlFile = "/fxml/reader_dashboard.fxml";
                break;
            case "Advertisement":
                fxmlFile = "/fxml/advertisement_dashboard.fxml";
                break;
            case "Journalist":
                fxmlFile = "/fxml/journalist_dashboard.fxml";
                break;
            case "Reporter":
                fxmlFile = "/fxml/reporter_dashboard.fxml";
                break;
            case "Editor-in-chief":
                fxmlFile = "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/editor-in-chief.fxml";
                break;
            case "Security and System Administrator":
                fxmlFile = "/fxml/security_dashboard.fxml";
                break;
            case "Subscription Manager":
                fxmlFile = "/fxml/subscription_dashboard.fxml";
                break;
            case "Payment Gateway Representative":
                fxmlFile = "/fxml/payment_dashboard.fxml";
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Scene Load Error", "No dashboard found for this user type.");
                return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) usernameInput.getScene().getWindow(); // get current stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(userType + " Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load dashboard: " + e.getMessage());
        }
    }


    public static class User {
        private final String username;
        private final String password;
        private final String userType;

        public User(String username, String password, String userType) {
            this.username = username;
            this.password = password;
            this.userType = userType;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getUserType() { return userType; }

        @Override
        public String toString() {
            return username + " (" + userType + ")";
        }
    }
}
