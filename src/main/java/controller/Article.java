package controller;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Article {
    private final StringProperty title;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty status;
    private String content;
    private String reference;

    public Article(String title, LocalDate date, String status, String content, String reference) {
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleObjectProperty<>(date);
        this.status = new SimpleStringProperty(status);
        this.content = content;
        this.reference = reference;
    }

    public StringProperty titleProperty() { return title; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
    public StringProperty statusProperty() { return status; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }

    public LocalDate getDate() { return date.get(); }
    public void setDate(LocalDate date) { this.date.set(date); }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}
