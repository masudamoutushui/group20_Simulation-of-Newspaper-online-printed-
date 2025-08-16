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

public class ContentModerationController {

    @FXML private TableView<Article> tableviewOfArticle;
    @FXML private TableColumn<Article, String> idCol;
    @FXML private TableColumn<Article, String> titileCol;
    @FXML private TableColumn<Article, String> AuthorCol;
    @FXML private TableColumn<Article, String> CatagoryCol;
    @FXML private TableColumn<Article, String> pulishDateCol;

    @FXML private TextArea FullcontentOftheselectedArticle;
    @FXML private TextArea commentinputfortheselectedArticle;

    @FXML private TableView<ArticleComment> tableViewWithComment;
    @FXML private TableColumn<ArticleComment, String> idColumn;
    @FXML private TableColumn<ArticleComment, String> titleColoumn;
    @FXML private TableColumn<ArticleComment, String> authorColumn;
    @FXML private TableColumn<ArticleComment, String> Catagory;
    @FXML private TableColumn<ArticleComment, String> PublishDateColoumn;
    @FXML private TableColumn<ArticleComment, String> commentColumn;

    @FXML private TextArea textareaforcommentoftheselectedArticle;
    @FXML private Button keepButton;
    @FXML private Button deleteButton;

    private final String PUBLISHED_FILE = "published.bin";
    private final String PUBLISHED_COMMENT_FILE = "publishedcomment.bin";

    private ObservableList<Article> publishedArticles = FXCollections.observableArrayList();
    private ObservableList<ArticleComment> publishedComments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up published articles table
        idCol.setCellValueFactory(data -> data.getValue().idProperty());
        titileCol.setCellValueFactory(data -> data.getValue().titleProperty());
        AuthorCol.setCellValueFactory(data -> data.getValue().authorProperty());
        CatagoryCol.setCellValueFactory(data -> data.getValue().categoryProperty());
        pulishDateCol.setCellValueFactory(data -> data.getValue().publishDateProperty());
        tableviewOfArticle.setItems(publishedArticles);

        // Set up published comments table
        idColumn.setCellValueFactory(data -> data.getValue().getArticle().idProperty());
        titleColoumn.setCellValueFactory(data -> data.getValue().getArticle().titleProperty());
        authorColumn.setCellValueFactory(data -> data.getValue().getArticle().authorProperty());
        Catagory.setCellValueFactory(data -> data.getValue().getArticle().categoryProperty());
        PublishDateColoumn.setCellValueFactory(data -> data.getValue().getArticle().publishDateProperty());
        commentColumn.setCellValueFactory(data -> data.getValue().commentProperty());
        tableViewWithComment.setItems(publishedComments);

        loadPublishedArticles();
        loadPublishedComments();

        // Show full content on article selection
        tableviewOfArticle.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                FullcontentOftheselectedArticle.setText(newSel.getContent());
            } else {
                FullcontentOftheselectedArticle.clear();
            }
        });

        // Show comment on comment table selection
        tableViewWithComment.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                textareaforcommentoftheselectedArticle.setText(newSel.getComment());
            } else {
                textareaforcommentoftheselectedArticle.clear();
            }
        });
    }

    @FXML
    private void SaveOnAction() {
        Article selected = tableviewOfArticle.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Please select an article to comment on!");
            return;
        }

        String comment = commentinputfortheselectedArticle.getText().trim();
        if (comment.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Comment cannot be empty!");
            return;
        }

        ArticleComment ac = new ArticleComment(selected, comment);
        publishedComments.add(ac);
        savePublishedComments();
        commentinputfortheselectedArticle.clear();
    }

    @FXML
    private void handleKeepAsIs() {
        // No changes, just reload the text area for safety
        ArticleComment selected = tableViewWithComment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            textareaforcommentoftheselectedArticle.setText(selected.getComment());
        }
    }

    @FXML
    private void handleDeleteOnlyComment() {
        ArticleComment selected = tableViewWithComment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            publishedComments.remove(selected);
            savePublishedComments();
            textareaforcommentoftheselectedArticle.clear();
        }
    }

    private void loadPublishedArticles() {
        publishedArticles.clear();
        File file = new File(PUBLISHED_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                for (Object item : (List<?>) obj) {
                    if (item instanceof Article) publishedArticles.add((Article) item);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadPublishedComments() {
        publishedComments.clear();
        File file = new File(PUBLISHED_COMMENT_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                for (Object item : (List<?>) obj) {
                    if (item instanceof ArticleComment) publishedComments.add((ArticleComment) item);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void savePublishedComments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PUBLISHED_COMMENT_FILE))) {
            oos.writeObject(new ArrayList<>(publishedComments));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to save comments!");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void NextOnActiion(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Announcement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void PreviousOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/AdpartnerManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
