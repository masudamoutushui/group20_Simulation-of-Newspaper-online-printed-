package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScheduledArticle {
    private final Article article; // Composition: base article
    private final StringProperty publicationDate;
    private final StringProperty medium;    // e.g. "Print" or "Online"
    private final StringProperty priority;  // e.g. "High", "Medium", "Low"

    public ScheduledArticle(Article article, String publicationDate, String medium, String priority) {
        this.article = article;
        this.publicationDate = new SimpleStringProperty(publicationDate);
        this.medium = new SimpleStringProperty(medium);
        this.priority = new SimpleStringProperty(priority);
    }

    // Properties for TableView binding
    public StringProperty publicationDateProperty() { return publicationDate; }
    public StringProperty mediumProperty() { return medium; }
    public StringProperty priorityProperty() { return priority; }

    // Getters
    public Article getArticle() { return article; }
    public String getPublicationDate() { return publicationDate.get(); }
    public String getMedium() { return medium.get(); }
    public String getPriority() { return priority.get(); }

    // You can add setters if you want to allow editing
}
