package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDetailsController {

    @FXML private Label lblName;
    @FXML private Label lblUsername;
    @FXML private Label lblUserType;
    @FXML private Label lblDOB;
    @FXML private Label lblJoiningDate;
    @FXML private Label lblAddress;

    public void setUserData(String name, String username, String userType, String dob, String joiningDate, String address) {
        lblName.setText("Name: " + name);
        lblUsername.setText("Username: " + username);
        lblUserType.setText("User Type: " + userType);
        lblDOB.setText("Date of Birth: " + dob);
        lblJoiningDate.setText("Joining Date: " + joiningDate);
        lblAddress.setText("Address: " + address);
    }

    @FXML
    private void handleReviewArticleClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Review Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void assignArticleOnClick(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Review Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @FXML
    public void setScheduleOnAction(ActionEvent actionEvent) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal3.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Review Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}
