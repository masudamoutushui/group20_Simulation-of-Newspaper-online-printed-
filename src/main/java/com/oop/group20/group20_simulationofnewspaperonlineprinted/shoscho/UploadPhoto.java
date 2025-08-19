package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class UploadPhoto {

    @FXML
    private Button SelectDraftPhotoOnAction;

    @FXML
    private Button AddMetadataOnAction;

    @FXML
    private Button ConfirmSubmissionOnAction;

    @FXML
    private Button ChangeStatusOnAction;

    private List<Photo> photoList = new ArrayList<>();
    private Photo selectedPhoto;

    @FXML
    public void initialize() {
        // Sample photos for demonstration
        photoList.add(new Photo("DraftPhoto1.jpg", "Draft", "No Metadata"));
        photoList.add(new Photo("DraftPhoto2.jpg", "Draft", "No Metadata"));
    }

    @FXML
    private void SelectDraftPhotoOnAction() {
        System.out.println("Draft Photos:");
        for (Photo p : photoList) {
            System.out.println(p);
        }
        if (!photoList.isEmpty()) {
            selectedPhoto = photoList.get(0);
            System.out.println("Selected Photo: " + selectedPhoto);
        }
    }

    @FXML
    private void AddMetadataOnAction() {
        if (selectedPhoto != null) {
            selectedPhoto.setMetadata("Metadata added at " + System.currentTimeMillis());
            System.out.println("Updated Photo with Metadata: " + selectedPhoto);
        }
    }

    @FXML
    private void ConfirmSubmissionOnAction() {
        if (selectedPhoto != null) {
            selectedPhoto.setStatus("Submitted");
            System.out.println("Photo Submitted: " + selectedPhoto);
        }
    }

    @FXML
    private void ChangeStatusOnAction() {
        if (selectedPhoto != null) {
            selectedPhoto.setStatus("Reviewed");
            System.out.println("Photo Status Changed: " + selectedPhoto);
        }
    }

    public static class Photo {
        private String filename;
        private String status;
        private String metadata;


        public Photo(String filename, String status, String metadata) {
            this.filename = filename;
            this.status = status;
            this.metadata = metadata;
        }


        public String getFilename() {
            return filename;
        }

        public String getStatus() {
            return status;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "filename='" + filename + '\'' +
                    ", status='" + status + '\'' +
                    ", metadata='" + metadata + '\'' +
                    '}';
        }
    }
}
