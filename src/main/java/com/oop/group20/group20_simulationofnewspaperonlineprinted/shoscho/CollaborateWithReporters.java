package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class CollaborateWithReporters {

    @FXML
    private Button selectPhotoOnAction;

    @FXML
    private Button collaborateOnAction;

    @FXML
    private Button sharingPhotoToReporters;

    @FXML
    private TextArea textalart;

    private ReporterCollaboration reporterCollaboration;

    @FXML
    public void initialize() {

        reporterCollaboration = new ReporterCollaboration("", "");
    }

    @FXML
    private void selectPhotoOnAction() {
        reporterCollaboration.setPhoto("Photo selected by photojournalist");
        textalart.appendText(reporterCollaboration.toString() + "\n");
    }

    @FXML
    private void collaborateOnAction() {
        reporterCollaboration.setAction("Collaboration started with reporters");
        textalart.appendText(reporterCollaboration.toString() + "\n");
    }

    @FXML
    private void sharingPhotoToReporters() {
        reporterCollaboration.setAction("Photo shared with reporters");
        textalart.appendText(reporterCollaboration.toString() + "\n");
    }

    public static class ReporterCollaboration {
        private String photo;
        private String action;

        public ReporterCollaboration(String photo, String action) {
            this.photo = photo;
            this.action = action;
        }


        public String getPhoto() {
            return photo;
        }

        public String getAction() {
            return action;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setAction(String action) {
            this.action = action;
        }

        @Override
        public String toString() {
            return "ReporterCollaboration{" +
                    "photo='" + photo + '\'' +
                    ", action='" + action + '\'' +
                    '}';
        }
    }
}
