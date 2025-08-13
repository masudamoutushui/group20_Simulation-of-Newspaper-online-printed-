package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.*;

public class ArticleMetric {

    private final StringProperty title;
    private final IntegerProperty views;
    private final IntegerProperty shares;

    public ArticleMetric(String title, int views, int shares) {
        this.title = new SimpleStringProperty(title);
        this.views = new SimpleIntegerProperty(views);
        this.shares = new SimpleIntegerProperty(shares);
    }

    public String getTitle() {
        return title.get();
    }
    public StringProperty titleProperty() {
        return title;
    }

    public int getViews() {
        return views.get();
    }
    public IntegerProperty viewsProperty() {
        return views;
    }

    public int getShares() {
        return shares.get();
    }
    public IntegerProperty sharesProperty() {
        return shares;
    }
}
