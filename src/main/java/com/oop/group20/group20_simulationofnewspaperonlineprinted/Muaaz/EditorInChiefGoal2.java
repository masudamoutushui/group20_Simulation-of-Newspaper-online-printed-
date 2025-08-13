
package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditorInChiefGoal2 {

    @FXML
    private TableView<UnassignedArticle> unassignedArticlesTable;

    @FXML
    private TableColumn<UnassignedArticle, String> articleIdCol;
    @FXML
    private TableColumn<UnassignedArticle, String> articleTitleCol;
    @FXML
    private TableColumn<UnassignedArticle, String> articleCategoryCol;
    @FXML
    private TableColumn<UnassignedArticle, String> articleDateCol;

    @FXML
    private ComboBox<String> staffComboBox;

    @FXML
    private Label assignMessage;

    private ObservableList<UnassignedArticle> unassignedArticlesList = FXCollections.observableArrayList();
    private ObservableList<String> staffList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind table columns to UnassignedArticle properties
        articleIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        articleTitleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        articleCategoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        articleDateCol.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        loadUnassignedArticles();
        loadStaffMembers();

        unassignedArticlesTable.setItems(unassignedArticlesList);
        staffComboBox.setItems(staffList);
    }

    private void loadUnassignedArticles() {
        // TODO: Fetch from DB, dummy data for now
        unassignedArticlesList.clear();
        unassignedArticlesList.addAll(
                new UnassignedArticle("101", "Climate Change Impact", "Environment", "2025-08-01"),
                new UnassignedArticle("102", "Tech Advances in AI", "Technology", "2025-08-03"),
                new UnassignedArticle("103", "Global Economy Trends", "Finance", "2025-08-05"),
                new UnassignedArticle("104", "Health Benefits of Yoga", "Health", "2025-08-06"),
                new UnassignedArticle("105", "Local Elections Results", "Politics", "2025-08-07"),
                new UnassignedArticle("106", "New Movie Releases", "Entertainment", "2025-08-08"),
                new UnassignedArticle("107", "Space Exploration Updates", "Science", "2025-08-09"),
                new UnassignedArticle("108", "Sports Championship Preview", "Sports", "2025-08-10")
        );
    }


    private void loadStaffMembers() {
        // TODO: Fetch reporters and journalists from DB
        staffList.clear();
        staffList.addAll(
                "John Doe - Reporter",
                "Jane Smith - Journalist",
                "Alice Brown - Reporter",
                "Michael Green - Journalist",
                "Emily White - Reporter",
                "David Black - Journalist",
                "Sophia Blue - Reporter",
                "Chris Red - Journalist"
        );
    }


    @FXML
    private void handleAssignArticle() {
        UnassignedArticle selectedArticle = unassignedArticlesTable.getSelectionModel().getSelectedItem();
        String selectedStaff = staffComboBox.getSelectionModel().getSelectedItem();

        if (selectedArticle == null) {
            assignMessage.setText("Please select an article to assign.");
            assignMessage.setStyle("-fx-text-fill: red;");
            return;
        }
        if (selectedStaff == null || selectedStaff.isEmpty()) {
            assignMessage.setText("Please select a staff member.");
            assignMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        // TODO: Update DB to assign article to staff

        // For demo: Remove from list to simulate assignment
        unassignedArticlesList.remove(selectedArticle);
        unassignedArticlesTable.getSelectionModel().clearSelection();
        staffComboBox.getSelectionModel().clearSelection();

        assignMessage.setText("Article assigned to " + selectedStaff);
        assignMessage.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleBack() {
        // TODO: Implement navigation back to main editor-in-chief dashboard
        System.out.println("Back button clicked");
    }
}
