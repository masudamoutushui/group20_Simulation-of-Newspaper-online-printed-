package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class EditPreviouslyWrittenArticle {

    @FXML
    private Button OpenDraftsOnAction;

    @FXML
    private Button SelectArticleOnAction;

    @FXML
    private Button MakeChangesOnAction;

    @FXML
    private Button SaveEditsOnAction;

    private List<ArticleDraft> drafts = new ArrayList<>();
    private ArticleDraft selectedArticle;

    @FXML
    public void initialize() {
        drafts.add(new ArticleDraft("Draft 1", "This is the first draft."));
        drafts.add(new ArticleDraft("Draft 2", "This is the second draft."));
    }

    @FXML
    private void OpenDraftsOnAction() {
        System.out.println("Drafts Opened: " + drafts.toString());
    }

    @FXML
    private void SelectArticleOnAction() {
        if (!drafts.isEmpty()) {
            selectedArticle = drafts.get(0); // example: select first draft
            System.out.println("Selected Article: " + selectedArticle);
        }
    }

    @FXML
    private void MakeChangesOnAction() {
        if (selectedArticle != null) {
            selectedArticle.setContent(selectedArticle.getContent() + " [Edited]");
            System.out.println("Article Edited: " + selectedArticle);
        }
    }

    @FXML
    private void SaveEditsOnAction() {
        if (selectedArticle != null) {
            System.out.println("Edits Saved: " + selectedArticle);
        }
    }

    public static class ArticleDraft {
        private String title;
        private String content;


        public ArticleDraft(String title, String content) {
            this.title = title;
            this.content = content;
        }


        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }


        @Override
        public String toString() {
            return "ArticleDraft{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
