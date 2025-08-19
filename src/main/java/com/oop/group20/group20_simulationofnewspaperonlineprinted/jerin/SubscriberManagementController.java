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

    private ObservableList<Subscriber> subscriberList;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("subscriberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("subscriptionStatus"));

        loadData();


        subscriberTableView.setItems(subscriberList);

        deleteButton.setDisable(true);

        subscriberTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> deleteButton.setDisable(newSelection == null)
        );

        logTextArea.setText("Subscriber Management panel loaded.");
    }


    @FXML
    private void handleAddSubscriber() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty()) {
            logTextArea.appendText("\nERROR: Name and Email fields cannot be empty.");
            return;
        }

        Subscriber newSubscriber = new Subscriber(name, email);
        subscriberList.add(newSubscriber);
        logTextArea.appendText("\nSUCCESS: Added subscriber " + name + ". Please complete payment.");

        nameField.clear();
        emailField.clear();

        openPaymentWindow(499.00, newSubscriber);
    }

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

    private void openPaymentWindow(double amount, Subscriber subscriber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView.fxml"));
            AnchorPane paymentPage = loader.load();

            PaymentViewController controller = loader.getController();
            controller.setPaymentAmount(amount);


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