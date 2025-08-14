package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsDashboardAdminController {

    @FXML
    private Label totalSubscribersLabel;
    @FXML
    private Label newSubscribersLabel;

    @FXML
    private LineChart<String, Number> revenueChart;
    @FXML
    private PieChart subscriberPieChart;
    @FXML
    private BarChart<String, Number> planPopularityBarChart;

    private List<Integer> monthlyRevenue = new ArrayList<>();
    private List<Integer> monthlyNewSubs = new ArrayList<>();
    private List<String> subscriberTypes = new ArrayList<>();
    private List<Integer> subscriberTypeCounts = new ArrayList<>();
    private List<String> subscriptionPlans = new ArrayList<>();
    private List<Integer> planCounts = new ArrayList<>();

    private XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
    private XYChart.Series<String, Number> planSeries = new XYChart.Series<>();

    @FXML
    public void initialize() {
        loadDummyData();
        setupCharts();
        refreshDashboard();
    }

    // ===== Load initial dummy data =====
    private void loadDummyData() {
        monthlyRevenue.addAll(List.of(1200, 1500, 1300, 1700, 1600, 1800));
        monthlyNewSubs.addAll(List.of(50, 65, 55, 70, 60, 75));

        subscriberTypes.addAll(List.of("Free", "Premium", "Corporate"));
        subscriberTypeCounts.addAll(List.of(120, 80, 25));

        subscriptionPlans.addAll(List.of("Monthly", "Quarterly", "Yearly"));
        planCounts.addAll(List.of(70, 40, 25));
    }

    // ===== Setup chart series =====
    private void setupCharts() {
        revenueSeries.setName("Revenue");
        revenueChart.getData().add(revenueSeries);

        planSeries.setName("Plans");
        planPopularityBarChart.getData().add(planSeries);
    }

    // ===== Refresh all dashboard elements =====
    private void refreshDashboard() {
        updateSummaryLabels();
        updateRevenueChart();
        updateSubscriberPieChart();
        updatePlanPopularityChart();
    }

    private void updateSummaryLabels() {
        int totalSubs = subscriberTypeCounts.stream().mapToInt(Integer::intValue).sum();
        int newSubs = monthlyNewSubs.get(monthlyNewSubs.size() - 1);

        totalSubscribersLabel.setText(String.valueOf(totalSubs));
        newSubscribersLabel.setText(String.valueOf(newSubs));
    }

    private void updateRevenueChart() {
        revenueSeries.getData().clear();
        String[] months = {"Mar", "Apr", "May", "Jun", "Jul", "Aug"};
        for (int i = 0; i < monthlyRevenue.size(); i++) {
            revenueSeries.getData().add(new XYChart.Data<>(months[i], monthlyRevenue.get(i)));
        }
    }

    private void updateSubscriberPieChart() {
        subscriberPieChart.getData().clear();
        for (int i = 0; i < subscriberTypes.size(); i++) {
            subscriberPieChart.getData().add(new PieChart.Data(subscriberTypes.get(i), subscriberTypeCounts.get(i)));
        }
    }

    private void updatePlanPopularityChart() {
        planSeries.getData().clear();
        for (int i = 0; i < subscriptionPlans.size(); i++) {
            planSeries.getData().add(new XYChart.Data<>(subscriptionPlans.get(i), planCounts.get(i)));
        }
    }

    // ===== Dynamic Methods to add data =====

    public void addNewSubscriber(String type, int count, int revenue) {
        Platform.runLater(() -> {
            int index = subscriberTypes.indexOf(type);
            if (index >= 0) {
                subscriberTypeCounts.set(index, subscriberTypeCounts.get(index) + count);
            } else {
                subscriberTypes.add(type);
                subscriberTypeCounts.add(count);
            }

            monthlyNewSubs.add(count);
            monthlyRevenue.add(revenue);

            refreshDashboard();
        });
    }

    public void addNewPlanData(String plan, int count) {
        Platform.runLater(() -> {
            int index = subscriptionPlans.indexOf(plan);
            if (index >= 0) {
                planCounts.set(index, planCounts.get(index) + count);
            } else {
                subscriptionPlans.add(plan);
                planCounts.add(count);
            }

            refreshDashboard();
        });
    }
}
