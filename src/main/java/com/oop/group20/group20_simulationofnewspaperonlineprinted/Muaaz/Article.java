package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty category;
    private final StringProperty publishDate;
    private final String content;

    public Article(String id, String title, String author, String category, String publishDate, String content) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
        this.content = content;
    }

    public StringProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public StringProperty authorProperty() { return author; }
    public StringProperty categoryProperty() { return category; }
    public StringProperty publishDateProperty() { return publishDate; }
    public String getId() { return id.get(); }
    public String getContent() { return content; }
}
