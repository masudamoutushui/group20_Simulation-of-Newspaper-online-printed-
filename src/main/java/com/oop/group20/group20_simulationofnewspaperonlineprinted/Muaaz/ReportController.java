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
import javafx.print.PrinterJob;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.List;

public class ReportController {

    // ============ FXML Bindings ============
    @FXML private Button previousButton;
    @FXML private ComboBox<String> reportTypeComboBox;
    @FXML private Button generateReportButton;
    @FXML private Label reportTitleLabel;
    @FXML private Button exportCsvButton;
    @FXML private Button exportPdfButton;
    @FXML private TableView<ReportRow> reportTableView;

    @FXML private TableColumn<ReportRow, String> usernameColumn;
    @FXML private TableColumn<ReportRow, String> passwordColumn;
    @FXML private TableColumn<ReportRow, String> userTypeColumn;
    @FXML private TableColumn<ReportRow, String> nameColumn;
    @FXML private TableColumn<ReportRow, String> addressColumn;
    @FXML private TableColumn<ReportRow, String> dobColumn;
    @FXML private TableColumn<ReportRow, String> joiningDateColumn;

    // ============ User Reference ============
    private RegisteredUser user;
    public void setUser(RegisteredUser user) { this.user = user; }

    // ============ Table Model ============
    public static class ReportRow {
        private final String username;
        private final String password;
        private final String userType;
        private final String name;
        private final String address;
        private final String dob;
        private final String joiningDate;

        public ReportRow(String username, String password, String userType,
                         String name, String address, String dob, String joiningDate) {
            this.username = username;
            this.password = password;
            this.userType = userType;
            this.name = name;
            this.address = address;
            this.dob = dob;
            this.joiningDate = joiningDate;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getUserType() { return userType; }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getDob() { return dob; }
        public String getJoiningDate() { return joiningDate; }
    }

    private final ObservableList<ReportRow> reportData = FXCollections.observableArrayList();

    // ============ Initialization ============
    @FXML
    public void initialize() {
        reportTypeComboBox.setItems(FXCollections.observableArrayList(
                "All Users Report",
                "System Logs",
                "Error Logs"
        ));

        // Bind table columns to ReportRow getters
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        joiningDateColumn.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));

        reportTableView.setItems(reportData);
    }

    // ============ Event Handlers ============
    @FXML
    public void PreviousOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/IT Admin.fxml"));
        Parent root = loader.load();

        ITAdminController controller = loader.getController();
        controller.setUser(this.user);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void onGenerateReport(ActionEvent event) {
        String type = reportTypeComboBox.getValue();
        if (type == null) {
            showAlert("Validation Error", "Please select a report type.");
            return;
        }

        reportData.clear();

        File userFile = new File("users.bin");
        if (!userFile.exists()) {
            showAlert("File Error", "users.bin file not found.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            List<RegisteredUser> users = (List<RegisteredUser>) ois.readObject();

            for (RegisteredUser u : users) {
                reportData.add(new ReportRow(
                        u.getUsername(),
                        u.getPassword(),
                        u.getUserType(),
                        u.getName(),
                        u.getAddress(),
                        u.getDob().toString(),
                        u.getJoiningDate().toString()
                ));
            }

        } catch (IOException | ClassNotFoundException e) {
            showAlert("File Error", "Could not load data from users.bin.");
            e.printStackTrace();
        }

        reportTitleLabel.setText(type + " (All Users)");
    }

    @FXML
    public void onExportCsv(ActionEvent event) {
        if (reportData.isEmpty()) {
            showAlert("Export Error", "No data to export.");
            return;
        }

        File file = new File("report.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            // Header
            writer.println("Username,Password,UserType,Name,Address,DOB,JoiningDate");

            // Data
            for (ReportRow row : reportData) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                        row.getUsername(),
                        row.getPassword(),
                        row.getUserType(),
                        row.getName(),
                        row.getAddress(),
                        row.getDob(),
                        row.getJoiningDate());
            }
            showAlert("Export Success", "Report exported to report.csv");
        } catch (IOException e) {
            showAlert("Export Error", "Failed to write CSV file.");
            e.printStackTrace();
        }
    }

    @FXML
    public void onExportPdf(ActionEvent event) {
        if (reportData.isEmpty()) {
            showAlert("Export Error", "No data to export.");
            return;
        }

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            showAlert("Export Error", "No printers available.");
            return;
        }

        boolean proceed = job.showPrintDialog(reportTableView.getScene().getWindow());
        if (proceed) {
            boolean success = job.printPage(reportTableView);
            if (success) {
                job.endJob();
                showAlert("Export Success", "Report exported via PDF printer.");
            } else {
                showAlert("Export Error", "Failed to export report.");
            }
        }
    }

    // ============ Helper ============
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
