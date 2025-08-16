package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController
{
    @javafx.fxml.FXML
    public void initialize() {
    }
    // Logged-in user reference
    private RegisteredUser user;


    // Setter for logged-in user
    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void PreviousOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/IT Admin.fxml"));
        Parent root = loader.load();

        // Pass the current logged-in user to ITAdminController
        ITAdminController controller = loader.getController();
        controller.setUser(this.user); // pass the logged-in user

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

}