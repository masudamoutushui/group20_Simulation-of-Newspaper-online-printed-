package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateAndPhotoGallery {

    @FXML
    private Button selectMultiplePhotoOnAction;

    @FXML
    private Button saveStoryBoard;

    @FXML
    private TextField caption;

    @FXML
    private TableView<PhotoGallery> tableView;

    @FXML
    private TableColumn<PhotoGallery, String> photocolumn;

    @FXML
    private TableColumn<PhotoGallery, String> captionColumn;

    private ObservableList<PhotoGallery> galleryList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        photocolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        captionColumn.setCellValueFactory(new PropertyValueFactory<>("caption"));

        tableView.setItems(galleryList);
    }

    @FXML
    private void selectMultiplePhotoOnAction() {
        galleryList.add(new PhotoGallery("Photo_" + (galleryList.size() + 1), caption.getText()));
    }

    @FXML
    private void saveStoryBoard() {
        System.out.println("Storyboard saved: " + galleryList.toString());
    }

    public void caption(ActionEvent actionEvent) {
    }

    public static class PhotoGallery {
        private String photo;
        private String caption;

        public PhotoGallery(String photo, String caption) {
            this.photo = photo;
            this.caption = caption;
        }

        public String getPhoto() {
            return photo;
        }

        public String getCaption() {
            return caption;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        @Override
        public String toString() {
            return "PhotoGallery{" +
                    "photo='" + photo + '\'' +
                    ", caption='" + caption + '\'' +
                    '}';
        }
    }
}
