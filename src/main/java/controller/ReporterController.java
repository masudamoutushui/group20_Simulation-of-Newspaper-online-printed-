package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReporterController {

    public void switchToPhotojournalistBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/oop/group20/group20_simulationofnewspaperonlineprinted/photojournalist.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Photojournalist Dashboard");
        stage.show();
    }

    // Placeholder methods for other buttons
    public void submitArticleBtn(ActionEvent event) {}
    public void editArticleBtn(ActionEvent event) {}
    public void resubmitArticleBtn(ActionEvent event) {}
}
