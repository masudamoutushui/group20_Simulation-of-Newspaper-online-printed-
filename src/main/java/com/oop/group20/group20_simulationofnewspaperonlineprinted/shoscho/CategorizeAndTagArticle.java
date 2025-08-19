package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CategorizeAndTagArticle {

    @FXML
    private Button chooseCategoryOnAction;

    @FXML
    private Button saveMetadataOnAction;

    @FXML
    private Button refreshViewOnAction;

    @FXML
    private TextField addTags;

    private ArticleMetadata metadata;

    @FXML
    public void initialize() {
        metadata = new ArticleMetadata("", "");
    }

    @FXML
    private void chooseCategoryOnAction() {

        metadata.setCategory("News");
        System.out.println("Category chosen: " + metadata.getCategory());
    }

    @FXML
    private void saveMetadataOnAction() {
        metadata.setTags(addTags.getText());
        System.out.println("Metadata saved: " + metadata.toString());
    }

    @FXML
    private void refreshViewOnAction() {
        addTags.clear();
        metadata = new ArticleMetadata("", "");
        System.out.println("View refreshed, metadata cleared.");
    }

    public static class ArticleMetadata {
        private String category;
        private String tags;

        public ArticleMetadata(String category, String tags) {
            this.category = category;
            this.tags = tags;
        }

        public String getCategory() {
            return category;
        }

        public String getTags() {
            return tags;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "ArticleMetadata{" +
                    "category='" + category + '\'' +
                    ", tags='" + tags + '\'' +
                    '}';
        }
    }
}
