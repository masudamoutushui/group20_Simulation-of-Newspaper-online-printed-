package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorialMember {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty hireDate = new SimpleStringProperty();
    private final StringProperty activeAssignments = new SimpleStringProperty();

    public EditorialMember(String id, String name, String role, String status, String email, String hireDate, String activeAssignments) {
        this.id.set(id);
        this.name.set(name);
        this.role.set(role);
        this.status.set(status);
        this.email.set(email);
        this.hireDate.set(hireDate);
        this.activeAssignments.set(activeAssignments);
    }

    // Properties getters for TableView binding
    public StringProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty roleProperty() { return role; }
    public StringProperty statusProperty() { return status; }
    public StringProperty emailProperty() { return email; }
    public StringProperty hireDateProperty() { return hireDate; }
    public StringProperty activeAssignmentsProperty() { return activeAssignments; }

    // Getters for details
    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getRole() { return role.get(); }
    public String getStatus() { return status.get(); }
    public String getEmail() { return email.get(); }
    public String getHireDate() { return hireDate.get(); }
    public String getActiveAssignments() { return activeAssignments.get(); }
}
