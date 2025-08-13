package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty category;
    private final StringProperty publishDate;
    private String content;
    private String status;   // New: Pending / Approved / Rejected
    private String reason;   // New: Reason for rejection

    // Constructor for new articles (defaults to Pending)
    public Article(String id, String title, String author, String category, String publishDate, String content) {
        this(id, title, author, category, publishDate, content, "Pending", "");
    }

    // Constructor for articles with status and reason
    public Article(String id, String title, String author, String category, String publishDate,
                   String content, String status, String reason) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
        this.content = content;
        this.status = status;
        this.reason = reason;
    }

    // Properties
    public StringProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public StringProperty authorProperty() { return author; }
    public StringProperty categoryProperty() { return category; }
    public StringProperty publishDateProperty() { return publishDate; }

    // Getters
    public String getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public String getAuthor() { return author.get(); }
    public String getCategory() { return category.get(); }
    public String getPublishDate() { return publishDate.get(); }
    public String getContent() { return content; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }

    // Setters
    public void setTitle(String title) { this.title.set(title); }
    public void setAuthor(String author) { this.author.set(author); }
    public void setCategory(String category) { this.category.set(category); }
    public void setPublishDate(String publishDate) { this.publishDate.set(publishDate); }
    public void setContent(String content) { this.content = content; }
    public void setStatus(String status) { this.status = status; }
    public void setReason(String reason) { this.reason = reason; }
}
