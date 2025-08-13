package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeamMember {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty role;
    private final StringProperty contact;

    public TeamMember(String id, String name, String role, String contact) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.role = new SimpleStringProperty(role);
        this.contact = new SimpleStringProperty(contact);
    }

    public StringProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty roleProperty() { return role; }
    public StringProperty contactProperty() { return contact; }

    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getRole() { return role.get(); }
    public String getContact() { return contact.get(); }

    public void setName(String name) { this.name.set(name); }
    public void setRole(String role) { this.role.set(role); }
    public void setContact(String contact) { this.contact.set(contact); }
}
