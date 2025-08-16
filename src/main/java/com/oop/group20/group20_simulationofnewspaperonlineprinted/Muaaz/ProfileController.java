package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ProfileController {

    @FXML private TextField nametF;
    @FXML private TextField usertf;
    @FXML private TextField passwordtf;
    @FXML private DatePicker Dateofbirthtf;
    @FXML private DatePicker joiningDateTf;
    @FXML private TextField AddressTf;
    @FXML private Label justshowUserType;

    private RegisteredUser user;

    // Called from ITAdminController after loading this scene
    public void setUser(RegisteredUser user) {
        this.user = user;
        populateFields();
    }

    // Populate fields with current user info
    private void populateFields() {
        if (user != null) {
            nametF.setText(user.getName());
            usertf.setText(user.getUsername());
            passwordtf.setText(user.getPassword());
            justshowUserType.setText(user.getUserType());
            AddressTf.setText(user.getAddress());

            if (user.getDob() != null) Dateofbirthtf.setValue(user.getDob());
            if (user.getJoiningDate() != null) joiningDateTf.setValue(user.getJoiningDate());

            justshowUserType.setDisable(true); // make userType non-editable
        }
    }

    // Save changes button
    @FXML
    private void saveOnAction() {
        if (user == null) return;

        try {
            String oldUsername = user.getUsername(); // store old username

            // Update user object
            user.setName(nametF.getText());
            user.setUsername(usertf.getText());
            user.setPassword(passwordtf.getText());
            user.setAddress(AddressTf.getText());
            user.setDob(Dateofbirthtf.getValue());
            user.setJoiningDate(joiningDateTf.getValue());

            // Update users.bin
            ArrayList<RegisteredUser> users = readUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(oldUsername)) { // match old username
                    users.set(i, user); // replace with updated user
                    break;
                }
            }
            writeUsers(users);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read all users from users.bin
    private ArrayList<RegisteredUser> readUsers() throws IOException, ClassNotFoundException {
        File userFile = new File("users.bin");
        if (!userFile.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            return (ArrayList<RegisteredUser>) ois.readObject();
        }
    }

    // Write all users to users.bin
    private void writeUsers(ArrayList<RegisteredUser> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"))) {
            oos.writeObject(users);
        }
    }

    // Back button
    @FXML
    private void backOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/oop/group20/group20_simulationofnewspaperonlineprinted/Muaaz/IT Admin.fxml"));
            Parent root = loader.load();

            ITAdminController controller = loader.getController();
            controller.setUser(user); // pass updated user back to dashboard

            Stage stage = (Stage) nametF.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Dashboard");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
