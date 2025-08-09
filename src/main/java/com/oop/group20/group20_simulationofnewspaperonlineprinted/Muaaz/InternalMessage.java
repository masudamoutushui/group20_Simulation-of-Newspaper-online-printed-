package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternalMessage {

    private final StringProperty sender;
    private final StringProperty recipient;
    private final StringProperty subject;
    private final StringProperty content;
    private final ObjectProperty<LocalDateTime> timestamp;
    private final StringProperty formattedTimestamp; // For display in TableView

    public InternalMessage(String sender, String recipient, String subject, String content, LocalDateTime timestamp) {
        this.sender = new SimpleStringProperty(sender);
        this.recipient = new SimpleStringProperty(recipient);
        this.subject = new SimpleStringProperty(subject);
        this.content = new SimpleStringProperty(content);
        this.timestamp = new SimpleObjectProperty<>(timestamp);
        // Formatter for a user-friendly date/time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formattedTimestamp = new SimpleStringProperty(timestamp.format(formatter));
    }

    // Property getters for TableView
    public StringProperty senderProperty() { return sender; }
    public StringProperty subjectProperty() { return subject; }
    public StringProperty recipientProperty() { return recipient; }
    public StringProperty formattedTimestampProperty() { return formattedTimestamp; }

    // Standard getters
    public String getSender() { return sender.get(); }
    public String getRecipient() { return recipient.get(); }
    public String getSubject() { return subject.get(); }
    public String getContent() { return content.get(); }
    public LocalDateTime getTimestamp() { return timestamp.get(); }
}