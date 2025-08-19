package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
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
    private ComboBox<String> categoryComboBox;

    @FXML
    private DatePicker publishDatePicker;

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
        // Set ComboBox items for categories
        categoryComboBox.setItems(FXCollections.observableArrayList(
                "Entertainment", "Politics", "Economics", "News", "Health", "Weather"
        ));

        addButton.setOnAction(event -> {
            if (validateInput()) {
                String publishDate = publishDatePicker.getValue() != null
                        ? publishDatePicker.getValue().toString()
                        : "";

                Article newArticle = new Article(
                        idField.getText().trim(),
                        titleField.getText().trim(),
                        authorField.getText().trim(),
                        categoryComboBox.getValue(),
                        publishDate,
                        contentArea.getText().trim()
                );

                mainController.addArticle(newArticle);
                closeWindow();
            }
        });
    }

    private boolean validateInput() {

        String newId = idField.getText().trim();
        boolean idExists = mainController.getArticles().stream()
                .anyMatch(a -> a.getId().equals(newId));

        if (idExists){
            showAlert("error","id should be unique");
            return false;
        }

        if (idField.getText().trim().isEmpty() ||
                titleField.getText().trim().isEmpty() ||
                authorField.getText().trim().isEmpty() ||
                categoryComboBox.getValue() == null ||
                publishDatePicker.getValue() == null ||
                contentArea.getText().trim().isEmpty()) {

            showAlert("Validation Error", "Please fill in all fields.");
            return false;
        }





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
