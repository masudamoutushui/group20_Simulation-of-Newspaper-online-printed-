package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class DeletePreviouslyUploadedphoto {

    @FXML
    private Button openGallery;

    @FXML
    private Button Delete;

    @FXML
    private Button ApplyAction;

    @FXML
    private Button updateDisplay;

    private List<UploadedPhoto> gallery = new ArrayList<>();

    @FXML
    public void initialize() {
        gallery.add(new UploadedPhoto("Photo1", true));
        gallery.add(new UploadedPhoto("Photo2", true));
    }

    @FXML
    private void openGallery() {
        System.out.println("Gallery Opened: " + gallery.toString());
    }

    @FXML
    private void Delete() {
        if (!gallery.isEmpty()) {
            UploadedPhoto removed = gallery.remove(0);
            System.out.println("Deleted: " + removed);
        }
    }

    @FXML
    private void ApplyAction() {
        for (UploadedPhoto photo : gallery) {
            photo.setActive(!photo.isActive());
        }
        System.out.println("Applied Action: " + gallery.toString());
    }

    @FXML
    private void updateDisplay() {
        System.out.println("Display Updated: " + gallery.toString());
    }
    public static class UploadedPhoto {
        private String name;
        private boolean active;

        public UploadedPhoto(String name, boolean active) {
            this.name = name;
            this.active = active;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return active;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public String toString() {
            return "UploadedPhoto{" +
                    "name='" + name + '\'' +
                    ", active=" + active +
                    '}';
        }
    }
}
