package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdpartnerManagementController {

    private static final String FILE_NAME = "advertisers.bin";

    @FXML private TableView<Advertiser> advertiserTableView;
    @FXML private TableColumn<Advertiser, String> idColumn;
    @FXML private TableColumn<Advertiser, String> companyColumn;
    @FXML private TableColumn<Advertiser, String> emailColumn;
    @FXML private TableColumn<Advertiser, String> statusColumn;
    @FXML private TableColumn<Advertiser, String> limitColumn;

    @FXML private TextField idField;
    @FXML private TextField companyNameField;
    @FXML private TextField emailField;
    @FXML private TextField spendingLimitField;
    @FXML private TextField campaignsField;
    @FXML private ListView<String> campaignsListView;

    private final ObservableList<Advertiser> advertiserList = FXCollections.observableArrayList();
    @FXML
    private Button approveButton;
    @FXML
    private TextField statusField;
    @FXML
    private VBox detailsPane;
    @FXML
    private Button deactivateButton;

    @FXML
    public void initialize() {
        // Link Table Columns
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        companyColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCompanyName()));
        emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        limitColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSpendingLimit()));

        // Load existing advertisers from file
        loadAdvertisers();
        advertiserTableView.setItems(advertiserList);

        // Show campaigns in list when advertiser is selected
        advertiserTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            campaignsListView.getItems().clear();
            if (newSel != null) {
                campaignsListView.getItems().addAll(newSel.getCampaigns());
            }
        });
    }

    @FXML
    private void handleAddAdvertiser() {
        String id = idField.getText();
        String company = companyNameField.getText();
        String email = emailField.getText();
        String status = statusField.getText();
        String limit = spendingLimitField.getText();
        List<String> campaigns = Arrays.asList(campaignsField.getText().split(","));

        Advertiser advertiser = new Advertiser(id, company, email, status, limit, campaigns);
        advertiserList.add(advertiser);
        saveAdvertisers();

        clearForm();
    }

    private void clearForm() {
        idField.clear();
        companyNameField.clear();
        emailField.clear();
        statusField.clear();
        spendingLimitField.clear();
        campaignsField.clear();
    }

    private void saveAdvertisers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new ArrayList<>(advertiserList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAdvertisers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                for (Object item : (List<?>) obj) {
                    if (item instanceof Advertiser) {
                        advertiserList.add((Advertiser) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Approve advertiser
    @FXML
    private void handleApprove() {
        Advertiser selected = advertiserTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Approved");
            advertiserTableView.refresh();
            saveAdvertisers();
        }
    }

    // Deactivate advertiser
    @FXML
    private void handleDeactivate() {
        Advertiser selected = advertiserTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Inactive");
            advertiserTableView.refresh();
            saveAdvertisers();
        }
    }

    @FXML
    public void nextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/ContentModeration.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void PreviousOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/settings.fxml.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
