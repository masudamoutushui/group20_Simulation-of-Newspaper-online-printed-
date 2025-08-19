package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CaptureHighQualityPhoto {

    @FXML
    private TableView<Photo> tableView;

    @FXML
    private TableColumn<Photo, String> namecolumn;

    @FXML
    private TableColumn<Photo, String> datecolumn;

    @FXML
    private TableColumn<Photo, String> status;

    @FXML
    private DatePicker date;

    @FXML
    private Button uploadnterfaceOnAction;

    @FXML
    private Button selectPhotoOnAction;

    @FXML
    private Button saveDraftOnAction;

    private ObservableList<Photo> photoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));


        tableView.setItems(photoList);
    }

    @FXML
    private void uploadnterfaceOnAction() {

        photoList.add(new Photo("Sample Upload", date.getValue() != null ? date.getValue().toString() : "N/A", "Uploaded"));
    }

    @FXML
    private void selectPhotoOnAction() {

        photoList.add(new Photo("Selected Photo", date.getValue() != null ? date.getValue().toString() : "N/A", "Selected"));
    }

    @FXML
    private void saveDraftOnAction() {

        photoList.add(new Photo("Draft Photo", date.getValue() != null ? date.getValue().toString() : "N/A", "Draft Saved"));
    }

    public static class Photo {
        private String name;
        private String date;
        private String status;

        public Photo(String name, String date, String status) {
            this.name = name;
            this.date = date;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getStatus() {
            return status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "name='" + name + '\'' +
                    ", date='" + date + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
