package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;
import java.io.*;

public class ScheduledArticle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Article article;  // Article is already Serializable

    // transient JavaFX properties
    private transient StringProperty publicationDate;
    private transient StringProperty medium;
    private transient StringProperty priority;

    // backup String fields for serialization
    private String publicationDateStr;
    private String mediumStr;
    private String priorityStr;

    public ScheduledArticle(Article article, String publicationDate, String medium, String priority) {
        this.article = article;
        this.publicationDate = new SimpleStringProperty(publicationDate);
        this.medium = new SimpleStringProperty(medium);
        this.priority = new SimpleStringProperty(priority);

        this.publicationDateStr = publicationDate;
        this.mediumStr = medium;
        this.priorityStr = priority;
    }

    // Properties
    public StringProperty publicationDateProperty() {
        if (publicationDate == null) {
            publicationDate = new SimpleStringProperty(publicationDateStr);
        }
        return publicationDate;
    }

    public StringProperty mediumProperty() {
        if (medium == null) {
            medium = new SimpleStringProperty(mediumStr);
        }
        return medium;
    }

    public StringProperty priorityProperty() {
        if (priority == null) {
            priority = new SimpleStringProperty(priorityStr);
        }
        return priority;
    }

    // Getters
    public Article getArticle() { return article; }
    public String getPublicationDate() { return publicationDateProperty().get(); }
    public String getMedium() { return mediumProperty().get(); }
    public String getPriority() { return priorityProperty().get(); }

    // Custom serialization methods
    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Sync properties to string fields before serialization
        publicationDateStr = getPublicationDate();
        mediumStr = getMedium();
        priorityStr = getPriority();

        oos.defaultWriteObject();
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        // Rebuild transient JavaFX properties after deserialization
        publicationDate = new SimpleStringProperty(publicationDateStr);
        medium = new SimpleStringProperty(mediumStr);
        priority = new SimpleStringProperty(priorityStr);
    }
}
