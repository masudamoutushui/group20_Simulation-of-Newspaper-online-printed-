package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import java.io.Serial;
import java.io.Serializable;

public class checkArticle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String author;
    private String content;

    public checkArticle(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
}
