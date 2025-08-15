package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentGatewayController {

    // --- FXML UI Components ---
    @FXML
    private TableView<Payment> paymentsTableView;
    @FXML
    private TableColumn<Payment, String> paymentIdColumn;
    @FXML
    private TableColumn<Payment, Double> amountColumn;
    @FXML
    private TableColumn<Payment, String> statusColumn;
    @FXML
    private TableColumn<Payment, String> dateColumn;
    @FXML
    private TextField refundIdField;
    @FXML
    private TextArea logTextArea;

    // List to hold payment data for the table
    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    /**
     * This method is called when the FXML view is loaded.
     */
    @FXML
    public void initialize() {
        // Link TableView columns to Payment class properties
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load data and set it to the table
        loadPaymentData();
        paymentsTableView.setItems(paymentList);

        logTextArea.setText("Payment Gateway Dashboard loaded.");
    }

    /**
     * Handles the "Generate Revenue Report" button click.
     */
    @FXML
    private void handleGenerateReport() {
        Report report = new Report("RevenueReport");
        report.generate(); // Simulates report generation
        logTextArea.appendText("\nSUCCESS: Revenue report generated.");
    }

    /**
     * Handles the "Process Refund" button click.
     */
    @FXML
    private void handleProcessRefund() {
        String paymentId = refundIdField.getText();
        if (paymentId.isEmpty()) {
            logTextArea.appendText("\nERROR: Payment ID field cannot be empty.");
            return;
        }

        // Simulate processing the refund
        System.out.println("Processing refund for payment ID: " + paymentId);
        logTextArea.appendText("\nSUCCESS: Refund for payment ID " + paymentId + " processed.");
        refundIdField.clear();
    }

    /**
     * Loads payment data. In a real app, this would come from a database.
     */
    private void loadPaymentData() {
        // For demonstration, we'll get payments from the main data file
        Object data = DataManager.loadData("appdata.bin");
        if (data instanceof ApplicationData) {
            this.paymentList.setAll(((ApplicationData) data).getPayments());
        }
        // Add some dummy data if the list is empty
        if (paymentList.isEmpty()) {
            paymentList.add(new Payment(499.00));
            paymentList.add(new Payment(799.00));
        }
    }
}