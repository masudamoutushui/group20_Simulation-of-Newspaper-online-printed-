package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import java.io.Serializable;

public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String message;
    private String urgency;
    private String toWhom;

    public Announcement(String title, String message, String urgency, String toWhom) {
        this.title = title;
        this.message = message;
        this.urgency = urgency;
        this.toWhom = toWhom;
    }

    // Getters & Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }

    public String getToWhom() { return toWhom; }
    public void setToWhom(String toWhom) { this.toWhom = toWhom; }
}
