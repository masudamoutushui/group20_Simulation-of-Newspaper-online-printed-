package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.*;

public class User {
    private final IntegerProperty id;
    private final StringProperty fullName;
    private final StringProperty username;
    private final StringProperty password;
    private final ObjectProperty<Role> role;
    private final BooleanProperty active;

    public User(int id, String fullName, String username, String password, Role role, boolean active) {
        this.id = new SimpleIntegerProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleObjectProperty<>(role);
        this.active = new SimpleBooleanProperty(active);
    }

    // Getters and setters
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getFullName() { return fullName.get(); }
    public void setFullName(String fullName) { this.fullName.set(fullName); }
    public StringProperty fullNameProperty() { return fullName; }

    public String getUsername() { return username.get(); }
    public void setUsername(String username) { this.username.set(username); }
    public StringProperty usernameProperty() { return username; }

    public String getPassword() { return password.get(); }
    public void setPassword(String password) { this.password.set(password); }
    public StringProperty passwordProperty() { return password; }

    public Role getRole() { return role.get(); }
    public void setRole(Role role) { this.role.set(role); }
    public ObjectProperty<Role> roleProperty() { return role; }

    public boolean isActive() { return active.get(); }
    public void setActive(boolean active) { this.active.set(active); }
    public BooleanProperty activeProperty() { return active; }
}
