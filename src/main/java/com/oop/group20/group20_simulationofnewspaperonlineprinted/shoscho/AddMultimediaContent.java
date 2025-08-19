package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AddMultimediaContent {

    public static class MultimediaContent {
        private String filePath;
        private String contentType;

        public MultimediaContent(String filePath, String contentType) {
            this.filePath = filePath;
            this.contentType = contentType;
        }

        public MultimediaContent() {
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public String toString() {
            return "MultimediaContent{" +
                    "filePath='" + filePath + '\'' +
                    ", contentType='" + contentType + '\'' +
                    '}';
        }
    }

    @FXML
    private ComboBox<String> contentType;


    private File selectedFile;

    @FXML
    public void initialize() {
        contentType.setItems(FXCollections.observableArrayList(
                "Image", "Video", "Audio", "Document"
        ));
    }

    @FXML
    private void selectMediaOnAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Videos", "*.mp4", "*.avi", "*.mkv"),
                new FileChooser.ExtensionFilter("Audio", "*.mp3", "*.wav"),
                new FileChooser.ExtensionFilter("Documents", "*.pdf", "*.docx", "*.txt")
        );

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Selected File: " + selectedFile.getName(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void openMediaOnAction() {
        if (selectedFile != null && selectedFile.exists()) {
            try {
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Failed to open file!", ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "No media file selected!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void saveDraftonaction() {
        if (selectedFile == null || contentType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please select media and content type before saving draft.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        MultimediaContent draft = new MultimediaContent(selectedFile.getAbsolutePath(), contentType.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Draft Saved!\n" + draft, ButtonType.OK);
        alert.showAndWait();
    }
}
