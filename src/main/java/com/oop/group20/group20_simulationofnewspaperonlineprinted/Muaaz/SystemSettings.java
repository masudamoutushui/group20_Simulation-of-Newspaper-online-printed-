package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SystemSettings {

    // Payment Gateway Settings
    private final BooleanProperty bKashEnabled = new SimpleBooleanProperty(false);
    private final StringProperty bKashApiKey = new SimpleStringProperty("");

    private final BooleanProperty upayEnabled = new SimpleBooleanProperty(false);
    private final StringProperty upayApiKey = new SimpleStringProperty("");

    private final BooleanProperty cardPaymentEnabled = new SimpleBooleanProperty(false);
    private final StringProperty cardPaymentApiKey = new SimpleStringProperty("");

    // General Settings
    private final StringProperty maxImageSize = new SimpleStringProperty("5");
    private final StringProperty contactEmail = new SimpleStringProperty("admin@example.com");

    // ===== Getters and Setters with Properties =====

    // bKash
    public BooleanProperty bKashEnabledProperty() { return bKashEnabled; }
    public boolean isBKashEnabled() { return bKashEnabled.get(); }
    public void setBKashEnabled(boolean value) { bKashEnabled.set(value); }

    public StringProperty bKashApiKeyProperty() { return bKashApiKey; }
    public String getBKashApiKey() { return bKashApiKey.get(); }
    public void setBKashApiKey(String value) { bKashApiKey.set(value); }

    // Upay
    public BooleanProperty upayEnabledProperty() { return upayEnabled; }
    public boolean isUpayEnabled() { return upayEnabled.get(); }
    public void setUpayEnabled(boolean value) { upayEnabled.set(value); }

    public StringProperty upayApiKeyProperty() { return upayApiKey; }
    public String getUpayApiKey() { return upayApiKey.get(); }
    public void setUpayApiKey(String value) { upayApiKey.set(value); }

    // Card Payment
    public BooleanProperty cardPaymentEnabledProperty() { return cardPaymentEnabled; }
    public boolean isCardPaymentEnabled() { return cardPaymentEnabled.get(); }
    public void setCardPaymentEnabled(boolean value) { cardPaymentEnabled.set(value); }

    public StringProperty cardPaymentApiKeyProperty() { return cardPaymentApiKey; }
    public String getCardPaymentApiKey() { return cardPaymentApiKey.get(); }
    public void setCardPaymentApiKey(String value) { cardPaymentApiKey.set(value); }

    // General Settings
    public StringProperty maxImageSizeProperty() { return maxImageSize; }
    public String getMaxImageSize() { return maxImageSize.get(); }
    public void setMaxImageSize(String value) { maxImageSize.set(value); }

    public StringProperty contactEmailProperty() { return contactEmail; }
    public String getContactEmail() { return contactEmail.get(); }
    public void setContactEmail(String value) { contactEmail.set(value); }

}
