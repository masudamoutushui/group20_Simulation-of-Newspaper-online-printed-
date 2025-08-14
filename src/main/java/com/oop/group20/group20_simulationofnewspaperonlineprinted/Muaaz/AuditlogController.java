package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;



public class AuditlogController {
    private static final String PENDING_FILE = "Loghistory.bin";
    @FXML
    private TableView<Auditlog> tableview;
    @FXML
    private TableColumn<Auditlog, String> usernameCol;
    @FXML
    private TableColumn<Auditlog, String> usertypeCol;
    @FXML
    private TableColumn<Auditlog, String> ActionCol;
    @FXML
    private TableColumn<Auditlog, LocalDate> DateandtimeCol;

    @FXML
    private TextField usernameinpu;
    @FXML
    private ComboBox<String> usertypeinpu;
    @FXML
    private TextField ActionInpu;
    @FXML
    private DatePicker Dateandtimeinpu;

    @FXML
    private Label useroutput;
    @FXML
    private Label ActionOuput;
    @FXML
    private Label usertypeOuput;
    @FXML
    private Label Timeoutput;

    @FXML
    private ComboBox<String> usertypeFil;

    private final ObservableList<Auditlog> auditlogList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Populate ComboBoxes
        usertypeinpu.getItems().addAll(
                "Reader", "Advertiser", "Journalist", "Reporter", "Editor-in-chief",
                "IT admin", "Subscription manager", "Payment gateway representative"
        );

        usertypeFil.getItems().addAll(
                "Reader", "Advertiser", "Journalist", "Reporter", "Editor-in-chief",
                "IT admin", "Subscription manager", "Payment gateway representative"
        );

        // Setup TableView columns
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usertypeCol.setCellValueFactory(new PropertyValueFactory<>("usertype"));
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        DateandtimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Bind the ObservableList to the TableView
        tableview.setItems(auditlogList);

        // When a row is clicked, display details
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                useroutput.setText(newSelection.getUsername());
                usertypeOuput.setText(newSelection.getUsertype());
                ActionOuput.setText(newSelection.getAction());
                Timeoutput.setText(newSelection.getTime().toString());
            }
        });
    }

    @FXML
    public void addActivityOnAction(ActionEvent event) {
        String username = usernameinpu.getText();
        String usertype = usertypeinpu.getValue();
        String action = ActionInpu.getText();
        LocalDate time = Dateandtimeinpu.getValue();

        if (username.isEmpty() || usertype == null || action.isEmpty() || time == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all fields!");
            alert.showAndWait();
            return;
        }

        // Create new Auditlog object
        Auditlog adlg = new Auditlog(username, usertype, action, time);

        // Add to TableView
        auditlogList.add(adlg);

        // Save to .bin file
        saveAuditlog(adlg);

        // Clear input fields
        usernameinpu.clear();
        usertypeinpu.getSelectionModel().clearSelection();
        ActionInpu.clear();
        Dateandtimeinpu.setValue(null);
    }
    private void saveAuditlog(Auditlog adlg) {
        try (FileOutputStream fos = new FileOutputStream(PENDING_FILE, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos) {
                 @Override
                 protected void writeStreamHeader() throws IOException {
                     // Skip header if file already has data
                     if (new java.io.File(PENDING_FILE).length() == 0) {
                         super.writeStreamHeader();
                     } else {
                         reset();
                     }
                 }
             }) {
            oos.writeObject(adlg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

