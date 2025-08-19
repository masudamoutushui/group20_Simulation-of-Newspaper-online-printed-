package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;

public class AddCopyrightAndAttributeInfo {

    public static class PhotoInfo {
        private String photo;
        private String copyright;
        private String attribute;

        public PhotoInfo(String photo, String copyright, String attribute) {
            this.photo = photo;
            this.copyright = copyright;
            this.attribute = attribute;
        }

        public PhotoInfo() {
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        @Override
        public String toString() {
            return "PhotoInfo{" +
                    "photo='" + photo + '\'' +
                    ", copyright='" + copyright + '\'' +
                    ", attribute='" + attribute + '\'' +
                    '}';
        }
    }

    @FXML
    private TableView<PhotoInfo> tableview;
    @FXML
    private TableColumn<PhotoInfo, String> photoColumn;
    @FXML
    private TableColumn<PhotoInfo, String> copyrightcolumn;
    @FXML
    private TableColumn<PhotoInfo, String> attributecolumn;
    @FXML
    private TextField addCopyRight;

    private final ObservableList<PhotoInfo> data = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        copyrightcolumn.setCellValueFactory(new PropertyValueFactory<>("copyright"));
        attributecolumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));

        tableview.setItems(data);
    }


    @FXML
    private void selectPhotoOnAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            data.add(new PhotoInfo(selectedFile.getName(), addCopyRight.getText(), "N/A"));
        }
    }

    @FXML
    private void addAttributeonAction() {
        PhotoInfo selected = tableview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setAttribute("Attribute Added");
            tableview.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select a row to add attribute!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
