package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.*;

public class Goal1Controller {

    private static final String PENDING_FILE = "articles.txt"; // pending articles
    private static final String REVIEWED_FILE = "reviewed_articles.txt"; // approved/rejected articles

    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> idCol;
    @FXML
    private TableColumn<Article, String> titleCol;
    @FXML
    private TableColumn<Article, String> authorCol;
    @FXML
    private TableColumn<Article, String> categoryCol;
    @FXML
    private TableColumn<Article, String> dateCol;
    @FXML
    private TextArea articleContent;
    @FXML
    private TextArea editorComment;
    @FXML
    private Button approveBtn;
    @FXML
    private Button rejectBtn;
    @FXML
    private Button backButton;
    @FXML
    private Button addArticleBtn;
    @FXML
    private Label actionMessage;

    private ObservableList<Article> articles;

    @FXML
    public void initialize() {
        articles = FXCollections.observableArrayList();

        // Bind columns to Article properties
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        articleTable.setItems(articles);
        loadPendingArticles();

        approveBtn.setDisable(true);
        rejectBtn.setDisable(true);

        articleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                articleContent.setText(newSel.getContent());
                editorComment.clear();
                setInfo("You are reviewing: \"" + newSel.getTitle() + "\"");
                approveBtn.setDisable(false);
                rejectBtn.setDisable(false);
            } else {
                articleContent.clear();
                editorComment.clear();
                setInfo("Please select an article to view.");
                approveBtn.setDisable(true);
                rejectBtn.setDisable(true);
            }
        });
    }

    private void loadPendingArticles() {
        articles.clear();
        Path path = Paths.get(PENDING_FILE);
        if (!Files.exists(path)) return;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 6);
                if (parts.length == 6) {
                    articles.add(new Article(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            setError("Error loading articles.");
        }
    }

    private void savePendingArticles() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PENDING_FILE))) {
            for (Article a : articles) {
                String safeContent = a.getContent().replace("\n", " ").replace("|", "/");
                writer.write(String.join("|", a.getId(), a.getTitle(), a.getAuthor(),
                        a.getCategory(), a.getPublishDate(), safeContent));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            setError("Error saving articles.");
        }
    }

    private void saveReviewedArticle(Article article, String status, String reason) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(REVIEWED_FILE),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            String safeContent = article.getContent().replace("\n", " ").replace("|", "/");
            String safeReason = reason == null ? "" : reason.replace("\n", " ").replace("|", "/");

            writer.write(String.join("|",
                    article.getId(),
                    article.getTitle(),
                    article.getAuthor(),
                    article.getCategory(),
                    article.getPublishDate(),
                    safeContent,
                    status,
                    safeReason
            ));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            setError("Error saving reviewed article.");
        }
    }

    @FXML
    private void handleApprove() {
        Article selected = articleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            setError("No article selected to approve.");
            return;
        }

        saveReviewedArticle(selected, "Approved", "");
        articles.remove(selected);
        savePendingArticles();

        articleContent.clear();
        editorComment.clear();
        setSuccess("Article \"" + selected.getTitle() + "\" approved!");
    }

    @FXML
    private void handleReject() {
        Article selected = articleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            setError("No article selected to reject.");
            return;
        }

        String reason = editorComment.getText().trim();
        if (reason.isEmpty()) {
            setError("Please provide a rejection reason.");
            return;
        }

        saveReviewedArticle(selected, "Rejected", reason);
        articles.remove(selected);
        savePendingArticles();

        articleContent.clear();
        editorComment.clear();
        setWarning("Article \"" + selected.getTitle() + "\" rejected.");
    }

    @FXML
    private void handleBack() {
        System.out.println("Back button clicked.");
        // Implement navigation logic if required
    }

    @FXML
    private void handleWriteEditorial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/ AddArticleDialog.fxml"));
            Parent root = loader.load();

            AddArticleDialogController dialogController = loader.getController();
            dialogController.setMainController(this);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Article");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            setError("Failed to open Add Article dialog.");
        }
    }

    public void addArticle(Article article) {
        articles.add(article);
        savePendingArticles();
        setInfo("Article \"" + article.getTitle() + "\" added.");
    }

    // Helper methods to set messages and colors
    private void setError(String msg) {
        actionMessage.setText(msg);
        actionMessage.setTextFill(Color.RED);
    }

    private void setSuccess(String msg) {
        actionMessage.setText(msg);
        actionMessage.setTextFill(Color.GREEN);
    }

    private void setWarning(String msg) {
        actionMessage.setText(msg);
        actionMessage.setTextFill(Color.ORANGE);
    }

    private void setInfo(String msg) {
        actionMessage.setText(msg);
        actionMessage.setTextFill(Color.BLUE);
    }
}
