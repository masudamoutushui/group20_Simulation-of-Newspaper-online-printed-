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

public class Goaltwo {

    private static final String APPROVED_FILE = "approved.bin";
    private static final String SCHEDULED_FILE = "scheduled.bin";
    private static final String PUBLISHED_FILE = "published.bin";

    @FXML
    private TableView<Article> approvedTable;

    @FXML
    private TableColumn<Article, String> idCol;
    @FXML
    private TableColumn<Article, String> titleCol;
    @FXML
    private TableColumn<Article, String> authorCol;

    @FXML
    private DatePicker publicationDatePicker;

    @FXML
    private ComboBox<String> mediumComboBox;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private Button scheduleButton;

    @FXML
    private TextArea articleContentArea;
    @FXML
    private TableView<ScheduledArticle> scheduleTable;

    @FXML
    private TableColumn<ScheduledArticle, String> schedIdCol;
    @FXML
    private TableColumn<ScheduledArticle, String> schedTitleCol;
    @FXML
    private TableColumn<ScheduledArticle, String> schedDateCol;
    @FXML
    private TableColumn<ScheduledArticle, String> schedMediumCol;
    @FXML
    private TableColumn<ScheduledArticle, String> schedPriorityCol;



    private RegisteredUser user;

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    @FXML
    private Button publishNowButton;

    private ObservableList<Article> approvedArticles = FXCollections.observableArrayList();
    private ObservableList<ScheduledArticle> scheduledArticles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        approvedTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean selected = (newSel != null);
            publishNowButton.setDisable(!selected);
        });

        // Set up columns for approved articles
        idCol.setCellValueFactory(cell -> cell.getValue().idProperty());
        titleCol.setCellValueFactory(cell -> cell.getValue().titleProperty());
        authorCol.setCellValueFactory(cell -> cell.getValue().authorProperty());

        // Set up columns for scheduled articles
        schedIdCol.setCellValueFactory(cell -> cell.getValue().getArticle().idProperty());
        schedTitleCol.setCellValueFactory(cell -> cell.getValue().getArticle().titleProperty());
        schedDateCol.setCellValueFactory(cell -> cell.getValue().publicationDateProperty());
        schedMediumCol.setCellValueFactory(cell -> cell.getValue().mediumProperty());
        schedPriorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());

        approvedTable.setItems(approvedArticles);
        scheduleTable.setItems(scheduledArticles);

        // Load approved and scheduled articles from binary files
        loadApprovedArticles();
        loadScheduledArticles();

        publicationDatePicker.setDisable(true);
        mediumComboBox.setDisable(true);
        priorityComboBox.setDisable(true);
        scheduleButton.setDisable(true);

        mediumComboBox.setItems(FXCollections.observableArrayList("Print", "Online"));
        priorityComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));

        approvedTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean selected = (newSel != null);
            publicationDatePicker.setDisable(!selected);
            mediumComboBox.setDisable(!selected);
            priorityComboBox.setDisable(!selected);
            scheduleButton.setDisable(!selected);

            if (selected) {
                articleContentArea.setText(newSel.getContent());
            } else {
                articleContentArea.clear();
            }
        });
    }

    private void loadApprovedArticles() {
        approvedArticles.clear();
        File file = new File(APPROVED_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                for (Object item : list) {
                    if (item instanceof Article) {
                        approvedArticles.add((Article) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveApprovedArticles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPROVED_FILE))) {
            oos.writeObject(new ArrayList<>(approvedArticles));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScheduledArticles() {
        scheduledArticles.clear();
        File file = new File(SCHEDULED_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                for (Object item : list) {
                    if (item instanceof ScheduledArticle) {
                        scheduledArticles.add((ScheduledArticle) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveScheduledArticles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCHEDULED_FILE))) {
            oos.writeObject(new ArrayList<>(scheduledArticles));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleScheduleArticle() {
        Article selected = approvedTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (publicationDatePicker.getValue() == null) {
            showAlert("Validation Error", "Please select a publication date.");
            return;
        }
        if (mediumComboBox.getValue() == null) {
            showAlert("Validation Error", "Please select a medium (Print/Online).");
            return;
        }
        if (priorityComboBox.getValue() == null) {
            showAlert("Validation Error", "Please select a priority.");
            return;
        }

        String pubDate = publicationDatePicker.getValue().toString();
        String medium = mediumComboBox.getValue();
        String priority = priorityComboBox.getValue();

        ScheduledArticle sa = new ScheduledArticle(selected, pubDate, medium, priority);
        scheduledArticles.add(sa);
        approvedArticles.remove(selected);

        saveScheduledArticles();
        saveApprovedArticles();

        approvedTable.getSelectionModel().clearSelection();
        publicationDatePicker.setValue(null);
        mediumComboBox.setValue(null);
        priorityComboBox.setValue(null);
        publicationDatePicker.setDisable(true);
        mediumComboBox.setDisable(true);
        priorityComboBox.setDisable(true);
        scheduleButton.setDisable(true);
    }

    @FXML
    private void handlePublishNow() {
        Article selected = approvedTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select an article to publish.");
            return;
        }

        List<Article> publishedList = new ArrayList<>();
        File file = new File(PUBLISHED_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof List<?>) {
                    for (Object item : (List<?>) obj) {
                        if (item instanceof Article) {
                            publishedList.add((Article) item);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        boolean alreadyPublished = publishedList.stream()
                .anyMatch(a -> a.getId().equals(selected.getId()));
        if (!alreadyPublished) {
            publishedList.add(selected);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PUBLISHED_FILE))) {
                oos.writeObject(publishedList);
                showAlert("Success", "Article published successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to publish the article.");
            }
        } else {
            showAlert("Info", "This article is already published.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleGoal1() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml");
    }

    @FXML
    private void handleGoal3() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal3.fxml");
    }

    @FXML
    private void handleGoal4() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal4.fxml");
    }

    @FXML
    private void handleGoal5() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal5.fxml");
    }

    @FXML
    private void handleGoal6() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal6.fxml");
    }

    @FXML
    private void handleGoal7() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal7.fxml");
    }

    @FXML
    private void handleGoal8() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal8.fxml");
    }

    @FXML
    private void handleLoginLogout() throws IOException {
        switchScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Log in.fxml");
    }

    private void switchScene(String fxmlFileName) throws IOException {
        Stage stage = (Stage) scheduleButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backOnAction(ActionEvent event) throws IOException {
        if (user == null) {
            System.err.println("Error: No logged-in user to pass back.");
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        UserDetailsController.openWithUser(user, stage);
    }

    @Deprecated
    public void nextOnAction(ActionEvent actionEvent) {
    }
}
