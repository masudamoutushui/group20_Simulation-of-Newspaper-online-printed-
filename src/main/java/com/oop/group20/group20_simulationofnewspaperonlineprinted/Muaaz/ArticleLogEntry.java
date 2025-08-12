package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArticleLogEntry {

    private final StringProperty articleId;
    private final StringProperty action;
    private final StringProperty performedBy;
    private final StringProperty role;
    private final StringProperty timestamp;
    private final StringProperty remarks;

    public ArticleLogEntry(String articleId, String action, String performedBy, String role, String timestamp, String remarks) {
        this.articleId = new SimpleStringProperty(articleId);
        this.action = new SimpleStringProperty(action);
        this.performedBy = new SimpleStringProperty(performedBy);
        this.role = new SimpleStringProperty(role);
        this.timestamp = new SimpleStringProperty(timestamp);
        this.remarks = new SimpleStringProperty(remarks);
    }

    // Properties for TableView binding
    public StringProperty articleIdProperty() { return articleId; }
    public StringProperty actionProperty() { return action; }
    public StringProperty performedByProperty() { return performedBy; }
    public StringProperty roleProperty() { return role; }
    public StringProperty timestampProperty() { return timestamp; }
    public StringProperty remarksProperty() { return remarks; }

    // Getters for filtering and other logic
    public String getArticleId() { return articleId.get(); }
    public String getAction() { return action.get(); }
    public String getPerformedBy() { return performedBy.get(); }
    public String getRole() { return role.get(); }
    public String getTimestamp() { return timestamp.get(); }
    public String getRemarks() { return remarks.get(); }
}
