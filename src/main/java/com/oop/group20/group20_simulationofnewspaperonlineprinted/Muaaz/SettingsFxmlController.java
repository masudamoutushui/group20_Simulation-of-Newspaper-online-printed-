package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class SettingsFxmlController {

    @FXML private CheckBox bKashEnabledCheckBox;
    @FXML private PasswordField bKashApiKeyField;

    @FXML private CheckBox upayEnabledCheckBox;
    @FXML private PasswordField upayApiKeyField;

    @FXML private CheckBox cardPaymentEnabledCheckBox;
    @FXML private PasswordField cardPaymentApiKeyField;

    @FXML private TextField maxImageSizeField;
    @FXML private TextField contactEmailField;

    private final String SETTINGS_FILE = "settings.bin";
    private Settings currentSettings;

    @FXML
    public void initialize() {
        loadSettings();
    }

    @FXML
    private void handleSaveSettings() {
        try {
            currentSettings = new Settings(
                    bKashEnabledCheckBox.isSelected(),
                    bKashApiKeyField.getText(),
                    upayEnabledCheckBox.isSelected(),
                    upayApiKeyField.getText(),
                    cardPaymentEnabledCheckBox.isSelected(),
                    cardPaymentApiKeyField.getText(),
                    maxImageSizeField.getText(),
                    contactEmailField.getText()
            );

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE));
            oos.writeObject(currentSettings);
            oos.close();
            System.out.println("Settings saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRevertSettings() {
        loadSettings();
        System.out.println("Settings reverted to last saved state!");
    }

    private void loadSettings() {
        try {
            File file = new File(SETTINGS_FILE);
            if(file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                currentSettings = (Settings) ois.readObject();
                ois.close();
            } else {
                // default dummy settings if file doesn't exist
                currentSettings = new Settings(
                        true, "dummyBkashKey",
                        false, "dummyUpayKey",
                        true, "dummyCardKey",
                        "5", "admin@example.com"
                );
            }

            // populate FXML controls
            bKashEnabledCheckBox.setSelected(currentSettings.isbKashEnabled());
            bKashApiKeyField.setText(currentSettings.getbKashApiKey());

            upayEnabledCheckBox.setSelected(currentSettings.isUpayEnabled());
            upayApiKeyField.setText(currentSettings.getUpayApiKey());

            cardPaymentEnabledCheckBox.setSelected(currentSettings.isCardPaymentEnabled());
            cardPaymentApiKeyField.setText(currentSettings.getCardPaymentApiKey());

            maxImageSizeField.setText(currentSettings.getMaxImageSize());
            contactEmailField.setText(currentSettings.getContactEmail());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // --- Settings POJO ---
    public static class Settings implements Serializable {
        private static final long serialVersionUID = 1L;

        private boolean bKashEnabled;
        private String bKashApiKey;

        private boolean upayEnabled;
        private String upayApiKey;

        private boolean cardPaymentEnabled;
        private String cardPaymentApiKey;

        private String maxImageSize;
        private String contactEmail;

        public Settings(boolean bKashEnabled, String bKashApiKey,
                        boolean upayEnabled, String upayApiKey,
                        boolean cardPaymentEnabled, String cardPaymentApiKey,
                        String maxImageSize, String contactEmail) {
            this.bKashEnabled = bKashEnabled;
            this.bKashApiKey = bKashApiKey;
            this.upayEnabled = upayEnabled;
            this.upayApiKey = upayApiKey;
            this.cardPaymentEnabled = cardPaymentEnabled;
            this.cardPaymentApiKey = cardPaymentApiKey;
            this.maxImageSize = maxImageSize;
            this.contactEmail = contactEmail;
        }

        // --- Getters ---
        public boolean isbKashEnabled() { return bKashEnabled; }
        public String getbKashApiKey() { return bKashApiKey; }
        public boolean isUpayEnabled() { return upayEnabled; }
        public String getUpayApiKey() { return upayApiKey; }
        public boolean isCardPaymentEnabled() { return cardPaymentEnabled; }
        public String getCardPaymentApiKey() { return cardPaymentApiKey; }
        public String getMaxImageSize() { return maxImageSize; }
        public String getContactEmail() { return contactEmail; }
    }
}
