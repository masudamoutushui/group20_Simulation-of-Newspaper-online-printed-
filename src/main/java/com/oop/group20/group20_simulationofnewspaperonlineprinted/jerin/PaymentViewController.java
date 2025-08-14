package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentViewController {

    @FXML
    private Label amountLabel;

    @FXML
    private ChoiceBox<String> paymentMethodBox;

    @FXML
    private TextField paymentDetailsField;

    @FXML
    private Button payButton;

    @FXML
    private Label statusLabel;

    private double amountToPay;

    // This method is called when the FXML file is loaded
    @FXML
    public void initialize() {
        // Add the payment options to the choice box
        paymentMethodBox.getItems().addAll("bKash", "Upay", "Card");
        paymentMethodBox.setValue("bKash"); // Set a default value
    }

    // This method can be called from another controller to set the payment amount
    public void setPaymentAmount(double amount) {
        this.amountToPay = amount;
        amountLabel.setText(String.format("Amount to Pay: ৳%.2f", amount));
    }

    @FXML
    void handleProcessPayment() {
        String selectedMethod = paymentMethodBox.getValue();
        if (selectedMethod == null || paymentDetailsField.getText().isEmpty()) {
            statusLabel.setText("Status: Please select a method and enter details.");
            return;
        }

        PaymentGateway gateway;

        // Select the correct gateway based on user's choice
        switch (selectedMethod) {
            case "bKash":
                gateway = new BkashGateway();
                break;
            case "Upay":
                gateway = new UpayGateway();
                break;
            case "Card":
                gateway = new CardPaymentGateway();
                break;
            default:
                statusLabel.setText("Status: Invalid payment method.");
                return;
        }

        // Process the payment
        statusLabel.setText("Status: Processing...");
        boolean success = gateway.processPayment(amountToPay);

        if (success) {
            statusLabel.setText("Status: Payment Successful!");
            payButton.setDisable(true); // Disable button after successful payment
        } else {
            statusLabel.setText("Status: Payment Failed. Please try again.");
        }
    }
}