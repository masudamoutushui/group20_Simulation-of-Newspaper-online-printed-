package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Goal6Controller implements Initializable {

    // FXML Components
    @FXML private ListView<String> folderListView;
    @FXML private Label folderTitleLabel;
    @FXML private TableView<InternalMessage> messageTable;
    @FXML private TableColumn<InternalMessage, String> senderReceiverCol;
    @FXML private TableColumn<InternalMessage, String> subjectCol;
    @FXML private TableColumn<InternalMessage, String> dateCol;
    @FXML private VBox readPane;
    @FXML private Label readFromLabel;
    @FXML private Label readSubjectLabel;
    @FXML private TextArea readMessageContentArea;
    @FXML private VBox composePane;
    @FXML private ComboBox<String> recipientComboBox;
    @FXML private TextField composeSubjectField;
    @FXML private TextArea composeMessageArea;
    @FXML private Button composeButton, sendButton, cancelButton, backButton;
    @FXML private Label statusLabel;

    // Data Lists
    private ObservableList<InternalMessage> inboxMessages = FXCollections.observableArrayList();
    private ObservableList<InternalMessage> sentMessages = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupControls();
        loadDummyData();
        setupListeners();
        // Set initial view to Inbox
        folderListView.getSelectionModel().select("Inbox");
        updateMessageView();
    }

    private void setupControls() {
        // Setup folder list
        folderListView.setItems(FXCollections.observableArrayList("Inbox", "Sent"));

        // Setup table columns
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("formattedTimestamp")); // Use the formatted string

        // Setup recipients ComboBox
        recipientComboBox.setItems(FXCollections.observableArrayList(
                "Advertisement Manager", "Subscription Manager", "IT Manager"
        ));
    }

    private void setupListeners() {
        // Listen for folder changes (Inbox/Sent)
        folderListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateMessageView()
        );

        // Listen for message selection in the table
        messageTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> showMessageDetails(newVal)
        );
    }

    private void updateMessageView() {
        String selectedFolder = folderListView.getSelectionModel().getSelectedItem();
        if (selectedFolder == null) return;

        if (selectedFolder.equals("Inbox")) {
            folderTitleLabel.setText("Inbox");
            senderReceiverCol.setText("From");
            senderReceiverCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
            messageTable.setItems(inboxMessages);
        } else { // Sent
            folderTitleLabel.setText("Sent");
            senderReceiverCol.setText("To");
            senderReceiverCol.setCellValueFactory(new PropertyValueFactory<>("recipient"));
            messageTable.setItems(sentMessages);
        }
        messageTable.refresh();
        clearReadPane();
    }

    private void showMessageDetails(InternalMessage message) {
        switchToReadMode();
        if (message != null) {
            readFromLabel.setText(message.getSender() + " to " + message.getRecipient());
            readSubjectLabel.setText(message.getSubject());
            readMessageContentArea.setText(message.getContent());
        } else {
            clearReadPane();
        }
    }

    private void clearReadPane() {
        readFromLabel.setText("N/A");
        readSubjectLabel.setText("N/A");
        readMessageContentArea.clear();
    }

    @FXML
    void handleComposeButton(ActionEvent event) {
        switchToComposeMode();
    }

    @FXML
    void handleSendButton(ActionEvent event) {
        String recipient = recipientComboBox.getValue();
        String subject = composeSubjectField.getText();
        String content = composeMessageArea.getText();

        if (recipient == null || subject.isBlank() || content.isBlank()) {
            showAlert("Error", "Recipient, Subject, and Message cannot be empty.");
            return;
        }

        InternalMessage newMessage = new InternalMessage(
                "Editor-in-Chief", recipient, subject, content, LocalDateTime.now()
        );
        sentMessages.add(newMessage);

        showAlert("Success", "Message sent to " + recipient);

        // Switch back to the sent folder to see the new message
        folderListView.getSelectionModel().select("Sent");
        updateMessageView();
        switchToReadMode();
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        switchToReadMode();
    }

    @FXML
    void handleBack(ActionEvent event) {
        System.out.println("Switching back to main dashboard...");
        // Add scene switching logic here
    }

    private void switchToComposeMode() {
        composePane.setVisible(true);
        readPane.setVisible(false);
        composeSubjectField.clear();
        composeMessageArea.clear();
        recipientComboBox.getSelectionModel().clearSelection();
        statusLabel.setText("Composing new message...");
    }

    private void switchToReadMode() {
        composePane.setVisible(false);
        readPane.setVisible(true);
        statusLabel.setText("Ready.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(title.equals("Error") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadDummyData() {
        inboxMessages.add(new InternalMessage("Advertisement Manager", "Editor-in-Chief", "Ad Space for Sunday Edition", "Hi Chief,\n\nWe have a full-page ad request from a major client for this Sunday. Can we confirm the space on page 3?\n\nThanks,\nAd Dept.", LocalDateTime.now().minusHours(2)));
        inboxMessages.add(new InternalMessage("IT Manager", "Editor-in-Chief", "System Maintenance Notice", "This is a reminder that the content management system will be down for scheduled maintenance tonight from 1 AM to 2 AM.", LocalDateTime.now().minusDays(1)));

        sentMessages.add(new InternalMessage("Editor-in-Chief", "Subscription Manager", "Online Subscription Numbers", "Could you please send me the latest report on our online subscription growth for Q2?\n\nRegards,\nEditor-in-Chief", LocalDateTime.now().minusHours(5)));
    }
}