package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.nio.file.*;

public class Goaltwo {

    private static final String REVIEWED_FILE = "reviewed_articles.txt";
    private static final String SCHEDULED_FILE = "scheduled_articles.txt";

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

    private ObservableList<Article> approvedArticles = FXCollections.observableArrayList();
    private ObservableList<ScheduledArticle> scheduledArticles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
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

        // Load approved articles from reviewed_articles.txt (status == "Approved")
        loadApprovedArticles();

        // Load scheduled articles from file
        loadScheduledArticles();

        // Disable controls initially
        publicationDatePicker.setDisable(true);
        mediumComboBox.setDisable(true);
        priorityComboBox.setDisable(true);
        scheduleButton.setDisable(true);

        // Fill comboBoxes
        mediumComboBox.setItems(FXCollections.observableArrayList("Print", "Online"));
        priorityComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));

        // Listen for selection
        approvedTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean selected = (newSel != null);
            publicationDatePicker.setDisable(!selected);
            mediumComboBox.setDisable(!selected);
            priorityComboBox.setDisable(!selected);
            scheduleButton.setDisable(!selected);
        });


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
        Path path = Paths.get(REVIEWED_FILE);
        if (!Files.exists(path)) return;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: id|title|author|category|publishDate|content|status|reason
                String[] parts = line.split("\\|", 8);
                if (parts.length >= 7) {
                    String status = parts[6];
                    if ("Approved".equalsIgnoreCase(status.trim())) {
                        Article a = new Article(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts.length == 8 ? parts[7] : "");
                        approvedArticles.add(a);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // You can add UI error handling here
        }
    }

    private void loadScheduledArticles() {
        scheduledArticles.clear();
        Path path = Paths.get(SCHEDULED_FILE);
        if (!Files.exists(path)) return;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: id|title|author|category|publishDate|content|status|reason|publicationDate|medium|priority
                String[] parts = line.split("\\|", 11);
                if (parts.length == 11) {
                    Article a = new Article(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                    ScheduledArticle sa = new ScheduledArticle(a, parts[8], parts[9], parts[10]);
                    scheduledArticles.add(sa);
                }
            }
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

        // Clear selection and controls
        approvedTable.getSelectionModel().clearSelection();
        publicationDatePicker.setValue(null);
        mediumComboBox.setValue(null);
        priorityComboBox.setValue(null);
        publicationDatePicker.setDisable(true);
        mediumComboBox.setDisable(true);
        priorityComboBox.setDisable(true);
        scheduleButton.setDisable(true);
    }

    private void saveScheduledArticles() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SCHEDULED_FILE))) {
            for (ScheduledArticle sa : scheduledArticles) {
                Article a = sa.getArticle();
                String safeContent = a.getContent().replace("\n", " ").replace("|", "/");
                String safeReason = a.getReason().replace("\n", " ").replace("|", "/");
                bw.write(String.join("|",
                        a.getId(),
                        a.getTitle(),
                        a.getAuthor(),
                        a.getCategory(),
                        a.getPublishDate(),
                        safeContent,
                        a.getStatus(),
                        safeReason,
                        sa.getPublicationDate(),
                        sa.getMedium(),
                        sa.getPriority()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveApprovedArticles() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(REVIEWED_FILE))) {
            for (Article a : approvedArticles) {
                String safeContent = a.getContent().replace("\n", " ").replace("|", "/");
                String safeReason = a.getReason().replace("\n", " ").replace("|", "/");
                bw.write(String.join("|",
                        a.getId(),
                        a.getTitle(),
                        a.getAuthor(),
                        a.getCategory(),
                        a.getPublishDate(),
                        safeContent,
                        a.getStatus(),
                        safeReason
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
