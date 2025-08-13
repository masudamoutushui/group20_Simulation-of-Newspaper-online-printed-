package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Model class for a Staff Member.
 * This uses JavaFX Properties to allow the TableView to be updated automatically.
 */
public class StaffMember {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty role;
    private final StringProperty status;
    private final StringProperty email;
    private final LocalDate hireDate;
    private final ObservableList<String> assignedArticles;
    private final IntegerProperty activeAssignments;

    public StaffMember(String id, String name, String role, String status, String email, LocalDate hireDate, ObservableList<String> assignedArticles) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.role = new SimpleStringProperty(role);
        this.status = new SimpleStringProperty(status);
        this.email = new SimpleStringProperty(email);
        this.hireDate = hireDate;
        this.assignedArticles = assignedArticles;
        this.activeAssignments = new SimpleIntegerProperty(assignedArticles.size());
    }

    // --- Getters for JavaFX Properties ---
    // These are needed for the PropertyValueFactory to work.

    public StringProperty nameProperty() { return name; }
    public StringProperty roleProperty() { return role; }
    public StringProperty statusProperty() { return status; }
    public IntegerProperty activeAssignmentsProperty() { return activeAssignments; }

    // --- Standard Getters for accessing data ---

    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getRole() { return role.get(); }
    public String getStatus() { return status.get(); }
    public String getEmail() { return email.get(); }
    public LocalDate getHireDate() { return hireDate; }
    public ObservableList<String> getAssignedArticles() { return assignedArticles; }
    public int getActiveAssignments() { return activeAssignments.get(); }
}