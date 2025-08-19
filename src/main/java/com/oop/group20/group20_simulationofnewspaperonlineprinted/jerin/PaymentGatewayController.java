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

    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Link TableView columns to Payment class properties
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


        loadPaymentData();
        paymentsTableView.setItems(paymentList);

        logTextArea.setText("Payment Gateway Dashboard loaded.");
    }


    @FXML
    private void handleGenerateReport() {
        Report report = new Report("RevenueReport");
        report.generate(); // Simulates report generation
        logTextArea.appendText("\nSUCCESS: Revenue report generated.");
    }

    @FXML
    private void handleProcessRefund() {
        String paymentId = refundIdField.getText();
        if (paymentId.isEmpty()) {
            logTextArea.appendText("\nERROR: Payment ID field cannot be empty.");
            return;
        }


        System.out.println("Processing refund for payment ID: " + paymentId);
        logTextArea.appendText("\nSUCCESS: Refund for payment ID " + paymentId + " processed.");
        refundIdField.clear();
    }


    private void loadPaymentData() {

        Object data = DataManager.loadData("appdata.bin");
        if (data instanceof ApplicationData) {
            this.paymentList.setAll(((ApplicationData) data).getPayments());
        }

        if (paymentList.isEmpty()) {
            paymentList.add(new Payment(499.00));
            paymentList.add(new Payment(799.00));
        }
    }
}