package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class AdpartnerManagementController {

    @FXML
    private TableView<Advertiser> advertiserTableView;
    @FXML
    private TableColumn<Advertiser, Number> idColumn;
    @FXML
    private TableColumn<Advertiser, String> companyColumn;
    @FXML
    private TableColumn<Advertiser, String> emailColumn;
    @FXML
    private TableColumn<Advertiser, String> statusColumn;
    @FXML
    private TableColumn<Advertiser, Number> limitColumn;
    @FXML
    private TableColumn<Advertiser, Number> revenueColumn;

    // Input Fields
    @FXML
    private TextField idField, companyNameField, emailField, statusField, spendingLimitField, campaignsField;

    @FXML
    private ListView<String> campaignsListView;

    private ObservableList<Advertiser> advertisers = FXCollections.observableArrayList();
    @FXML
    private Button approveButton;
    @FXML
    private VBox detailsPane;
    @FXML
    private Button deactivateButton;

    @FXML
    public void initialize() {
        // Setup TableView columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        limitColumn.setCellValueFactory(cellData -> cellData.getValue().spendingLimitProperty());


        advertiserTableView.setItems(advertisers);

        // Update details pane when selecting an advertiser
        advertiserTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showAdvertiserDetails(newSelection);
            }
        });
    }

    // Handle adding advertiser manually
    @FXML
    private void handleAddAdvertiser() {
        try {
            int id = Integer.parseInt(idField.getText());
            String company = companyNameField.getText();
            String email = emailField.getText();
            String status = statusField.getText();
            double limit = Double.parseDouble(spendingLimitField.getText());

            String[] campaigns = campaignsField.getText().split(",");

            Advertiser adv = new Advertiser(id, company, email, status, limit);
            adv.setActiveCampaigns(Arrays.asList(campaigns));

            advertisers.add(adv);

            clearInputFields();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID, Spending Limit and Total Revenue must be numeric!");
            alert.showAndWait();
        }
    }

    private void showAdvertiserDetails(Advertiser adv) {
        companyNameField.setText(adv.getCompanyName());
        spendingLimitField.setText(String.valueOf(adv.getSpendingLimit()));
        campaignsListView.getItems().setAll(adv.getActiveCampaigns());
    }

    private void clearInputFields() {
        idField.clear();
        companyNameField.clear();
        emailField.clear();
        statusField.clear();
        spendingLimitField.clear();

        campaignsField.clear();
        campaignsListView.getItems().clear();
    }

    @FXML
    public void handleDeactivate(ActionEvent actionEvent) {
    }

    @FXML
    public void handleApprove(ActionEvent actionEvent) {
    }
}
