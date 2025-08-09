package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditorInChiefGoal5 {

    @FXML
    private ComboBox<String> memberComboBox;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField roleField;

    private ObservableList<String> teamMembers;

    @FXML
    public void initialize() {
        // Dummy members for starting
        teamMembers = FXCollections.observableArrayList(
                "Alice - Editor",
                "Bob - Journalist",
                "Charlie - Photographer"
        );

        memberComboBox.setItems(teamMembers);
        teamListView.setItems(teamMembers);
    }

    @FXML
    private void handleAddMember() {
        String name = nameField.getText().trim();
        String role = roleField.getText().trim();

        if (!name.isEmpty() && !role.isEmpty()) {
            String newMember = name + " - " + role;
            teamMembers.add(newMember);

            // Update both UI components
            memberComboBox.setItems(teamMembers);
            teamListView.setItems(teamMembers);

            // Clear inputs
            nameField.clear();
            roleField.clear();
        }
    }

    @FXML
    private void handleRemoveMember() {
        String selected = teamListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teamMembers.remove(selected);

            // Update both UI components
            memberComboBox.setItems(teamMembers);
            teamListView.setItems(teamMembers);
        }
    }
}
