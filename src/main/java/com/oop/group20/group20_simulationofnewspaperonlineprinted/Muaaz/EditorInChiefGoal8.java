package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EditorInChiefGoal8 {

    @FXML private ComboBox<String> userFilterComboBox;
    @FXML private ComboBox<String> statusFilterComboBox;
    @FXML private ComboBox<String> articleIdFilterComboBox;
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private TableView<ArticleLogEntry> logsTable;

    @FXML private TableColumn<ArticleLogEntry, String> articleIdCol;
    @FXML private TableColumn<ArticleLogEntry, String> actionCol;
    @FXML private TableColumn<ArticleLogEntry, String> performedByCol;
    @FXML private TableColumn<ArticleLogEntry, String> roleCol;
    @FXML private TableColumn<ArticleLogEntry, String> timestampCol;
    @FXML private TableColumn<ArticleLogEntry, String> remarksCol;

    private ObservableList<ArticleLogEntry> allLogs = FXCollections.observableArrayList();

    private static final String APPROVED_FILE = "approved.bin";
    private static final String SCHEDULED_FILE = "scheduled.bin";
    private static final String PUBLISHED_FILE = "published.bin";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // adjust format if needed

    @FXML
    public void initialize() {
        // Setup table columns with property values
        articleIdCol.setCellValueFactory(cell -> cell.getValue().articleIdProperty());
        actionCol.setCellValueFactory(cell -> cell.getValue().actionProperty());
        performedByCol.setCellValueFactory(cell -> cell.getValue().performedByProperty());
        roleCol.setCellValueFactory(cell -> cell.getValue().roleProperty());
        timestampCol.setCellValueFactory(cell -> cell.getValue().timestampProperty());
        remarksCol.setCellValueFactory(cell -> cell.getValue().remarksProperty());

        // Load all articles and create logs
        loadAllLogs();

        // Populate filter ComboBoxes with unique values from loaded logs
        populateFilterOptions();

        logsTable.setItems(allLogs);
    }

    private void loadAllLogs() {
        allLogs.clear();

        List<Article> approvedArticles = loadArticlesFromFile(APPROVED_FILE);
        List<ScheduledArticle> scheduledArticles = loadScheduledArticlesFromFile(SCHEDULED_FILE);
        List<Article> publishedArticles = loadArticlesFromFile(PUBLISHED_FILE);

        // Add approved (pending) articles logs
        for (Article a : approvedArticles) {
            allLogs.add(new ArticleLogEntry(a.getId(), "Approved (Pending)", "System", "Editor", a.getPublishDate(), "No remarks"));
        }

        // Add scheduled articles logs
        for (ScheduledArticle sa : scheduledArticles) {
            allLogs.add(new ArticleLogEntry(sa.getArticle().getId(), "Scheduled", "Scheduler", "Editor", sa.getPublicationDate(), sa.getPriority()));
        }

        // Add published articles logs
        for (Article a : publishedArticles) {
            allLogs.add(new ArticleLogEntry(a.getId(), "Published", "Publisher", "Editor", a.getPublishDate(), "Published successfully"));
        }
    }

    private List<Article> loadArticlesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<Article> articles = new ArrayList<>();
                for (Object item : (List<?>) obj) {
                    if (item instanceof Article) {
                        articles.add((Article) item);
                    }
                }
                return articles;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<ScheduledArticle> loadScheduledArticlesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<ScheduledArticle> scheduledArticles = new ArrayList<>();
                for (Object item : (List<?>) obj) {
                    if (item instanceof ScheduledArticle) {
                        scheduledArticles.add((ScheduledArticle) item);
                    }
                }
                return scheduledArticles;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void populateFilterOptions() {
        // Populate user filter
        var users = allLogs.stream().map(ArticleLogEntry::getPerformedBy).distinct().sorted().toList();
        userFilterComboBox.setItems(FXCollections.observableArrayList(users));

        // Populate status filter (actions)
        var statuses = allLogs.stream().map(ArticleLogEntry::getAction).distinct().sorted().toList();
        statusFilterComboBox.setItems(FXCollections.observableArrayList(statuses));

        // Populate articleId filter
        var articleIds = allLogs.stream().map(ArticleLogEntry::getArticleId).distinct().sorted().toList();
        articleIdFilterComboBox.setItems(FXCollections.observableArrayList(articleIds));
    }

    @FXML
    private void handleSearch() {
        String selectedUser = userFilterComboBox.getValue();
        String selectedStatus = statusFilterComboBox.getValue();
        String selectedArticleId = articleIdFilterComboBox.getValue();
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        ObservableList<ArticleLogEntry> filtered = allLogs.filtered(log -> {
            boolean matchesUser = (selectedUser == null || selectedUser.isEmpty()) || log.getPerformedBy().equals(selectedUser);
            boolean matchesStatus = (selectedStatus == null || selectedStatus.isEmpty()) || log.getAction().equals(selectedStatus);
            boolean matchesArticleId = (selectedArticleId == null || selectedArticleId.isEmpty()) || log.getArticleId().equals(selectedArticleId);

            LocalDate logDate = parseDateSafe(log.getTimestamp());
            boolean matchesFromDate = (fromDate == null) || (logDate != null && (logDate.isEqual(fromDate) || logDate.isAfter(fromDate)));
            boolean matchesToDate = (toDate == null) || (logDate != null && (logDate.isEqual(toDate) || logDate.isBefore(toDate)));

            return matchesUser && matchesStatus && matchesArticleId && matchesFromDate && matchesToDate;
        });

        logsTable.setItems(filtered);
    }

    private LocalDate parseDateSafe(String dateStr) {
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null; // handle invalid format gracefully
        }
    }

    @FXML
    private void handleExportPDF() {
        // Implement PDF export logic here
    }

    @FXML
    private void handleExportCSV() {
        // Implement CSV export logic here
    }
}
