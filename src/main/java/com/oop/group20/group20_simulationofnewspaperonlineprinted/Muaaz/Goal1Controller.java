package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Goal1Controller {

    @FXML private TableView<ArticleData> articleTable;
    @FXML private TableColumn<ArticleData, String> idCol;
    @FXML private TableColumn<ArticleData, String> titleCol;
    @FXML private TableColumn<ArticleData, String> authorCol;
    @FXML private TableColumn<ArticleData, String> categoryCol;
    @FXML private TableColumn<ArticleData, String> dateCol;
    @FXML private TextArea articleContent;
    @FXML private TextArea editorComment;
    @FXML private Button approveBtn;
    @FXML private Button rejectBtn;
    @FXML private Button addArticleBtn;
    @FXML private Label actionMessage;
   @FXML private Button backButton;

    private ObservableList<ArticleData> submittedArticles;
    private final File dataFile = new File("articles.txt");

    @FXML
    private void initialize() {
        // Initialize table columns
        idCol.setCellValueFactory(data -> data.getValue().idProperty());
        titleCol.setCellValueFactory(data -> data.getValue().titleProperty());
        authorCol.setCellValueFactory(data -> data.getValue().authorProperty());
        categoryCol.setCellValueFactory(data -> data.getValue().categoryProperty());
        dateCol.setCellValueFactory(data -> data.getValue().publishDateProperty());

        // Load data from file
        try {
            List<ArticleData> loaded = ArticleData.loadArticlesFromFile(dataFile);
            submittedArticles = FXCollections.observableArrayList(loaded);
        } catch (IOException e) {
            submittedArticles = FXCollections.observableArrayList();
            e.printStackTrace();
        }

        articleTable.setItems(submittedArticles);

        // Select article listener
        articleTable.setOnMouseClicked((MouseEvent event) -> {
            ArticleData selected = articleTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                articleContent.setText(selected.getContent());
                editorComment.clear();
                actionMessage.setText("");
            }
        });

        // Add new article button
        addArticleBtn.setOnAction(e -> showAddArticleDialog());
    }

    @FXML
    private void handleApprove() {
        ArticleData selected = articleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("✅ Approved: " + selected.getId());
            actionMessage.setText("✅ Article approved.");
        } else {
            actionMessage.setText("⚠️ Please select an article.");
        }
    }

    @FXML
    private void handleReject() {
        ArticleData selected = articleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("❌ Rejected: " + selected.getId());
            actionMessage.setText("❌ Article rejected.");
        } else {
            actionMessage.setText("⚠️ Please select an article.");
        }
    }

    private void showAddArticleDialog() {
        Dialog<ArticleData> dialog = new Dialog<>();
        dialog.setTitle("Add New Article");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        TextField idInput = new TextField();
        TextField titleInput = new TextField();
        TextField authorInput = new TextField();
        TextField categoryInput = new TextField();
        DatePicker dateInput = new DatePicker(LocalDate.now());
        TextArea contentInput = new TextArea();

        idInput.setPromptText("ID");
        titleInput.setPromptText("Title");
        authorInput.setPromptText("Author");
        categoryInput.setPromptText("Category");
        contentInput.setPromptText("Content");
        contentInput.setPrefRowCount(5);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("ID:"), 0, 0);         grid.add(idInput, 1, 0);
        grid.add(new Label("Title:"), 0, 1);      grid.add(titleInput, 1, 1);
        grid.add(new Label("Author:"), 0, 2);     grid.add(authorInput, 1, 2);
        grid.add(new Label("Category:"), 0, 3);   grid.add(categoryInput, 1, 3);
        grid.add(new Label("Publish Date:"), 0, 4); grid.add(dateInput, 1, 4);
        grid.add(new Label("Content:"), 0, 5);    grid.add(contentInput, 1, 5);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new ArticleData(
                        idInput.getText(),
                        titleInput.getText(),
                        authorInput.getText(),
                        categoryInput.getText(),
                        dateInput.getValue().toString(),
                        contentInput.getText()
                );
            }
            return null;
        });

        Optional<ArticleData> result = dialog.showAndWait();

        result.ifPresent(article -> {
            submittedArticles.add(article);
            saveArticles();
            actionMessage.setText("✅ New article added.");
        });
    }

    private void saveArticles() {
        try {
            ArticleData.saveArticlesToFile(submittedArticles, dataFile);
        } catch (IOException e) {
            actionMessage.setText("⚠️ Failed to save articles.");
            e.printStackTrace();
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Log in.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load login page.");
        }
    }
}
