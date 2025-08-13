package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PublishingSchedule {
    private final StringProperty articleTitle;
    private final StringProperty publishDate;
    private final StringProperty edition;
    private final StringProperty status;

    public PublishingSchedule(String articleTitle, String publishDate, String edition, String status) {
        this.articleTitle = new SimpleStringProperty(articleTitle);
        this.publishDate = new SimpleStringProperty(publishDate);
        this.edition = new SimpleStringProperty(edition);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty articleTitleProperty() {
        return articleTitle;
    }

    public StringProperty publishDateProperty() {
        return publishDate;
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getArticleTitle() {
        return articleTitle.get();
    }

    public String getPublishDate() {
        return publishDate.get();
    }

    public String getEdition() {
        return edition.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle.set(articleTitle);
    }

    public void setPublishDate(String publishDate) {
        this.publishDate.set(publishDate);
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
