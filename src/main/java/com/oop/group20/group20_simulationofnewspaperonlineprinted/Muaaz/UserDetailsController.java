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

    private RegisteredUser currentUser;

    // -------------------- Important --------------------
    // Only called from LoginController after login success
    public void setUser(RegisteredUser user) {
        this.currentUser = user;
        displayUserData(); // now the labels are updated
    }

    private void displayUserData() {
        if (currentUser != null) {
            lblName.setText("Name: " + currentUser.getName());
            lblUsername.setText("Username: " + currentUser.getUsername());
            lblUserType.setText("User Type: " + currentUser.getUserType());
            lblDOB.setText("Date of Birth: " + currentUser.getDob());
            lblJoiningDate.setText("Joining Date: " + currentUser.getJoiningDate());
            lblAddress.setText("Address: " + currentUser.getAddress());
        } else {
            System.err.println("Error: currentUser is null. Did you forget to call setUser()?");
        }
    }

    // ------------------ Navigation methods ------------------
    @FXML
    private void handleReviewArticleClick(ActionEvent event) {
        loadScene(event, "EditorInChiefGoal1.fxml", "Review Articles");
    }

    @FXML
    public void assignArticleOnClick(ActionEvent event) {
        loadScene(event, "EditorInChiefGoal2.fxml", "Assign Article");
    }

    @FXML
    public void setScheduleOnAction(ActionEvent event) {
        loadScene(event, "EditorInChiefGoal3.fxml", "Set Schedule");
    }

    @FXML
    public void AnalysisOnAction(ActionEvent event) {
        loadScene(event, "EditorInChiefGoal7.fxml", "Analysis");
    }

    @FXML
    public void CheckArticleOnAction(ActionEvent event) {
        loadScene(event, "Check.fxml", "Check Articles");
    }

    @FXML
    public void ReportOnAction(ActionEvent event) {
        loadScene(event, "EditorInChiefGoal8.fxml", "Reports");
    }

    private void loadScene(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Empty handlers
    @FXML
    public void CommunicateOnAction(ActionEvent actionEvent) {}
    @FXML
    public void assigntaskOnAction(ActionEvent actionEvent) {}
}
