package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;
import java.io.Serializable;
import java.time.LocalDate;

public class Auditlog implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public String getUsername() { return username; }
    public String getUsertype() { return usertype; }
    public String getAction() { return action; }
    public LocalDate getTime() { return time; }
}
