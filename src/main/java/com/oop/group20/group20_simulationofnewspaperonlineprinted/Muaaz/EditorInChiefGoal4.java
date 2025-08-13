package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EditorInChiefGoal4 {

    @FXML private TableView<Article> publishedArticlesTable;
    @FXML private TableColumn<Article, String> idCol;
    @FXML private TableColumn<Article, String> titleCol;
    @FXML private TableColumn<Article, String> authorCol;
    @FXML private TableColumn<Article, String> categoryCol;
    @FXML private TableColumn<Article, String> dateCol;

    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private DatePicker publishDatePicker;

    @FXML private Label actionMessage;

    private ObservableList<Article> publishedArticlesList = FXCollections.observableArrayList();
    private final File dataFile = new File("articles.txt"); // Adjust path if needed

    @FXML
    public void initialize() {
        // Bind columns to Article properties
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        loadPublishedArticles();

        publishedArticlesTable.setItems(publishedArticlesList);

        // Populate form fields when a row is selected
        publishedArticlesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void loadPublishedArticles() {
        publishedArticlesList.clear();
        try {
            List<ArticleData> articleDataList = ArticleData.loadArticlesFromFile(dataFile);
            List<Article> articles = articleDataList.stream()
                    .map(ad -> new Article(ad.getId(), ad.getTitle(), ad.getAuthor(), ad.getCategory(), ad.getPublishDate(), ad.getContent()))
                    .collect(Collectors.toList());
            publishedArticlesList.addAll(articles);
            actionMessage.setText("Articles loaded successfully.");
            actionMessage.setStyle("-fx-text-fill: green;");
        } catch (IOException e) {
            e.printStackTrace();
            actionMessage.setText("Failed to load articles from file.");
            actionMessage.setStyle("-fx-text-fill: red;");
        }
    }

    private void savePublishedArticles() {
        try {
            List<ArticleData> articleDataList = publishedArticlesList.stream()
                    .map(a -> new ArticleData(a.getId(), a.getTitle(), a.getAuthor(), a.getCategory(), a.getPublishDate(), a.getContent()))
                    .collect(Collectors.toList());
            ArticleData.saveArticlesToFile(articleDataList, dataFile);
            actionMessage.setText("Changes saved successfully.");
            actionMessage.setStyle("-fx-text-fill: green;");
        } catch (IOException e) {
            e.printStackTrace();
            actionMessage.setText("Failed to save articles.");
            actionMessage.setStyle("-fx-text-fill: red;");
        }
    }

    private void populateFields(Article article) {
        idField.setText(article.getId());
        titleField.setText(article.getTitle());
        authorField.setText(article.getAuthor());
        categoryField.setText(article.getCategory());
        publishDatePicker.setValue(LocalDate.parse(article.getPublishDate()));
        actionMessage.setText("");
    }

    @FXML
    private void handleUpdateArticle() {
        Article selected = publishedArticlesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            actionMessage.setText("Please select an article to update.");
            actionMessage.setStyle("-fx-text-fill: red;");
            return;
        }
        if (titleField.getText().isEmpty() || authorField.getText().isEmpty() || categoryField.getText().isEmpty() || publishDatePicker.getValue() == null) {
            actionMessage.setText("All fields except ID are required.");
            actionMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        // Update article fields
        selected.setTitle(titleField.getText());
        selected.setAuthor(authorField.getText());
        selected.setCategory(categoryField.getText());
        selected.setPublishDate(publishDatePicker.getValue().toString());

        publishedArticlesTable.refresh();
        savePublishedArticles();
        actionMessage.setText("Article updated successfully.");
        actionMessage.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleDeleteArticle() {
        Article selected = publishedArticlesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            actionMessage.setText("Please select an article to delete.");
            actionMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        publishedArticlesList.remove(selected);
        clearFields();
        savePublishedArticles();
        actionMessage.setText("Article deleted successfully.");
        actionMessage.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleClearFields() {
        clearFields();
        actionMessage.setText("");
        publishedArticlesTable.getSelectionModel().clearSelection();
    }

    private void clearFields() {
        idField.clear();
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        publishDatePicker.setValue(null);
    }

    @FXML
    private void handleBack() {
        // Implement navigation logic if needed
        System.out.println("Back clicked");
    }
}
