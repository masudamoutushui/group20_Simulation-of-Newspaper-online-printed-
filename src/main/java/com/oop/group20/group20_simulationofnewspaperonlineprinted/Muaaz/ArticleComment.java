package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

public class ArticleComment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Article article; // The published article
    private String comment;  // The comment for this article

    // Transient JavaFX property for TableView binding
    private transient StringProperty commentProperty;

    public ArticleComment(Article article, String comment) {
        this.article = article;
        this.comment = comment;
        this.commentProperty = new SimpleStringProperty(comment);
    }

    public Article getArticle() {
        return article;
    }

    public String getComment() {
        if (commentProperty != null) {
            return commentProperty.get();
        }
        return comment;
    }

    public StringProperty commentProperty() {
        if (commentProperty == null) {
            commentProperty = new SimpleStringProperty(comment);
        }
        return commentProperty;
    }

    public void setComment(String comment) {
        this.comment = comment;
        if (commentProperty != null) {
            commentProperty.set(comment);
        }
    }

    // Custom serialization to handle transient property
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        commentProperty = new SimpleStringProperty(comment);
    }
}
