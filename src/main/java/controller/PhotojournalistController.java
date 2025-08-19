package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PhotojournalistController {

    @FXML private TableView<Photo> tableView;
    @FXML private TableColumn<Photo, String> colImageName;
    @FXML private TableColumn<Photo, String> colCategory;
    @FXML private TableColumn<Photo, LocalDate> colDate;
    @FXML private TableColumn<Photo, String> colStatus;

    private ObservableList<Photo> photoList = FXCollections.observableArrayList();
    @FXML
    private Text photojournalistTitle;
    @FXML
    private Button switchToReporterBtn;

    @FXML
    public void initialize() {
        colImageName.setCellValueFactory(data -> data.getValue().imageNameProperty());
        colCategory.setCellValueFactory(data -> data.getValue().categoryProperty());
        colDate.setCellValueFactory(data -> data.getValue().dateProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        tableView.setItems(photoList);
    }

    @FXML
    public void handleSwitchToReporter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/com/oop/group20/group20_simulationofnewspaperonlineprinted/shoscho/reporter.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Reporter Dashboard");
        stage.show();
    }


    @FXML
    public void capturePhotoBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Photo photo = new Photo(file.getName(), "Uncategorized", LocalDate.now(), "Draft");
            photoList.add(photo);
        }
    }

    @FXML
    public void addMetadataBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TextInputDialog dialog = new TextInputDialog(selected.getCategory());
            dialog.setTitle("Add Metadata");
            dialog.setHeaderText("Enter category for the photo");
            dialog.setContentText("Category:");
            dialog.showAndWait().ifPresent(selected::setCategory);
        } else {
            showAlert("No Selection", "Please select a photo to add metadata.");
        }
    }

    @FXML
    public void submitPhotoBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Submitted");
            tableView.refresh();
        } else {
            showAlert("No Selection", "Please select a photo to submit.");
        }
    }

    @FXML
    public void editPhotoBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TextInputDialog dialog = new TextInputDialog(selected.getImageName());
            dialog.setTitle("Edit Photo Name");
            dialog.setHeaderText("Change the name of the photo");
            dialog.setContentText("New Name:");
            dialog.showAndWait().ifPresent(selected::setImageName);
            tableView.refresh();
        } else {
            showAlert("No Selection", "Please select a photo to edit.");
        }
    }

    @FXML
    public void deletePhotoBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            photoList.remove(selected);
        } else {
            showAlert("No Selection", "Please select a photo to delete.");
        }
    }

    @FXML
    public void addCopyrightBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setImageName(selected.getImageName() + " ©");
            tableView.refresh();
        } else {
            showAlert("No Selection", "Please select a photo to add copyright.");
        }
    }

    @FXML
    public void collaborateBtn(ActionEvent event) {
        showAlert("Collaborate", "Photo shared with collaborators!");
    }

    @FXML
    public void monitorStatusBtn(ActionEvent event) {
        Photo selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showAlert("Photo Status", "Current status: " + selected.getStatus());
        } else {
            showAlert("No Selection", "Please select a photo to monitor.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
