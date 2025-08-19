package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PublishedArticleManagement {

    private static final String PUBLISHED_FILE = "published.bin";

    @FXML
    private TableView<Article> publishedcontentTableview;
    @FXML
    private TableColumn<Article, String> IdCol;
    @FXML
    private TableColumn<Article, String> titleCol;
    @FXML
    private TableColumn<Article, String> AuthorCol;
    @FXML
    private TableColumn<Article, String> CatagoryCol;
    @FXML
    private TableColumn<Article, String> PublishDateCol;

    @FXML
    private TextArea fullContentofselectedArticle;

    @FXML
    private Label totalArctlebycatagoryouput;


    // Logged-in user reference
    private RegisteredUser user;


    // Setter for logged-in user
    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    private final ObservableList<Article> publishedArticles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Setup Table Columns
        IdCol.setCellValueFactory(data -> data.getValue().idProperty());
        titleCol.setCellValueFactory(data -> data.getValue().titleProperty());
        AuthorCol.setCellValueFactory(data -> data.getValue().authorProperty());
        CatagoryCol.setCellValueFactory(data -> data.getValue().categoryProperty());
        PublishDateCol.setCellValueFactory(data -> data.getValue().publishDateProperty());

        // Load data from file
        loadPublishedArticles();

        // Set table data
        publishedcontentTableview.setItems(publishedArticles);

        // Show article content on row selection
        publishedcontentTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                fullContentofselectedArticle.setText(newSel.getContent());
            } else {
                fullContentofselectedArticle.clear();
            }
        });
    }

    private void loadPublishedArticles() {
        publishedArticles.clear();
        File file = new File(PUBLISHED_FILE);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                for (Object item : (List<?>) obj) {
                    if (item instanceof Article) {
                        publishedArticles.add((Article) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // --- Button Actions ---
    @FXML
    private void editOnAction() {
        Article selected = publishedcontentTableview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setContent(fullContentofselectedArticle.getText());
            savePublishedArticles();
            showAlert("Success", "Article updated successfully.");
        } else {
            showAlert("Error", "No article selected.");
        }
    }

    @FXML
    private void DeleteOnAction() {
        Article selected = publishedcontentTableview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            publishedArticles.remove(selected);
            savePublishedArticles();
            fullContentofselectedArticle.clear();
            showAlert("Deleted", "Article deleted successfully.");
        } else {
            showAlert("Error", "No article selected.");
        }
    }

    @FXML
    private void TotalArticleCountOnAction() {
        // Count articles by category
        var counts = new java.util.HashMap<String, Integer>();
        for (Article article : publishedArticles) {
            counts.put(article.getCategory(), counts.getOrDefault(article.getCategory(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        counts.forEach((cat, count) -> sb.append(cat).append(": ").append(count).append("\n"));
        totalArctlebycatagoryouput.setText(sb.toString());
    }

    private void savePublishedArticles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PUBLISHED_FILE))) {
            oos.writeObject(new ArrayList<>(publishedArticles));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
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


    @Deprecated
    public void nextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/AnalyticsDashboardAdmin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
