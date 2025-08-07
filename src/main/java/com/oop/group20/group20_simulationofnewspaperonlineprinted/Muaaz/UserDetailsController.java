package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;



import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
