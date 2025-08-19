package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class WriteAndSubmitNewsArticle {

    @FXML
    private Button OpenArticleEditor;

    @FXML
    private Button SubmitDraftOnAction;

    @FXML
    private TextField writeContent;

    @FXML
    private TextField AttachReferences;

    private List<Article> articleList = new ArrayList<>();
    private Article currentArticle;


    public WriteAndSubmitNewsArticle() {

        articleList.add(new Article("Sample Article", "Sample Reference"));
    }

    @FXML
    private void OpenArticleEditor() {
        System.out.println("Article Editor Opened");
        if (currentArticle == null) {
            currentArticle = new Article("", "");
        }
    }

    @FXML
    private void SubmitDraftOnAction() {
        if (currentArticle == null) {
            currentArticle = new Article("", "");
        }
        currentArticle.setContent(writeContent.getText());
        currentArticle.setReferences(AttachReferences.getText());
        articleList.add(currentArticle);
        System.out.println("Draft Submitted: " + currentArticle);
        currentArticle = null; // Reset for next draft
        writeContent.clear();
        AttachReferences.clear();
    }

    public static class Article {
        private String content;
        private String references;

        public Article(String content, String references) {
            this.content = content;
            this.references = references;
        }

        public String getContent() {
            return content;
        }

        public String getReferences() {
            return references;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setReferences(String references) {
            this.references = references;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "content='" + content + '\'' +
                    ", references='" + references + '\'' +
                    '}';
        }
    }

    public TextField getWriteContent() {
        return writeContent;
    }

    public void setWriteContent(TextField writeContent) {
        this.writeContent = writeContent;
    }

    public TextField getAttachReferences() {
        return AttachReferences;
    }

    public void setAttachReferences(TextField attachReferences) {
        this.AttachReferences = attachReferences;
    }
}
