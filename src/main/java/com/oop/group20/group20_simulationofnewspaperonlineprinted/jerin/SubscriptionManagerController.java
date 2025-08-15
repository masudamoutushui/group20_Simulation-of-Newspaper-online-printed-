package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
// --- FIX: DELETE THIS LINE ---
// import javafx.util.Subscription;  <-- REMOVE THIS LINE

import java.util.HashMap;
import java.util.Map;

// Make sure your OWN Subscription class is in this 'jerin' package
public class SubscriptionManagerController {

    // --- FXML Components from the design ---
    @FXML
    private TextField campaignNameField;
    @FXML
    private TextArea logTextArea;
    @FXML
    private TableView<Subscription> subscriptionTableView; // This should be your Subscription class
    @FXML
    private TableColumn<Subscription, String> subIdColumn;
    @FXML
    private TableColumn<Subscription, String> subTierColumn;
    @FXML
    private TableColumn<Subscription, String> subTypeColumn;
    @FXML
    private TableColumn<Subscription, Double> subPriceColumn;
    @FXML
    private ChoiceBox<String> tierChoiceBox;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private TextField priceField;

    // This list will hold the subscriptions displayed in the table
    private ObservableList<Subscription> subscriptionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // --- Setup TableView ---
        subIdColumn.setCellValueFactory(new PropertyValueFactory<>("subscriptionId"));
        subTierColumn.setCellValueFactory(new PropertyValueFactory<>("tier"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        subPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Set the table's data source
        subscriptionTableView.setItems(subscriptionList);

        // --- Setup ChoiceBoxes ---
        tierChoiceBox.getItems().addAll("Premium", "Standard", "Basic");
        typeChoiceBox.getItems().addAll("Online", "Print", "Print + Online");

        // --- FIX: Simplified this line ---
        // This now correctly creates an instance of YOUR Subscription class.
        subscriptionList.add(new Subscription("Premium", "Online", 499.00));

        logTextArea.setText("Subscription Manager Dashboard Loaded.");
    }

    @FXML
    private void handleCreateCampaign() {
        String campaignName = campaignNameField.getText();
        if (campaignName.isEmpty()) {
            logTextArea.appendText("\nError: Campaign Name cannot be empty.");
            return;
        }
        Map<String, String> details = new HashMap<>();
        details.put("campaignId", "C" + System.currentTimeMillis());
        details.put("name", campaignName);

        Campaign newCampaign = new Campaign(details.get("campaignId"), details.get("name"));
        newCampaign.launch();

        logTextArea.appendText("\nCampaign created and launched: " + newCampaign.getName());
        campaignNameField.clear();
    }

    @FXML
    private void handleViewAnalytics() {
        Report report = new Report("AnalyticsReport");
        report.generate();
        logTextArea.appendText("\nGenerating subscriber analytics report.");
    }

    @FXML
    private void handleAddSubscription() {
        String tier = tierChoiceBox.getValue();
        String type = typeChoiceBox.getValue();
        String priceText = priceField.getText();

        if (tier == null || type == null || priceText.isEmpty()) {
            logTextArea.appendText("\nError: Please fill all subscription fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            // This now correctly creates an instance of YOUR Subscription class
            Subscription newSubscription = new Subscription(tier, type, price);
            subscriptionList.add(newSubscription);
            logTextArea.appendText("\nNew subscription added: " + tier + " - " + type);
        } catch (NumberFormatException e) {
            logTextArea.appendText("\nError: Price must be a valid number.");
        }
    }
}