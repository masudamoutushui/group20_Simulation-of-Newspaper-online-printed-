package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class SettingsFxmlController {

    // ===== Payment Gateway Controls =====
    @FXML
    private CheckBox bKashEnabledCheckBox;
    @FXML
    private PasswordField bKashApiKeyField;

    @FXML
    private CheckBox upayEnabledCheckBox;
    @FXML
    private PasswordField upayApiKeyField;

    @FXML
    private CheckBox cardPaymentEnabledCheckBox;
    @FXML
    private PasswordField cardPaymentApiKeyField;

    // ===== General Settings Controls =====
    @FXML
    private TextField maxImageSizeField;
    @FXML
    private TextField contactEmailField;

    // ===== Buttons =====
    @FXML
    private Button saveButton;
    @FXML
    private Button revertButton;

    // ===== Model =====
    private SystemSettings settings = new SystemSettings();
    private SystemSettings originalSettings = new SystemSettings(); // For revert

    // ===== ArrayList to store saved configurations =====
    private ArrayList<SystemSettings> savedSettingsList = new ArrayList<>();

    // ===== Initialization =====
    @FXML
    public void initialize() {
        // Bind Payment Gateway Controls
        bKashEnabledCheckBox.selectedProperty().bindBidirectional(settings.bKashEnabledProperty());
        bKashApiKeyField.textProperty().bindBidirectional(settings.bKashApiKeyProperty());

        upayEnabledCheckBox.selectedProperty().bindBidirectional(settings.upayEnabledProperty());
        upayApiKeyField.textProperty().bindBidirectional(settings.upayApiKeyProperty());

        cardPaymentEnabledCheckBox.selectedProperty().bindBidirectional(settings.cardPaymentEnabledProperty());
        cardPaymentApiKeyField.textProperty().bindBidirectional(settings.cardPaymentApiKeyProperty());

        // Bind General Settings Controls
        maxImageSizeField.textProperty().bindBidirectional(settings.maxImageSizeProperty());
        contactEmailField.textProperty().bindBidirectional(settings.contactEmailProperty());

        // Save original settings for revert
        copySettings(settings, originalSettings);
    }

    // ===== Save Settings =====
    @FXML
    private void handleSaveSettings() {
        if (!validateInputs()) return;

        // Create a copy of current settings and add to ArrayList
        SystemSettings savedCopy = new SystemSettings();
        copySettings(settings, savedCopy);
        savedSettingsList.add(savedCopy);

        // Update original settings for revert
        copySettings(settings, originalSettings);

        showAlert(AlertType.INFORMATION, "Settings Saved",
                "Your settings have been saved successfully. Total saved versions: " + savedSettingsList.size());
    }

    // ===== Revert Settings =====
    @FXML
    private void handleRevertSettings() {
        copySettings(originalSettings, settings);
        showAlert(AlertType.INFORMATION, "Settings Reverted", "Changes have been reverted.");
    }

    // ===== Validation =====
    private boolean validateInputs() {
        try {
            int maxSize = Integer.parseInt(maxImageSizeField.getText());
            if (maxSize <= 0) {
                showAlert(AlertType.ERROR, "Invalid Input", "Max image size must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Invalid Input", "Max image size must be a number.");
            return false;
        }

        String email = contactEmailField.getText();
        if (!email.contains("@") || !email.contains(".")) {
            showAlert(AlertType.ERROR, "Invalid Input", "Enter a valid email address.");
            return false;
        }

        return true;
    }

    // ===== Utility Methods =====
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void copySettings(SystemSettings source, SystemSettings target) {
        target.setBKashEnabled(source.isBKashEnabled());
        target.setBKashApiKey(source.getBKashApiKey());
        target.setUpayEnabled(source.isUpayEnabled());
        target.setUpayApiKey(source.getUpayApiKey());
        target.setCardPaymentEnabled(source.isCardPaymentEnabled());
        target.setCardPaymentApiKey(source.getCardPaymentApiKey());
        target.setMaxImageSize(source.getMaxImageSize());
        target.setContactEmail(source.getContactEmail());
    }

    // ===== Getter for saved settings list =====
    public ArrayList<SystemSettings> getSavedSettingsList() {
        return savedSettingsList;
    }
}
