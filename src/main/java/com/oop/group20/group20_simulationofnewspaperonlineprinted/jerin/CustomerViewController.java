package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerViewController {

    @FXML
    private TextArea statusTextArea;

    @FXML
    public void initialize() {
        statusTextArea.setText("Welcome! Please select a plan to begin your subscription.");
    }

    @FXML
    private void handleSubscribeOnline() {
        statusTextArea.appendText("\nSelected: Online Edition. Opening payment window...");
        openPaymentWindow(499.00);
    }

    @FXML
    private void handleSubscribePrint() {
        statusTextArea.appendText("\nSelected: Print + Online Edition. Opening payment window...");
        openPaymentWindow(799.00);
    }

    private void openPaymentWindow(double amount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView.fxml"));
            AnchorPane paymentPage = loader.load();

            PaymentViewController controller = loader.getController();
            controller.setPaymentAmount(amount);

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Complete Your Payment");
            paymentStage.setScene(new Scene(paymentPage));
            paymentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            statusTextArea.setText("Error: Could not open the payment window. Please contact support.");
        }
    }
}