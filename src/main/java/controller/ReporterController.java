package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class ReporterController {

    @FXML
    private TextField articleTitleField;

    @FXML
    private TextArea articleContentArea;

    @FXML
    private DatePicker publishDatePicker;

    @FXML
    private TextField referenceField;

    @FXML
    private TableView<Article> articleTable;

    @FXML
    private TableColumn<Article, String> articleTitleCol;

    @FXML
    private TableColumn<Article, LocalDate> submissionDateCol;

    @FXML
    private TableColumn<Article, String> approvalStatusCol;

    private final ObservableList<Article> articleList = FXCollections.observableArrayList();

    private Article selectedArticle = null;

    @FXML
    public void initialize() {
        articleTitleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        submissionDateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        approvalStatusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        articleTable.setItems(articleList);
    }

    @FXML
    private void submitArticleBtn() {
        String title = articleTitleField.getText();
        String content = articleContentArea.getText();
        LocalDate date = publishDatePicker.getValue();
        String reference = referenceField.getText();

        if (title.isEmpty() || content.isEmpty() || date == null) {
            showAlert("Error", "Please fill all required fields.");
            return;
        }

        Article newArticle = new Article(title, date, "Pending", content, reference);
        articleList.add(newArticle);
        clearForm();
    }

    @FXML
    private void editArticleBtn() {
        selectedArticle = articleTable.getSelectionModel().getSelectedItem();
        if (selectedArticle == null) {
            showAlert("Error", "Please select an article to edit.");
            return;
        }

        articleTitleField.setText(selectedArticle.getTitle());
        articleContentArea.setText(selectedArticle.getContent());
        publishDatePicker.setValue(selectedArticle.getDate());
        referenceField.setText(selectedArticle.getReference());
    }

    @FXML
    private void resubmitArticleBtn() {
        if (selectedArticle == null) {
            showAlert("Error", "Please select an article to resubmit.");
            return;
        }

        selectedArticle.setTitle(articleTitleField.getText());
        selectedArticle.setContent(articleContentArea.getText());
        selectedArticle.setDate(publishDatePicker.getValue());
        selectedArticle.setReference(referenceField.getText());
        selectedArticle.setStatus("Resubmitted");

        articleTable.refresh();
        selectedArticle = null;
        clearForm();
    }

    @FXML
    private void switchToPhotojournalistBtn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/photojournalist.fxml"));
            Stage stage = (Stage) articleTable.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        articleTitleField.clear();
        articleContentArea.clear();
        publishDatePicker.setValue(null);
        referenceField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
