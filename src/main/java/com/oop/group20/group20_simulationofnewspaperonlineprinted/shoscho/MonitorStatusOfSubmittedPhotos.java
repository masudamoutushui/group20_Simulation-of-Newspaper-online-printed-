package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class MonitorStatusOfSubmittedPhotos {

    @FXML
    private Button SelectPhotoOnAction;

    @FXML
    private Button viewStatus;

    @FXML
    private TextArea statusfield;

    @FXML
    private TextArea showStatus;

    private List<PhotoSubmission> submissions = new ArrayList<>();
    private PhotoSubmission selectedPhoto;

    @FXML
    public void initialize() {
        submissions.add(new PhotoSubmission("Photo1.jpg", "Pending"));
        submissions.add(new PhotoSubmission("Photo2.jpg", "Approved"));
        submissions.add(new PhotoSubmission("Photo3.jpg", "Rejected"));
    }

    @FXML
    private void SelectPhotoOnAction() {
        if (!submissions.isEmpty()) {
            selectedPhoto = submissions.get(0);
            statusfield.setText(selectedPhoto.toString());
            System.out.println("Selected Photo: " + selectedPhoto);
        }
    }

    @FXML
    private void viewStatus() {
        if (selectedPhoto != null) {
            showStatus.setText(selectedPhoto.getStatus());
            System.out.println("Photo Status: " + selectedPhoto.getStatus());
        }
    }

    public static class PhotoSubmission {
        private String photoName;
        private String status;

        public PhotoSubmission(String photoName, String status) {
            this.photoName = photoName;
            this.status = status;
        }

        public String getPhotoName() {
            return photoName;
        }

        public String getStatus() {
            return status;
        }

        public void setPhotoName(String photoName) {
            this.photoName = photoName;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "PhotoSubmission{" +
                    "photoName='" + photoName + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
