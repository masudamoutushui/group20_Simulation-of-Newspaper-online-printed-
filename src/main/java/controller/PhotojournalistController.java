package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotojournalistController {

    public void switchToReporterBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/reporter.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Reporter Dashboard");
        stage.show();
    }

    // Placeholder methods for other buttons
    public void capturePhotoBtn(ActionEvent event) {}
    public void addMetadataBtn(ActionEvent event) {}
    public void submitPhotoBtn(ActionEvent event) {}
    public void editPhotoBtn(ActionEvent event) {}
    public void deletePhotoBtn(ActionEvent event) {}
    public void addCopyrightBtn(ActionEvent event) {}
    public void collaborateBtn(ActionEvent event) {}
    public void monitorStatusBtn(ActionEvent event) {}
}
