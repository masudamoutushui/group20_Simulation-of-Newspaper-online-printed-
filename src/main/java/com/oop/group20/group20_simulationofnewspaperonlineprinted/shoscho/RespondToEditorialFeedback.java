package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class RespondToEditorialFeedback {

    @FXML
    private Button OpenFeedbackSection;

    @FXML
    private Button ApplyChanges;

    @FXML
    private Button ResubmitArticle;

    @FXML
    private Button RefreshTable;

    private List<Feedback> feedbackList = new ArrayList<>();
    private Feedback selectedFeedback;

    @FXML
    public void initialize() {
        feedbackList.add(new Feedback("Article1", "Fix typos", false));
        feedbackList.add(new Feedback("Article2", "Add more images", false));
    }

    @FXML
    private void OpenFeedbackSection() {
        if (!feedbackList.isEmpty()) {
            selectedFeedback = feedbackList.get(0);
            System.out.println("Opened Feedback: " + selectedFeedback);
        }
    }

    @FXML
    private void ApplyChanges() {
        if (selectedFeedback != null) {
            selectedFeedback.setApplied(true);
            System.out.println("Applied Changes to: " + selectedFeedback);
        }
    }

    @FXML
    private void ResubmitArticle() {
        if (selectedFeedback != null) {
            System.out.println("Resubmitted Article: " + selectedFeedback.getArticleName());
        }
    }

    @FXML
    private void RefreshTable() {
        System.out.println("Refreshing Feedback Table...");
        feedbackList.forEach(System.out::println);
    }

    public static class Feedback {
        private String articleName;
        private String comment;
        private boolean applied;

        public Feedback(String articleName, String comment, boolean applied) {
            this.articleName = articleName;
            this.comment = comment;
            this.applied = applied;
        }

        public String getArticleName() {
            return articleName;
        }

        public String getComment() {
            return comment;
        }

        public boolean isApplied() {
            return applied;
        }

        public void setArticleName(String articleName) {
            this.articleName = articleName;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setApplied(boolean applied) {
            this.applied = applied;
        }

        @Override
        public String toString() {
            return "Feedback{" +
                    "articleName='" + articleName + '\'' +
                    ", comment='" + comment + '\'' +
                    ", applied=" + applied +
                    '}';
        }
    }
}
