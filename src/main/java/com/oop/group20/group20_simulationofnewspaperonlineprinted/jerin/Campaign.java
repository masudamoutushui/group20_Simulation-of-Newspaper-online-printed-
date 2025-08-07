package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

public class Campaign {
    private String campaignId;
    private String name;

    public Campaign(String campaignId, String name) {
        this.campaignId = campaignId;
        this.name = name;
    }

    public void launch() {
        System.out.println("Campaign " + this.name + " launched.");
    }

    public Report trackPerformance() {
        System.out.println("Tracking performance for campaign: " + this.name);
        return new Report("CampaignPerformanceReport");
    }

    public String getName() {
        return name;
    }
}
