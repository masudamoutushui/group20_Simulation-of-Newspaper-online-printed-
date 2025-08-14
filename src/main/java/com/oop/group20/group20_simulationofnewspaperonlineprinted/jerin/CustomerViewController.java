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

    /**
     * This method is called by JavaFX after the FXML file has been loaded.
     * It's used to set up the initial state of the view.
     */
    @FXML
    public void initialize() {
        statusTextArea.setText("Welcome! Please select a plan to begin your subscription.");
    }

    /**
     * Handles the click event for the "Online Edition" subscribe button.
     */
    @FXML
    private void handleSubscribeOnline() {
        statusTextArea.appendText("\nSelected: Online Edition. Opening payment window...");
        // Open the payment view with the price for the online plan
        openPaymentWindow(499.00);
    }

    /**
     * Handles the click event for the "Print + Online" subscribe button.
     */
    @FXML
    private void handleSubscribePrint() {
        statusTextArea.appendText("\nSelected: Print + Online Edition. Opening payment window...");
        // Open the payment view with the price for the print + online plan
        openPaymentWindow(799.00);
    }

    /**
     * Creates and displays the payment window when a user decides to subscribe.
     * @param amount The subscription price to be passed to the payment view.
     */
    private void openPaymentWindow(double amount) {
        try {
            // 1. Load the FXML file for the payment window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView.fxml"));
            AnchorPane paymentPage = loader.load();

            // 2. Get the controller for the payment window
            PaymentViewController controller = loader.getController();

            // 3. Pass the subscription amount to the payment controller
            controller.setPaymentAmount(amount);

            // 4. Create and show the new window (Stage)
            Stage paymentStage = new Stage();
            paymentStage.setTitle("Complete Your Payment");
            paymentStage.setScene(new Scene(paymentPage));
            paymentStage.show();

        } catch (IOException e) {
            // If the FXML file can't be found, print an error
            e.printStackTrace();
            statusTextArea.setText("Error: Could not open the payment window. Please contact support.");
        }
    }
}