package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ITAdminController {

    @FXML private Label ShowName;
    @FXML private Label Showusername;
    @FXML private Label Showusetype;
    @FXML private Label Showaddress;
    @FXML private Label showDateOfBirth;
    @FXML private Label showjoiningdate;

    private RegisteredUser user;

    // Called from LogInController after login
    public void setUser(RegisteredUser user) {
        this.user = user;
        loadUserDetails();
    }

    private void loadUserDetails() {
        if (user != null) {
            ShowName.setText(user.getName());
            Showusername.setText(user.getUsername());
            Showusetype.setText(user.getUserType());
            Showaddress.setText(user.getAddress());
            showDateOfBirth.setText(user.getDob() != null ? user.getDob().toString() : "");
            showjoiningdate.setText(user.getJoiningDate() != null ? user.getJoiningDate().toString() : "");
        }
    }

    // ✅ Generic scene loader that passes `user` to the next controller if available
    private void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Object controller = loader.getController();
            // Inject current user if target controller has setUser method
            try {
                controller.getClass().getMethod("setUser", RegisteredUser.class).invoke(controller, user);
            } catch (NoSuchMethodException ignored) {
                // Controller does not need user object
            }

            Stage stage = (Stage) ShowName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    // ----------------- Button Handlers -----------------

    @FXML
    public void manageCommentFromReaderOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/ContentModeration.fxml", "Manage Comments");
    }

    @FXML
    public void AnnouncementSceneOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Announcement.fxml", "Create Announcement");
    }

    @FXML
    public void LogoutOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Log in.fxml", "Login");
    }

    @FXML
    public void ConfigureSettingsSceneOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/settings.fxml", "Change Settings");
    }

    @FXML
    public void ManageUserSceneOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/RoleManagement.fxml", "Manage Users");
    }

    @FXML
    public void EditProfileOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/Profile.fxml", "Edit Profile");
    }

    @FXML
    public void ManageAdvertisementDealOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/AdpartnerManagement.fxml", "Manage Advertisement Deals");
    }

    @FXML
    public void ReportOnAction(ActionEvent actionEvent) {
        loadScene("/fxml/manage_users.fxml", "Reports");
    }

    @FXML
    public void AnalyticsOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/AnalyticsDashboardAdmin.fxml", "Analytics Dashboard");
    }

    @FXML
    public void ManagePublishedArticleOnAction(ActionEvent actionEvent) {
        loadScene("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/PublishedArticleManagement.fxml", "Manage Published Articles");
    }
}
