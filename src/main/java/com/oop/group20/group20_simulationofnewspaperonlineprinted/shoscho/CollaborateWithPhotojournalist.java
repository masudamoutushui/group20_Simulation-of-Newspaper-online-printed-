package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CollaborateWithPhotojournalist {

    @FXML
    private Button requestPhotosOnAction;

    @FXML
    private Button AttachMedia;

    @FXML
    private Button ReviewContent;

    @FXML
    private Button FinalizeOnAction;

    private CollaborationData collaborationData;

    @FXML
    public void initialize() {
        collaborationData = new CollaborationData("", "");
    }

    @FXML
    private void requestPhotosOnAction() {
        collaborationData.setRequest("Requested photos from photojournalist");
        System.out.println(collaborationData.toString());
    }

    @FXML
    private void AttachMedia() {
        collaborationData.setMedia("Media attached to article");
        System.out.println(collaborationData.toString());
    }

    @FXML
    private void ReviewContent() {
        collaborationData.setRequest("Content reviewed with team");
        System.out.println(collaborationData.toString());
    }

    @FXML
    private void FinalizeOnAction() {
        collaborationData.setRequest("Article finalized and submitted");
        System.out.println(collaborationData.toString());
    }

    public static class CollaborationData {
        private String request;
        private String media;

        public CollaborationData(String request, String media) {
            this.request = request;
            this.media = media;
        }

        public String getRequest() {
            return request;
        }

        public String getMedia() {
            return media;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        @Override
        public String toString() {
            return "CollaborationData{" +
                    "request='" + request + '\'' +
                    ", media='" + media + '\'' +
                    '}';
        }
    }
}
