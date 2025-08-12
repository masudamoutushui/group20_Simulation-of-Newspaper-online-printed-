package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.util.*;

public class Goal7 {

    @FXML private ComboBox<String> filterComboBox;

    @FXML private BarChart<String, Number> topArticlesChart;
    @FXML private PieChart categoryViewsChart;
    @FXML private LineChart<String, Number> readershipTrendChart;

    private static final String PUBLISHED_FILE = "published.bin";

    private List<Article> publishedArticles = new ArrayList<>();

    @FXML
    public void initialize() {
        filterComboBox.setItems(FXCollections.observableArrayList(
                "Last 7 Days",
                "Last 30 Days",
                "Last 90 Days"
        ));
        filterComboBox.getSelectionModel().selectFirst();

        publishedArticles = loadPublishedArticles();

        loadDashboardData(filterComboBox.getSelectionModel().getSelectedItem());

        filterComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadDashboardData(newVal);
            }
        });
    }

    private List<Article> loadPublishedArticles() {
        File file = new File(PUBLISHED_FILE);
        if (!file.exists()) return Collections.emptyList();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                List<Article> articles = new ArrayList<>();
                for (Object item : list) {
                    if (item instanceof Article) {
                        articles.add((Article) item);
                    }
                }
                return articles;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void loadDashboardData(String period) {
        // Ignore period filtering for now; just use all published articles
        Map<String, Integer> categoryCounts = countArticlesByCategory(publishedArticles);

        loadBarChart(categoryCounts);
        loadPieChart(categoryCounts);
        loadLineChart(period);
    }

    private Map<String, Integer> countArticlesByCategory(List<Article> articles) {
        Map<String, Integer> counts = new HashMap<>();

        for (Article article : articles) {
            String category = article.getCategory(); // Assuming your Article class has getCategory()
            if (category == null || category.isEmpty()) {
                category = "Unknown";
            }
            counts.put(category, counts.getOrDefault(category, 0) + 1);
        }

        return counts;
    }

    private void loadBarChart(Map<String, Integer> categoryCounts) {
        topArticlesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Published Articles");

        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        topArticlesChart.getData().add(series);
    }

    private void loadPieChart(Map<String, Integer> categoryCounts) {
        categoryViewsChart.getData().clear();

        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            categoryViewsChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
    }

    private void loadLineChart(String period) {
        readershipTrendChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Views");

        // You can keep your existing dummy data here or replace with your own logic
        if ("Last 7 Days".equals(period)) {
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            int[] views = {100, 120, 130, 125, 140, 160, 170};
            for (int i = 0; i < days.length; i++) {
                series.getData().add(new XYChart.Data<>(days[i], views[i]));
            }
        } else if ("Last 30 Days".equals(period)) {
            for (int week = 1; week <= 4; week++) {
                series.getData().add(new XYChart.Data<>("Week " + week, 1000 + week * 300));
            }
        } else if ("Last 90 Days".equals(period)) {
            for (int month = 1; month <= 3; month++) {
                series.getData().add(new XYChart.Data<>("Month " + month, 3000 + month * 700));
            }
        }

        readershipTrendChart.getData().add(series);
    }
}
