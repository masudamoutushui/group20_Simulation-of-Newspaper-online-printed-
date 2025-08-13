package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleData {

    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty category;
    private final StringProperty publishDate;
    private final StringProperty content;

    public ArticleData(String id, String title, String author, String category, String publishDate, String content) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.publishDate = new SimpleStringProperty(publishDate);
        this.content = new SimpleStringProperty(content);
    }

    // Getters for property bindings
    public StringProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public StringProperty authorProperty() { return author; }
    public StringProperty categoryProperty() { return category; }
    public StringProperty publishDateProperty() { return publishDate; }

    // Getters for raw values
    public String getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public String getAuthor() { return author.get(); }
    public String getCategory() { return category.get(); }
    public String getPublishDate() { return publishDate.get(); }
    public String getContent() { return content.get(); }

    // Setter for content
    public void setContent(String content) {
        this.content.set(content);
    }

    // Convert article to a line of data for saving
    public String toDataString() {
        return getId() + "|" + getTitle() + "|" + getAuthor() + "|" + getCategory() + "|" + getPublishDate() + "|" + getContent().replace("\n", "\\n");
    }

    // Reconstruct article from a saved line
    public static ArticleData fromDataString(String line) {
        String[] parts = line.split("\\|", 6);  // Split only 5 times
        if (parts.length < 6) return null;

        String restoredContent = parts[5].replace("\\n", "\n");
        return new ArticleData(parts[0], parts[1], parts[2], parts[3], parts[4], restoredContent);
    }

    // Save list of articles to file
    public static void saveArticlesToFile(List<ArticleData> articles, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (ArticleData a : articles) {
                writer.write(a.toDataString());
                writer.newLine();
            }
        }
    }

    // Load list of articles from file
    public static List<ArticleData> loadArticlesFromFile(File file) throws IOException {
        List<ArticleData> list = new ArrayList<>();
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArticleData a = fromDataString(line);
                if (a != null) list.add(a);
            }
        }

        return list;
    }
}
