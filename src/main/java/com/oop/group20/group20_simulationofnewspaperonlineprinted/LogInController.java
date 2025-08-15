package com.oop.group20.group20_simulationofnewspaperonlineprinted;

import com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz.RegisteredUser;
import com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz.UserDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class LogInController {

    @javafx.fxml.FXML
    private TextField passwordInpu;

    @javafx.fxml.FXML
    private TextField usernameInput;

    @javafx.fxml.FXML
    private ComboBox<String> userTypeinpu;

    private ArrayList<RegisteredUser> userList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        if (userTypeinpu != null) {
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
        } else {
            System.out.println("Warning: userTypeinpu ComboBox is null! Check FXML linkage.");
        }

        loadUsersFromFile();
    }

    @javafx.fxml.FXML
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

        for (RegisteredUser user : userList) {
            if (user.getUsername().equalsIgnoreCase(username) &&
                    user.getPassword().equals(password) &&
                    user.getUserType().equalsIgnoreCase(userType)) {
                loadDashboardScene(user); // <-- pass the user object
                return;
            }
        }

        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, password, or user type.");
    }

    private void loadUsersFromFile() {
        File userFile = new File("users.bin");
        if (!userFile.exists()) {
            System.out.println("Debug: users.bin file does not exist yet.");
            return; // no users yet
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;
                for (Object o : tempList) {
                    if (o instanceof RegisteredUser) {
                        RegisteredUser user = (RegisteredUser) o;
                        userList.add(user);

                        // Debug output: print loaded user details
                        System.out.println("Loaded user -> Username: " + user.getUsername() +
                                ", Name: " + user.getName() +
                                ", UserType: " + user.getUserType() +
                                ", DOB: " + user.getDob() +
                                ", JoiningDate: " + user.getJoiningDate());
                    }
                }
                System.out.println("Debug: Total users loaded = " + userList.size());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user data.");
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

    // ---------------- FIXED ----------------
    private void loadDashboardScene(RegisteredUser user) {
        String userType = user.getUserType();
        String fxmlFile;
        String title;

        switch (userType) {
            case "Readers (Online and Printed)":
                fxmlFile = "/com/oop/group20/group20_simulationofnewspaperonlineprinted/jerin/CustomerView.fxml";
                title = "Customer Subscription View";
                break;
            case "Subscription Manager":
                fxmlFile = "/com/oop/group20/group20_simulationofnewspaperonlineprinted/jerin/MainView.fxml";
                title = "Subscription Manager Dashboard";
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
                fxmlFile = "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/UserDetails.fxml";
                break;
            case "Security and System Administrator":
                fxmlFile = "/fxml/security_dashboard.fxml";
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

            // --- IMPORTANT FIX ---
            if (userType.equals("Editor-in-chief")) {
                UserDetailsController controller = loader.getController();
                controller.setUser(user);
            }

            Stage stage = (Stage) usernameInput.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(userType + " Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load dashboard: " + e.getMessage());
        }
    }
}
