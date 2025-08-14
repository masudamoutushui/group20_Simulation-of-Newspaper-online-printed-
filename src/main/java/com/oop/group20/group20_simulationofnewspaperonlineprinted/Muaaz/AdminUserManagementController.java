//package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;
//
//
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//
//public class AdminUserManagementController {
//
//    @FXML private TableView<User> userTable;
//    @FXML private TableColumn<User, String> colUsername;
//    @FXML private TableColumn<User, String> colRole;
//    @FXML private TableColumn<User, String> colStatus;
//
//    @FXML
//    public void initialize() {
//        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
//        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        ObservableList<User> users = FXCollections.observableArrayList(
//                new User("muaaz", "Admin", "Active"),
//                new User("ali", "Journalist", "Inactive"),
//                new User("sara", "Reader", "Active")
//        );
//        userTable.setItems(users);
//    }
//}
