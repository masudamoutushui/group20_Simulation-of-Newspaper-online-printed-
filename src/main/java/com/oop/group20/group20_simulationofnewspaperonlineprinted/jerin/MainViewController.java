package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainViewController {

    // Backend System Instances
    private final NewspaperSubscriptionSystem system = new NewspaperSubscriptionSystem();
    private final SubscriptionManager subscriptionManager = new SubscriptionManager("Admin");
    private final PaymentGatewayRepresentative paymentRepresentative = new PaymentGatewayRepresentative("FinanceUser");

    // FXML Components from Subscriber Tab
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button registerButton;
    @FXML
    private TextArea subscriberLogArea;

    // FXML Components from Marketing Tab
    @FXML
    private TextField campaignNameField;
    @FXML
    private Button createCampaignButton;
    @FXML
    private Button viewAnalyticsButton;
    @FXML
    private TextArea marketingLogArea;

    // FXML Components from Finance Tab
    @FXML
    private Button generateReportButton;
    @FXML
    private TextField refundPaymentIdField;
    @FXML
    private Button processRefundButton;
    @FXML
    private TextArea financeLogArea;

    // --- Subscriber Management Logic ---
    @FXML
    void handleRegisterSubscriber() {
        String name = nameField.getText();
        String email = emailField.getText();
        if (name.isEmpty() || email.isEmpty()) {
            subscriberLogArea.appendText("Error: Name and Email cannot be empty.\n");
            return;
        }
        Map<String, String> details = new HashMap<>();
        details.put("name", name);
        details.put("email", email);
        Subscriber newSubscriber = system.registerSubscriber(details);
        subscriberLogArea.appendText("SUCCESS: Registered " + newSubscriber.getName() + "\n");
        nameField.clear();
        emailField.clear();
        openPaymentWindow(12.99);
    }

    // --- Marketing Logic ---
    @FXML
    void handleCreateCampaign() {
        String campaignName = campaignNameField.getText();
        if (campaignName.isEmpty()) {
            marketingLogArea.appendText("Error: Campaign name cannot be empty.\n");
            return;
        }
        Map<String, String> details = new HashMap<>();
        details.put("campaignId", "C" + System.currentTimeMillis());
        details.put("name", campaignName);
        Campaign campaign = subscriptionManager.createCampaign(details);
        campaign.launch();
        marketingLogArea.appendText("SUCCESS: Campaign '" + campaign.getName() + "' created and launched.\n");
        campaignNameField.clear();
    }

    @FXML
    void handleViewAnalytics() {
        Report report = subscriptionManager.viewSubscriberAnalytics();
        report.generate();
        marketingLogArea.appendText("SUCCESS: Generated Subscriber Analytics Report.\n");
    }

    // --- Finance Logic ---
    @FXML
    void handleGenerateRevenueReport() {
        Report report = paymentRepresentative.generateRevenueReport();
        report.generate();
        financeLogArea.appendText("SUCCESS: Generated Revenue Report.\n");
    }

    @FXML
    void handleProcessRefund() {
        String paymentId = refundPaymentIdField.getText();
        if (paymentId.isEmpty()) {
            financeLogArea.appendText("Error: Payment ID cannot be empty.\n");
            return;
        }
        boolean success = paymentRepresentative.processRefund(paymentId);
        if (success) {
            financeLogArea.appendText("SUCCESS: Refund processed for payment ID: " + paymentId + "\n");
        } else {
            financeLogArea.appendText("FAILURE: Could not process refund for payment ID: " + paymentId + "\n");
        }
        refundPaymentIdField.clear();
    }

    private void openPaymentWindow(double amount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView.fxml"));
            AnchorPane paymentPage = loader.load();

            // Pass the payment amount to the new controller
            PaymentViewController controller = loader.getController();
            controller.setPaymentAmount(amount);

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Process Payment");
            paymentStage.setScene(new Scene(paymentPage));
            paymentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}