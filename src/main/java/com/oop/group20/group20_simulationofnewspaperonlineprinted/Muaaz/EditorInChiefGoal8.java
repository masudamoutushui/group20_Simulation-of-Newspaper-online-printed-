package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EditorInChiefGoal8 {

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

    private RegisteredUser user;

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML private ComboBox userFilterComboBox;

    @FXML
    public void initialize() {
        articleIdCol.setCellValueFactory(cell -> cell.getValue().articleIdProperty());
        actionCol.setCellValueFactory(cell -> cell.getValue().actionProperty());
        performedByCol.setCellValueFactory(cell -> cell.getValue().performedByProperty());
        roleCol.setCellValueFactory(cell -> cell.getValue().roleProperty());
        timestampCol.setCellValueFactory(cell -> cell.getValue().timestampProperty());
        remarksCol.setCellValueFactory(cell -> cell.getValue().remarksProperty());

        loadAllLogs();
        populateFilterOptions();
        logsTable.setItems(allLogs);
    }

    private void loadAllLogs() {
        allLogs.clear();

        List<Article> approvedArticles = loadArticlesFromFile(APPROVED_FILE);
        List<ScheduledArticle> scheduledArticles = loadScheduledArticlesFromFile(SCHEDULED_FILE);
        List<Article> publishedArticles = loadArticlesFromFile(PUBLISHED_FILE);

        for (Article a : approvedArticles) {
            allLogs.add(new ArticleLogEntry(a.getId(), "Approved (Pending)", "System", "Editor", a.getPublishDate(), "No remarks"));
        }

        for (ScheduledArticle sa : scheduledArticles) {
            allLogs.add(new ArticleLogEntry(sa.getArticle().getId(), "Scheduled", "Scheduler", "Editor", sa.getPublicationDate(), sa.getPriority()));
        }

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
                    if (item instanceof Article) articles.add((Article) item);
                }
                return articles;
            }
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
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
                    if (item instanceof ScheduledArticle) scheduledArticles.add((ScheduledArticle) item);
                }
                return scheduledArticles;
            }
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return new ArrayList<>();
    }

    private void populateFilterOptions() {
        var users = allLogs.stream().map(ArticleLogEntry::getPerformedBy).distinct().sorted().toList();
        userFilterComboBox.setItems(FXCollections.observableArrayList(users));

        var statuses = allLogs.stream().map(ArticleLogEntry::getAction).distinct().sorted().toList();
        statusFilterComboBox.setItems(FXCollections.observableArrayList(statuses));

        var articleIds = allLogs.stream().map(ArticleLogEntry::getArticleId).distinct().sorted().toList();
        articleIdFilterComboBox.setItems(FXCollections.observableArrayList(articleIds));
    }

    @FXML
    private void handleSearch() {
        String selectedUser = (String) userFilterComboBox.getValue();
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
        try { return LocalDate.parse(dateStr, formatter); }
        catch (DateTimeParseException e) { return null; }
    }

    // ------------------ EXPORT CSV ------------------
    @FXML
    private void handleExportCSV() {
        if (logsTable.getItems().isEmpty()) {
            showAlert("Export CSV", "No data to export.");
            return;
        }

        File file = new File("ArticleLogs.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            // Write header
            writer.println("Article ID,Action,Performed By,Role,Timestamp,Remarks");
            // Write rows
            for (ArticleLogEntry log : logsTable.getItems()) {
                writer.printf("%s,%s,%s,%s,%s,%s%n",
                        log.getArticleId(),
                        log.getAction(),
                        log.getPerformedBy(),
                        log.getRole(),
                        log.getTimestamp(),
                        log.getRemarks()
                );
            }
            showAlert("Export CSV", "CSV exported successfully to " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            showAlert("Export CSV", "Error exporting CSV.");
            e.printStackTrace();
        }
    }

    // ------------------ EXPORT PDF (JavaFX PrinterJob) ------------------
    @FXML
    private void handleExportPDF() {
        if (logsTable.getItems().isEmpty()) {
            showAlert("Export PDF", "No data to export.");
            return;
        }

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            showAlert("Export PDF", "No printers found.");
            return;
        }

        boolean proceed = job.showPrintDialog(logsTable.getScene().getWindow());
        if (proceed) {
            boolean success = job.printPage(logsTable);
            if (success) {
                job.endJob();
                showAlert("Export PDF", "Table sent to printer successfully. Use 'Save as PDF' in printer dialog if available.");
            } else {
                showAlert("Export PDF", "Failed to print the table.");
            }
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    public void PreviousOnAction(ActionEvent event) throws IOException {
        if (user == null) {
            System.err.println("Error: No logged-in user to pass back.");
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        UserDetailsController.openWithUser(user, stage);
    }
}
