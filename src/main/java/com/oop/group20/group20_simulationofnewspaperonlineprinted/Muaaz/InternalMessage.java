package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InternalMessage {

    private final StringProperty sender;
    private final StringProperty recipient;
    private final StringProperty subject;
    private final StringProperty content;
    private final StringProperty timestamp;

    public InternalMessage(String sender, String recipient, String subject, String content, String timestamp) {
        this.sender = new SimpleStringProperty(sender);
        this.recipient = new SimpleStringProperty(recipient);
        this.subject = new SimpleStringProperty(subject);
        this.content = new SimpleStringProperty(content);
        this.timestamp = new SimpleStringProperty(timestamp);
    }

    // Getters and property getters for TableView binding
    public String getSender() {
        return sender.get();
    }
    public StringProperty senderProperty() {
        return sender;
    }

    public String getRecipient() {
        return recipient.get();
    }
    public StringProperty recipientProperty() {
        return recipient;
    }

    public String getSubject() {
        return subject.get();
    }
    public StringProperty subjectProperty() {
        return subject;
    }

    public String getContent() {
        return content.get();
    }
    public StringProperty contentProperty() {
        return content;
    }

    public String getTimestamp() {
        return timestamp.get();
    }
    public StringProperty timestampProperty() {
        return timestamp;
    }

    // You can add setters if you want the messages to be editable, otherwise keep immutable
}
