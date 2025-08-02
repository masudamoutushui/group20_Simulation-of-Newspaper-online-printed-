


package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Goal1Controller {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label messageLabel;
    @FXML private Button reviewArticlesBtn;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("editor".equals(username) && "password".equals(password)) {
            messageLabel.setText("Login successful!");
            reviewArticlesBtn.setVisible(true);
            loginButton.setDisable(true);
            usernameField.setDisable(true);
            passwordField.setDisable(true);
        } else {
            messageLabel.setText("Invalid credentials");
        }
    }

    @FXML
    private void handleReviewArticles() {
        // TODO: Load submitted articles list scene or open modal
        System.out.println("Load submitted articles");
    }
}
