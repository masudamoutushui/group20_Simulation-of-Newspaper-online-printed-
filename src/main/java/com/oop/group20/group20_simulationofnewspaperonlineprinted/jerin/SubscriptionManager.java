package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;
import java.util.Map;

public class SubscriptionManager {
    private String userId;
    private String username;

    public SubscriptionManager(String username) {
        this.userId = "SM" + System.currentTimeMillis();
        this.username = username;
    }

    public boolean login(String password) {
        // Mock login
        return "password".equals(password);
    }

    public void logout() {
        System.out.println("Subscription Manager logged out.");
    }

    public Campaign createCampaign(Map<String, String> details) {
        Campaign newCampaign = new Campaign(details.get("campaignId"), details.get("name"));
        System.out.println("Campaign created: " + newCampaign.getName());
        return newCampaign;
    }

    public void managePromotions() {
        System.out.println("Managing promotions.");
    }

    public Report viewSubscriberAnalytics() {
        System.out.println("Generating subscriber analytics report.");
        return new Report("AnalyticsReport");
    }
}
