package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ScheduleArticlesForPublishing {

    @FXML
    private Button selectarticleOnAction;

    @FXML
    private Button submitArticleOnAction;

    private List<Article> articleList = new ArrayList<>();
    private Article selectedArticle;

    @FXML
    public void initialize() {
        articleList.add(new Article("Breaking News", "Content of breaking news", false));
        articleList.add(new Article("Sports Update", "Content of sports update", false));
    }

    @FXML
    private void selectarticleOnAction() {
        if (!articleList.isEmpty()) {
            selectedArticle = articleList.get(0);
            System.out.println("Selected Article: " + selectedArticle);
        }
    }

    @FXML
    private void submitArticleOnAction() {
        if (selectedArticle != null) {
            selectedArticle.setSubmitted(true);
            System.out.println("Submitted Article: " + selectedArticle);
        }
    }
    public static class Article {
        private String title;
        private String content;
        private boolean submitted;

        public Article(String title, String content, boolean submitted) {
            this.title = title;
            this.content = content;
            this.submitted = submitted;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public boolean isSubmitted() {
            return submitted;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setSubmitted(boolean submitted) {
            this.submitted = submitted;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", submitted=" + submitted +
                    '}';
        }
    }
}
