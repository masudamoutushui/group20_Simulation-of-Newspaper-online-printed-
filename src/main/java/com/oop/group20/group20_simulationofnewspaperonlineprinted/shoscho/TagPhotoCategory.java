package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TagPhotoCategory {

    @FXML
    private Button SelectUploadedPhotoOnAction;

    @FXML
    private Button EnterCategoryOnAction;

    @FXML
    private Button SaveTagsOnAction;

    @FXML
    private Button UpdateTableOnAction;

    private List<Photo> photoList = new ArrayList<>();
    private Photo selectedPhoto;

    @FXML
    public void initialize() {
        photoList.add(new Photo("Beach.png", ""));
        photoList.add(new Photo("City.png", ""));
    }

    @FXML
    private void SelectUploadedPhotoOnAction() {
        if (!photoList.isEmpty()) {
            selectedPhoto = photoList.get(0);
            System.out.println("Selected Photo: " + selectedPhoto);
        }
    }

    @FXML
    private void EnterCategoryOnAction() {
        if (selectedPhoto != null) {
            selectedPhoto.setCategory("Nature");
            System.out.println("Entered Category for Photo: " + selectedPhoto);
        }
    }

    @FXML
    private void SaveTagsOnAction() {
        if (selectedPhoto != null) {
            selectedPhoto.setTagged(true);
            System.out.println("Saved Tags for Photo: " + selectedPhoto);
        }
    }

    @FXML
    private void UpdateTableOnAction() {
        System.out.println("Photo Table Updated:");
        for (Photo p : photoList) {
            System.out.println(p);
        }
    }

    public static class Photo {
        private String fileName;
        private String category;
        private boolean tagged;


        public Photo(String fileName, String category) {
            this.fileName = fileName;
            this.category = category;
            this.tagged = false;
        }

        public String getFileName() {
            return fileName;
        }

        public String getCategory() {
            return category;
        }

        public boolean isTagged() {
            return tagged;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setTagged(boolean tagged) {
            this.tagged = tagged;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "fileName='" + fileName + '\'' +
                    ", category='" + category + '\'' +
                    ", tagged=" + tagged +
                    '}';
        }
    }
}
