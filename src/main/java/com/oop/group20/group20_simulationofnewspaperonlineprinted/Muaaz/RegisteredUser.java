package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import java.io.Serializable;
import java.time.LocalDate;

public class RegisteredUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String userType;
    private String name;
    private String address;
    private LocalDate dob;
    private LocalDate joiningDate;

    public RegisteredUser(String username, String password, String userType, String name,
                          String address, LocalDate dob, LocalDate joiningDate) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.joiningDate = joiningDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
