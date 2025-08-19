package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal1Controller {

    private static final String PENDING_FILE = "articles.bin";
    private static final String REVIEWED_FILE = "reviewed_articles.bin";

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
    private Label actionMessage;
    @FXML
    private Button addArticleBtn;
    @FXML
    private Button backButton;

    private ObservableList<Article> articles;
    private RegisteredUser user;

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

//    /*** NEW STATIC METHOD TO OPEN THIS CONTROLLER SAFELY ***/
//    public static void openWithUser(RegisteredUser user, Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(Goal1Controller.class.getResource(
//                "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml"));
//        Parent root = loader.load();
//        Goal1Controller controller = loader.getController();
//        controller.setUser(user); // Pass the logged-in user here
//        stage.setScene(new Scene(root));
//        stage.setTitle("Review Articles");
//        stage.show();
//    }

    @FXML
    public void initialize() {
        articles = FXCollections.observableArrayList();

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

    @SuppressWarnings("unchecked")
    private void loadPendingArticles() {
        articles.clear();
        File file = new File(PENDING_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Article> list = (List<Article>) ois.readObject();
            articles.addAll(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            setError("Error loading articles.");
        }
    }

    private void savePendingArticles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PENDING_FILE))) {
            oos.writeObject(new ArrayList<>(articles));
        } catch (IOException e) {
            e.printStackTrace();
            setError("Error saving articles.");
        }
    }

    @SuppressWarnings("unchecked")
    private void saveReviewedArticle(Article article, String status, String reason) {
        File file = new File(REVIEWED_FILE);
        List<Article> reviewedList = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reviewedList = (List<Article>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Article reviewedArticle = new Article(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getCategory(),
                article.getPublishDate(),
                article.getContent()
        );
        reviewedArticle.setStatus(status);
        reviewedArticle.setReason(reason);

        reviewedList.add(reviewedArticle);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(reviewedList);
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

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        if (user == null) {
            System.err.println("Error: No logged-in user to pass back.");
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        UserDetailsController.openWithUser(user, stage);
    }


    @Deprecated
    public void NextOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Check.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public ObservableList<Article> getArticles() {
        return articles;
    }
}
