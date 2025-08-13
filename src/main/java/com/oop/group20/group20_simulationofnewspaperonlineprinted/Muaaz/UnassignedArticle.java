package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UnassignedArticle {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty category;
    private final StringProperty publishDate;

    public UnassignedArticle(String id, String title, String category, String publishDate) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public StringProperty publishDateProperty() {
        return publishDate;
    }

    public String getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getPublishDate() {
        return publishDate.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public void setPublishDate(String publishDate) {
        this.publishDate.set(publishDate);
    }
}
