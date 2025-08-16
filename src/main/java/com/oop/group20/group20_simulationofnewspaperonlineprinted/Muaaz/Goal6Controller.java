package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Goal6Controller {

    @FXML private Button composeButton;
    @FXML private RadioButton inboxRadio;
    @FXML private RadioButton sentRadio;
    @FXML private TableView<InternalMessage> messagesTable;
    @FXML private TableColumn<InternalMessage, String> senderCol;
    @FXML private TableColumn<InternalMessage, String> recipientCol;
    @FXML private TableColumn<InternalMessage, String> subjectCol;
    @FXML private TableColumn<InternalMessage, String> timeCol;

    @FXML private Label messageTitleLabel;

    // Reading pane controls
    @FXML private VBox readPane;
    @FXML private Label readSubjectLabel;
    @FXML private Label readSenderLabel;
    @FXML private Label readRecipientLabel;
    @FXML private Label readTimestampLabel;
    @FXML private TextArea readContentArea;

    // Compose pane controls
    @FXML private VBox composePane;
    @FXML private TextField composeRecipientField;
    @FXML private TextField composeSubjectField;
    @FXML private TextArea composeContentArea;

    private ObservableList<InternalMessage> inboxMessages = FXCollections.observableArrayList();
    private ObservableList<InternalMessage> sentMessages = FXCollections.observableArrayList();

    private final String SENT_MESSAGES_FILE = "sent_messages.txt";
    @FXML
    private ToggleGroup folderToggleGroup;


    private RegisteredUser user;

    public void setUser(RegisteredUser user) {
        this.user = user;
    }


    @FXML
    public void initialize() {
        // Setup table columns using your InternalMessage StringProperty bindings
        senderCol.setCellValueFactory(cell -> cell.getValue().senderProperty());
        recipientCol.setCellValueFactory(cell -> cell.getValue().recipientProperty());
        subjectCol.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        timeCol.setCellValueFactory(cell -> cell.getValue().timestampProperty());

        // Load dummy inbox data
        loadDummyInbox();

        // Load sent messages from file
        loadSentMessagesFromFile();

        // Set initial table to inbox messages
        messagesTable.setItems(inboxMessages);

        // Show reading pane, hide compose pane initially
        readPane.setVisible(true);
        readPane.setManaged(true);
        composePane.setVisible(false);
        composePane.setManaged(false);

        // Listen for table selection changes
        messagesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                showMessageDetails(newSel);
            } else {
                clearMessageDetails();
            }
        });
    }

    private void loadDummyInbox() {
        inboxMessages.clear();
        inboxMessages.addAll(
                new InternalMessage("HR Dept", "Editor-in-Chief", "Meeting Reminder", "Don't forget the editorial meeting tomorrow at 10 AM.", "2025-08-01 09:00"),
                new InternalMessage("Tech Team", "Editor-in-Chief", "System Maintenance", "Scheduled downtime on Sunday 1 AM - 3 AM.", "2025-08-03 15:45"),
                new InternalMessage("Finance", "Editor-in-Chief", "Budget Report", "Please review the Q3 budget report attached.", "2025-08-04 11:30")
        );
    }

    private void loadSentMessagesFromFile() {
        sentMessages.clear();
        try {
            if (!Files.exists(Paths.get(SENT_MESSAGES_FILE))) {
                // No file yet, create empty
                Files.createFile(Paths.get(SENT_MESSAGES_FILE));
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(SENT_MESSAGES_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                // Each line format: sender|recipient|subject|content|timestamp
                String[] parts = line.split("\\|", 5);
                if (parts.length == 5) {
                    InternalMessage msg = new InternalMessage(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    sentMessages.add(msg);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSentMessagesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SENT_MESSAGES_FILE))) {
            for (InternalMessage msg : sentMessages) {
                // Write in format sender|recipient|subject|content|timestamp
                writer.write(String.join("|",
                        msg.getSender(),
                        msg.getRecipient(),
                        msg.getSubject(),
                        msg.getContent(),
                        msg.getTimestamp()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessageDetails(InternalMessage msg) {
        messageTitleLabel.setText("Message Details");
        readSubjectLabel.setText(msg.getSubject());
        readSenderLabel.setText(msg.getSender());
        readRecipientLabel.setText(msg.getRecipient());
        readTimestampLabel.setText(msg.getTimestamp());
        readContentArea.setText(msg.getContent());

        // Show read pane, hide compose pane
        readPane.setVisible(true);
        readPane.setManaged(true);
        composePane.setVisible(false);
        composePane.setManaged(false);
    }

    private void clearMessageDetails() {
        messageTitleLabel.setText("Select a message or Compose new");
        readSubjectLabel.setText("");
        readSenderLabel.setText("");
        readRecipientLabel.setText("");
        readTimestampLabel.setText("");
        readContentArea.clear();
    }

    @FXML
    private void handleCompose() {
        messagesTable.getSelectionModel().clearSelection();
        messageTitleLabel.setText("Compose Message");
        composeRecipientField.clear();
        composeSubjectField.clear();
        composeContentArea.clear();

        composePane.setVisible(true);
        composePane.setManaged(true);
        readPane.setVisible(false);
        readPane.setManaged(false);
    }

    @FXML
    private void handleSendMessage() {
        String sender = "Editor-in-Chief"; // Or fetch dynamically if you have user login
        String recipient = composeRecipientField.getText().trim();
        String subject = composeSubjectField.getText().trim();
        String content = composeContentArea.getText().trim();

        if (recipient.isEmpty() || subject.isEmpty() || content.isEmpty()) {
            showAlert("Error", "Please fill all fields to send a message.");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        InternalMessage newMsg = new InternalMessage(sender, recipient, subject, content, timestamp);

        // Add to sent messages list & save
        sentMessages.add(newMsg);
        saveSentMessagesToFile();

        if (sentRadio.isSelected()) {
            messagesTable.setItems(sentMessages);
        }

        // Reset UI to reading mode and show the sent message selected
        messagesTable.getSelectionModel().select(newMsg);
        showMessageDetails(newMsg);

        composePane.setVisible(false);
        composePane.setManaged(false);
        readPane.setVisible(true);
        readPane.setManaged(true);
    }

    @FXML
    private void handleCancelCompose() {
        composePane.setVisible(false);
        composePane.setManaged(false);
        readPane.setVisible(true);
        readPane.setManaged(true);
        messageTitleLabel.setText("Select a message or Compose new");
    }

    @FXML
    private void handleFolderSwitch() {
        if (inboxRadio.isSelected()) {
            messagesTable.setItems(inboxMessages);
        } else if (sentRadio.isSelected()) {
            messagesTable.setItems(sentMessages);
        }
        messagesTable.getSelectionModel().clearSelection();
        clearMessageDetails();
        messageTitleLabel.setText("Select a message or Compose new");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void backOnAction(ActionEvent event) throws IOException {
        if (user == null) {
            System.err.println("Error: No logged-in user to pass back.");
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        UserDetailsController.openWithUser(user, stage);
    }
    @Deprecated
    public void nextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal7.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
