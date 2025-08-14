package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CatagoryManagmentController {

    @FXML
    private TableView<Catagory> categoryTableView;
    @FXML
    private TableColumn<Catagory, Integer> categoryIdColumn;
    @FXML
    private TableColumn<Catagory, String> categoryNameColumn;
    @FXML
    private TableColumn<Catagory, Integer> articleCountColumn;

    @FXML
    private TextField categoryNameField;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button deleteButton;

    private final ObservableList<Catagory> categoryList = FXCollections.observableArrayList();
    private int nextId = 1; // Simple auto-increment ID

    @FXML
    public void initialize() {
        // Bind table columns
        categoryIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        categoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        articleCountColumn.setCellValueFactory(cellData -> cellData.getValue().articleCountProperty().asObject());

        // Set table data
        categoryTableView.setItems(categoryList);

        // Handle row selection to load data into form
        categoryTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                categoryNameField.setText(newSel.getName());
            }
        });
    }

    @FXML
    private void handleSaveCategory() {
        String name = categoryNameField.getText().trim();
        if (name.isEmpty()) {
            showAlert("Please enter a category name.");
            return;
        }

        Catagory selected = categoryTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Update existing category
            selected.setName(name);
            categoryTableView.refresh();
        } else {
            // Add new category
            Catagory category = new Catagory(nextId++, name, 0);
            categoryList.add(category);
        }

        categoryNameField.clear();
        categoryTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleClearForm() {
        categoryNameField.clear();
        categoryTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleDeleteCategory() {
        Catagory selected = categoryTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            categoryList.remove(selected);
            categoryNameField.clear();
        } else {
            showAlert("Please select a category to delete.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
