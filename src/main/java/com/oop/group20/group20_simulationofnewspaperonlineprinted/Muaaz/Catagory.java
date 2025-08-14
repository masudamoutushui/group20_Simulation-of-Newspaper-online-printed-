package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;



import javafx.beans.property.*;

public class Catagory {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty articleCount;

    public Catagory(int id, String name, int articleCount) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.articleCount = new SimpleIntegerProperty(articleCount);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getArticleCount() { return articleCount.get(); }
    public void setArticleCount(int count) { this.articleCount.set(count); }
    public IntegerProperty articleCountProperty() { return articleCount; }
}
