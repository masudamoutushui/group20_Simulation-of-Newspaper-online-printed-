package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddArticleDialogController {

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField publishDateField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button addButton;

    private Goal1Controller mainController;

    public void setMainController(Goal1Controller controller) {
        this.mainController = controller;
    }

    @FXML
    private void initialize() {
        addButton.setOnAction(event -> {
            if (validateInput()) {
                Article newArticle = new Article(
                        idField.getText().trim(),
                        titleField.getText().trim(),
                        authorField.getText().trim(),
                        categoryField.getText().trim(),
                        publishDateField.getText().trim(),
                        contentArea.getText().trim()
                );

                mainController.addArticle(newArticle);
                closeWindow();
            }
        });
    }

    private boolean validateInput() {
        if (idField.getText().trim().isEmpty() ||
                titleField.getText().trim().isEmpty() ||
                authorField.getText().trim().isEmpty() ||
                categoryField.getText().trim().isEmpty() ||
                publishDateField.getText().trim().isEmpty() ||
                contentArea.getText().trim().isEmpty()) {

            showAlert("Validation Error", "Please fill in all fields.");
            return false;
        }

        // You can add more validation here (e.g., date format)

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}
