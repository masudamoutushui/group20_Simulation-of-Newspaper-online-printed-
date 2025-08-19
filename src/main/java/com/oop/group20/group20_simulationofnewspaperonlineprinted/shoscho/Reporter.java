package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import java.time.LocalDate;

public class Reporter {

    private String articleTitle;
    private String articleContent;
    private LocalDate publishDate;
    private String reference;
    private String status;

    public Reporter() {
        this.articleTitle = "";
        this.articleContent = "";
        this.publishDate = LocalDate.now();
        this.reference = "";
        this.status = "Pending";
    }

    public Reporter(String articleTitle, String articleContent, LocalDate publishDate, String reference, String status) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.publishDate = publishDate;
        this.reference = reference;
        this.status = status;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @FXML
    public void initialize() {
        System.out.println("Reporter initialized.");
    }
}
