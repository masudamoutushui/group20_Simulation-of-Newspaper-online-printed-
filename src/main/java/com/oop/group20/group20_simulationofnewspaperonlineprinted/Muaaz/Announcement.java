package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.*;

public class Announcement {
    private final IntegerProperty id;
    private final StringProperty message;
    private final StringProperty urgency;
    private final StringProperty toWhom;

    public Announcement(int id, String message, String urgency, String toWhom) {
        this.id = new SimpleIntegerProperty(id);
        this.message = new SimpleStringProperty(message);
        this.urgency = new SimpleStringProperty(urgency);
        this.toWhom = new SimpleStringProperty(toWhom);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getMessage() { return message.get(); }
    public StringProperty messageProperty() { return message; }

    public String getUrgency() { return urgency.get(); }
    public StringProperty urgencyProperty() { return urgency; }

    public String getToWhom() { return toWhom.get(); }
    public StringProperty toWhomProperty() { return toWhom; }
}
