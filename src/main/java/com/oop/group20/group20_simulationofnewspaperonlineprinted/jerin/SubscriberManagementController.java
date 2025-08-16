package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SubscriberManagementController {

    // FXML UI Components
    @FXML
    private TableView<Subscriber> subscriberTableView;
    @FXML
    private TableColumn<Subscriber, String> idColumn;
    @FXML
    private TableColumn<Subscriber, String> nameColumn;
    @FXML
    private TableColumn<Subscriber, String> emailColumn;
    @FXML
    private TableColumn<Subscriber, String> statusColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button deleteButton;
    @FXML
    private TextArea logTextArea;

    // This list holds the data displayed in the table
    private ObservableList<Subscriber> subscriberList;

    /**
     * This method is called when the FXML is loaded. It initializes the view.
     */
    @FXML
    public void initialize() {
        // 1. Link table columns to the properties in the Subscriber class
        idColumn.setCellValueFactory(new PropertyValueFactory<>("subscriberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("subscriptionStatus"));

        // 2. Load data from the appdata.bin file
        loadData();

        // 3. Set the loaded data into the table
        subscriberTableView.setItems(subscriberList);

        // 4. Disable the delete button initially
        deleteButton.setDisable(true);

        // 5. Add a listener to enable the delete button only when a row is selected
        subscriberTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> deleteButton.setDisable(newSelection == null)
        );

        logTextArea.setText("Subscriber Management panel loaded.");
    }

    /**
     * Handles the "Add New Subscriber" button click.
     */
    @FXML
    private void handleAddSubscriber() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty()) {
            logTextArea.appendText("\nERROR: Name and Email fields cannot be empty.");
            return;
        }

        // Create the new subscriber and add it to our list
        Subscriber newSubscriber = new Subscriber(name, email);
        subscriberList.add(newSubscriber);
        logTextArea.appendText("\nSUCCESS: Added subscriber " + name + ". Please complete payment.");

        // Clear the input fields
        nameField.clear();
        emailField.clear();

        // Open the payment window for the subscription fee
        openPaymentWindow(499.00, newSubscriber);
    }

    /**
     * Handles the "Delete Selected Subscriber" button click.
     */
    @FXML
    private void handleDeleteSubscriber() {
        Subscriber selectedSubscriber = subscriberTableView.getSelectionModel().getSelectedItem();
        if (selectedSubscriber != null) {
            subscriberList.remove(selectedSubscriber);
            logTextArea.appendText("\nSUCCESS: Removed subscriber " + selectedSubscriber.getName() + ".");
        } else {
            logTextArea.appendText("\nERROR: No subscriber selected.");
        }
    }

    /**
     * Loads subscriber data from the binary file.
     */
    private void loadData() {
        Object data = DataManager.loadData("appdata.bin");
        if (data instanceof ApplicationData) {
            // Get the list of subscribers from the main data container
            this.subscriberList = FXCollections.observableArrayList(((ApplicationData) data).getSubscribers());
        } else {
            // If no file exists, start with an empty list
            this.subscriberList = FXCollections.observableArrayList();
        }
    }

    /**
     * Opens the payment window and passes the amount and subscriber reference.
     * @param amount The subscription fee.
     * @param subscriber The newly created subscriber.
     */
    private void openPaymentWindow(double amount, Subscriber subscriber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView.fxml"));
            AnchorPane paymentPage = loader.load();

            PaymentViewController controller = loader.getController();
            controller.setPaymentAmount(amount);
            // We can enhance the payment controller later to update the subscriber status

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Process Payment for " + subscriber.getName());
            paymentStage.setScene(new Scene(paymentPage));
            paymentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            logTextArea.appendText("\nERROR: Could not open payment window.");
        }
    }
}