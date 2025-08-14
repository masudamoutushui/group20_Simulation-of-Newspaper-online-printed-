package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import java.io.Serializable;
import java.time.LocalDate;

public class Auditlog implements Serializable {

    private String username;
    private String usertype;
    private String action;
    private LocalDate time;

    public Auditlog(String username, String usertype, String action, LocalDate time) {
        this.username = username;
        this.usertype = usertype;
        this.action = action;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Auditlog{" +
                "username='" + username + '\'' +
                ", usertype='" + usertype + '\'' +
                ", action='" + action + '\'' +
                ", time=" + time +
                '}';
    }
}
