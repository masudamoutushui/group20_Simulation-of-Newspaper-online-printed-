package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.*;

public class FlaggedComment {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty reason = new SimpleStringProperty();
    private StringProperty articleTitle = new SimpleStringProperty();
    private StringProperty timestamp = new SimpleStringProperty();
    private StringProperty commentSnippet = new SimpleStringProperty();
    private StringProperty fullComment = new SimpleStringProperty();

    public FlaggedComment(int id, String author, String reason, String articleTitle, String timestamp, String commentSnippet, String fullComment) {
        this.id.set(id);
        this.author.set(author);
        this.reason.set(reason);
        this.articleTitle.set(articleTitle);
        this.timestamp.set(timestamp);
        this.commentSnippet.set(commentSnippet);
        this.fullComment.set(fullComment);
    }

    // ===== Getters & Properties =====
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getAuthor() { return author.get(); }
    public StringProperty authorProperty() { return author; }

    public String getReason() { return reason.get(); }
    public StringProperty reasonProperty() { return reason; }

    public String getArticleTitle() { return articleTitle.get(); }
    public StringProperty articleTitleProperty() { return articleTitle; }

    public String getTimestamp() { return timestamp.get(); }
    public StringProperty timestampProperty() { return timestamp; }

    public String getCommentSnippet() { return commentSnippet.get(); }
    public StringProperty commentSnippetProperty() { return commentSnippet; }

    public String getFullComment() { return fullComment.get(); }
    public StringProperty fullCommentProperty() { return fullComment; }
}
