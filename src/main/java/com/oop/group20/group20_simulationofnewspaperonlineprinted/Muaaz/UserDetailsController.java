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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal1.fxml"));
            Parent root = loader.load();

            // Pass the logged-in user to Goal1Controller
            Goal1Controller controller = loader.getController();
            controller.setUser(this.currentUser);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Review Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void assignArticleOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal2.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            Goaltwo controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setScheduleOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal3.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            EditorInChiefGoal3 controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void AnalysisOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal7.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            Goal7 controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CheckArticleOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Check.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            CheckController controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void ReportOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal8.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            EditorInChiefGoal8 controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadScene(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/" + fxmlFile));
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
    public void CommunicateOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal6.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            Goal6Controller controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void assigntaskOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/EditorInChiefGoal5.fxml"));
            Parent root = loader.load();

            // Get the correct controller for Check.fxml
            Goal5Controller controller = loader.getController();
            controller.setUser(this.currentUser);  // pass logged-in user

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Check Articles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------ Static helper ------------------
    /**
     * Opens the UserDetails scene with the given logged-in user.
     * Can be used from any controller.
     */
    public static void openWithUser(RegisteredUser user, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(UserDetailsController.class.getResource(
                "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/UserDetails.fxml"));
        Parent root = loader.load();

        UserDetailsController controller = loader.getController();
        controller.setUser(user);

        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }
}
