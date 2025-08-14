//package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;
//
//
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.util.*;
//
//public class RBACController {
//
//    @FXML private TableView<User> userTable;
//    @FXML private TableColumn<User, String> usernameCol;
//    @FXML private TableColumn<User, String> roleCol;
//    @FXML private TextField actionField;
//    @FXML private Label resultLabel;
//
//    // Dummy data store
//    private final ObservableList<User> users = FXCollections.observableArrayList();
//
//    // Role → Permissions mapping
//    private final Map<String, Set<String>> rolePermissions = new HashMap<>();
//
//    @FXML
//    public void initialize() {
//        // Setup table columns
//        usernameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUsername()));
//        roleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRole()));
//
//        // Load dummy permissions
//        rolePermissions.put("READER", Set.of("view_published_articles", "manage_own_profile"));
//        rolePermissions.put("ADVERTISER", Set.of("submit_ads", "view_own_ads_metrics"));
//        rolePermissions.put("JOURNALIST", Set.of("create_article", "edit_own_article", "submit_article"));
//        rolePermissions.put("EDITOR_IN_CHIEF", Set.of("review_article", "edit_article", "publish_article", "manage_journalists"));
//        rolePermissions.put("SUBSCRIPTION_MANAGER", Set.of("view_subscribers", "manage_subscriptions"));
//        rolePermissions.put("PAYMENT_GATEWAY", Set.of("process_payment"));
//
//        // Load dummy users
//        users.addAll(
//                new User("reader1", "READER"),
//                new User("adUser", "ADVERTISER"),
//                new User("reporter1", "JOURNALIST"),
//                new User("chiefEditor", "EDITOR_IN_CHIEF"),
//                new User("subManager", "SUBSCRIPTION_MANAGER"),
//                new User("bkashGateway", "PAYMENT_GATEWAY")
//        );
//
//        // Add to table
//        userTable.setItems(users);
//    }
//
//    @FXML
//    private void checkPermission() {
//        User selectedUser = userTable.getSelectionModel().getSelectedItem();
//        String action = actionField.getText().trim();
//
//        if (selectedUser == null) {
//            resultLabel.setText("Select a user first!");
//            return;
//        }
//        if (action.isEmpty()) {
//            resultLabel.setText("Enter an action to check.");
//            return;
//        }
//
//        boolean allowed = rolePermissions.getOrDefault(selectedUser.getRole(), Set.of())
//                .contains(action);
//
//        if (allowed) {
//            resultLabel.setText("✅ Allowed");
//        } else {
//            resultLabel.setText("❌ Not Allowed");
//        }
//    }
//}
