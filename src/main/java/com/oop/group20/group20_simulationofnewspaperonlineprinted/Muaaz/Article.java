package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // transient because JavaFX properties are not serializable
    private transient StringProperty id;
    private transient StringProperty title;
    private transient StringProperty author;
    private transient StringProperty category;
    private transient StringProperty publishDate;

    // Serializable fields
    private String idStr;
    private String titleStr;
    private String authorStr;
    private String categoryStr;
    private String publishDateStr;

    private String content;
    private String status;   // Pending / Approved / Rejected
    private String reason;   // Reason for rejection

    // Constructor for new articles (defaults to Pending)
    public Article(String id, String title, String author, String category, String publishDate, String content) {
        this(id, title, author, category, publishDate, content, "Pending", "");
    }

    // Constructor for articles with status and reason
    public Article(String id, String title, String author, String category, String publishDate,
                   String content, String status, String reason) {
        this.idStr = id;
        this.titleStr = title;
        this.authorStr = author;
        this.categoryStr = category;
        this.publishDateStr = publishDate;

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
    public StringProperty idProperty() {
        if (id == null) id = new SimpleStringProperty(idStr);
        return id;
    }

    public StringProperty titleProperty() {
        if (title == null) title = new SimpleStringProperty(titleStr);
        return title;
    }

    public StringProperty authorProperty() {
        if (author == null) author = new SimpleStringProperty(authorStr);
        return author;
    }

    public StringProperty categoryProperty() {
        if (category == null) category = new SimpleStringProperty(categoryStr);
        return category;
    }

    public StringProperty publishDateProperty() {
        if (publishDate == null) publishDate = new SimpleStringProperty(publishDateStr);
        return publishDate;
    }

    // Getters
    public String getId() {
        return idProperty().get();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public String getAuthor() {
        return authorProperty().get();
    }

    public String getCategory() {
        return categoryProperty().get();
    }

    public String getPublishDate() {
        return publishDateProperty().get();
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    // Setters
    public void setTitle(String title) {
        titleProperty().set(title);
        this.titleStr = title;
    }

    public void setAuthor(String author) {
        authorProperty().set(author);
        this.authorStr = author;
    }

    public void setCategory(String category) {
        categoryProperty().set(category);
        this.categoryStr = category;
    }

    public void setPublishDate(String publishDate) {
        publishDateProperty().set(publishDate);
        this.publishDateStr = publishDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // Custom serialization to handle transient JavaFX properties
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Sync StringProperty values with String fields
        idStr = getId();
        titleStr = getTitle();
        authorStr = getAuthor();
        categoryStr = getCategory();
        publishDateStr = getPublishDate();

        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        // Recreate JavaFX properties from Strings
        id = new SimpleStringProperty(idStr);
        title = new SimpleStringProperty(titleStr);
        author = new SimpleStringProperty(authorStr);
        category = new SimpleStringProperty(categoryStr);
        publishDate = new SimpleStringProperty(publishDateStr);
    }
}
