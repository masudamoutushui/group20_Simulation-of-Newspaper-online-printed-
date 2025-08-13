package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.util.*;

public class Goal7 {

    @FXML private ComboBox<String> filterComboBox;  // must match fx:id in FXML

    @FXML private BarChart<String, Number> topArticlesChart;
    @FXML private PieChart categoryViewsChart;
    @FXML private LineChart<String, Number> readershipTrendChart;

    // Dummy data store simulating real data source
    private final Map<String, List<ArticleMetric>> dummyDataByPeriod = new HashMap<>();

    @FXML
    public void initialize() {
        // Setup time filter options
        filterComboBox.setItems(FXCollections.observableArrayList(
                "Last 7 Days",
                "Last 30 Days",
                "Last 90 Days"
        ));
        filterComboBox.getSelectionModel().selectFirst();

        // Initialize dummy data
        setupDummyData();

        // Load initial data
        loadDashboardData(filterComboBox.getSelectionModel().getSelectedItem());

        // Listen for time filter changes
        filterComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadDashboardData(newVal);
            }
        });
    }

    private void setupDummyData() {
        dummyDataByPeriod.put("Last 7 Days", Arrays.asList(
                new ArticleMetric("Climate Change Impact", 350, 75),
                new ArticleMetric("Elections 2025", 420, 95),
                new ArticleMetric("Tech Innovations", 300, 40),
                new ArticleMetric("Sports Highlights", 280, 60)
        ));
        dummyDataByPeriod.put("Last 30 Days", Arrays.asList(
                new ArticleMetric("Climate Change Impact", 1200, 250),
                new ArticleMetric("Elections 2025", 1400, 300),
                new ArticleMetric("Tech Innovations", 1100, 220),
                new ArticleMetric("Sports Highlights", 900, 190),
                new ArticleMetric("Health & Wellness", 800, 130)
        ));
        dummyDataByPeriod.put("Last 90 Days", Arrays.asList(
                new ArticleMetric("Climate Change Impact", 3800, 760),
                new ArticleMetric("Elections 2025", 4300, 850),
                new ArticleMetric("Tech Innovations", 3600, 720),
                new ArticleMetric("Sports Highlights", 3200, 630),
                new ArticleMetric("Health & Wellness", 2900, 520),
                new ArticleMetric("Global Economy", 2700, 410)
        ));
    }

    private void loadDashboardData(String period) {
        List<ArticleMetric> metrics = dummyDataByPeriod.getOrDefault(period, Collections.emptyList());

        loadBarChart(metrics);
        loadPieChart(metrics);
        loadLineChart(period);
    }

    private void loadBarChart(List<ArticleMetric> metrics) {
        topArticlesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Views");

        for (ArticleMetric m : metrics) {
            series.getData().add(new XYChart.Data<>(m.getTitle(), m.getViews()));
        }

        topArticlesChart.getData().add(series);
    }

    private void loadPieChart(List<ArticleMetric> metrics) {
        categoryViewsChart.getData().clear();

        // Map article titles to categories
        Map<String, String> categoryMap = Map.of(
                "Climate Change Impact", "Environment",
                "Elections 2025", "Politics",
                "Tech Innovations", "Technology",
                "Sports Highlights", "Sports",
                "Health & Wellness", "Health",
                "Global Economy", "Economy"
        );

        Map<String, Integer> categoryViews = new HashMap<>();
        for (ArticleMetric m : metrics) {
            String cat = categoryMap.getOrDefault(m.getTitle(), "Other");
            categoryViews.put(cat, categoryViews.getOrDefault(cat, 0) + m.getViews());
        }

        for (Map.Entry<String, Integer> entry : categoryViews.entrySet()) {
            categoryViewsChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
    }

    private void loadLineChart(String period) {
        readershipTrendChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Views");

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
