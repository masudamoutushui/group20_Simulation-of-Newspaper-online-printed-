package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TrackArticleStatus {

    @FXML
    private Button ViewSubmissionList;

    @FXML
    private Button ReviewNotesOnAction;

    @FXML
    private Button UpdatePlanOnAction;

    @FXML
    private Button CheckApprovalStatus;

    private List<Article> articleList = new ArrayList<>();
    private Article selectedArticle;

    @FXML
    public void initialize() {
        articleList.add(new Article("Article1", "Pending", "Needs more references"));
        articleList.add(new Article("Article2", "Approved", "Well-written"));
    }

    @FXML
    private void ViewSubmissionList() {
        System.out.println("Submission List:");
        for (Article a : articleList) {
            System.out.println(a);
        }
        if (!articleList.isEmpty()) {
            selectedArticle = articleList.get(0);
        }
    }

    @FXML
    private void ReviewNotesOnAction() {
        if (selectedArticle != null) {
            System.out.println("Reviewing Notes for: " + selectedArticle.getTitle());
            System.out.println("Notes: " + selectedArticle.getNotes());
        }
    }

    @FXML
    private void UpdatePlanOnAction() {
        if (selectedArticle != null) {
            selectedArticle.setPlan("Updated Plan Example");
            System.out.println("Updated Plan for Article: " + selectedArticle);
        }
    }

    public static class Article {
        private String title;
        private String status;
        private String notes;
        private String plan;

        public Article(String title, String status, String notes) {
            this.title = title;
            this.status = status;
            this.notes = notes;
            this.plan = "";
        }

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }

        public String getNotes() {
            return notes;
        }

        public String getPlan() {
            return plan;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "title='" + title + '\'' +
                    ", status='" + status + '\'' +
                    ", notes='" + notes + '\'' +
                    ", plan='" + plan + '\'' +
                    '}';
        }
    }
}
