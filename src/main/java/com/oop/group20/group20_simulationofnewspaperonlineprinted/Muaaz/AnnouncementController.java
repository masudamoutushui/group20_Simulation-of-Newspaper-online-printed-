package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementController {

    @FXML private TextField titleField;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<String> urgencyCombo;
    @FXML private ComboBox<String> towhomCombo;
    @FXML private TableView<Announcement> historyTable;
    @FXML private TableColumn<Announcement, String> Announcementtitle;
    @FXML private TableColumn<Announcement, String> MessageCol;
    @FXML private TableColumn<Announcement, String> urgencycol;
    @FXML private TableColumn<Announcement, String> towhomcol;
    @FXML private ComboBox<String> towhomCombofil;

    private final String FILE_PATH = "announcements.bin";
    private ObservableList<Announcement> announcementList;
    @FXML
    private Button clearButton;
    @FXML
    private Button sendButton;
    // Logged-in user reference
    private RegisteredUser user;


    // Setter for logged-in user
    public void setUser(RegisteredUser user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        // Table column bindings
        Announcementtitle.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        MessageCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getMessage()));
        urgencycol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUrgency()));
        towhomcol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getToWhom()));

        // Urgency options
        urgencyCombo.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));

        // User combo options
        ObservableList<String> userTypes = FXCollections.observableArrayList(
                "Journalist",
                "Reporter",
                "Editor-in-chief",
                "Subscription Manager",
                "Payment Gateway Representative",
                "All Users"
        );

        towhomCombo.setItems(userTypes);
        towhomCombofil.setItems(userTypes);

        announcementList = FXCollections.observableArrayList();
        loadFromFile();
        historyTable.setItems(announcementList);
    }

    @FXML
    private void handleSendAnnouncement() {
        String title = titleField.getText().trim();
        String message = messageArea.getText().trim();
        String urgency = urgencyCombo.getValue();
        String toWhom = towhomCombo.getValue();

        if (title.isEmpty() || message.isEmpty() || urgency == null || toWhom == null) {
            showAlert(Alert.AlertType.WARNING, "Please fill all fields!");
            return;
        }

        // If "All Users" is selected, replace it with all individual users
        if (toWhom.equals("All Users")) {
            toWhom = "Journalist, Reporter, Editor-in-chief, Subscription Manager, Payment Gateway Representative";
        }

        Announcement announcement = new Announcement(title, message, urgency, toWhom);
        announcementList.add(announcement);
        saveToFile();
        clearForm();
    }

    @FXML
    private void handleClearForm() {
        clearForm();
    }

    private void clearForm() {
        titleField.clear();
        messageArea.clear();
        urgencyCombo.setValue(null);
        towhomCombo.setValue(null);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            List<Announcement> listToSave = new ArrayList<>(announcementList);
            oos.writeObject(listToSave);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error saving file!");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            List<Announcement> loadedList = (List<Announcement>) ois.readObject();
            announcementList.setAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error loading file!");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void NextOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/PublishedArticleManagement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void PreviousOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/IT Admin.fxml"));
        Parent root = loader.load();

        // Pass the current logged-in user to ITAdminController
        ITAdminController controller = loader.getController();
        controller.setUser(this.user); // pass the logged-in user

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
