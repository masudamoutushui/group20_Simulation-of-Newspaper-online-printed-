package com.oop.group20.group20_simulationofnewspaperonlineprinted.masuda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class Goal1AdvertiserCreateandSubmitAdvertisementProposalsController {

    @FXML private Text addCompanyName;
    @FXML private Text addPublishingDate;
    @FXML private Label showReview;
    @FXML private Text addPublicationType;
    @FXML private Text addTitleDescription;
    @FXML private TextField enterCompanyName;
    @FXML private ComboBox<String> selectType;
    @FXML private Text addAdvertiserName;
    @FXML private TextArea enterTitleDescription;
    @FXML private DatePicker selectDate;
    @FXML private TextField enterAdvertiserName;

    @FXML
    public void initialize() {
        // Initialize ComboBox with publication types
        selectType.getItems().addAll("Online", "Print", "Both");
    }

    @FXML
    public void reviewOnAction(ActionEvent actionEvent) {
        String advertiser = enterAdvertiserName.getText();
        String company = enterCompanyName.getText();
        String description = enterTitleDescription.getText();
        String type = selectType.getValue();
        String date = (selectDate.getValue() != null) ? selectDate.getValue().toString() : "No Date Selected";

        // Show review text in label
        showReview.setText("Ad by " + advertiser + " (" + company + ")\nType: " + type + "\nDate: " + date);
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        enterAdvertiserName.clear();
        enterCompanyName.clear();
        enterTitleDescription.clear();
        selectType.getSelectionModel().clearSelection();
        selectDate.setValue(null);
        showReview.setText("Label");
    }

    @FXML
    public void submitOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission Confirmation");
        alert.setHeaderText("Advertisement Submitted");
        alert.setContentText("Your advertisement proposal has been submitted successfully!");
        alert.showAndWait();
    }
}
