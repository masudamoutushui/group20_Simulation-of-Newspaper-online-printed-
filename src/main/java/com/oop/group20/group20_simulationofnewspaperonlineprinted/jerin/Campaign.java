package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.Serializable; // 1. Import Serializable

// 2. Add "implements Serializable"
public class Campaign implements Serializable {
    // 3. Add the version ID
    private static final long serialVersionUID = 1L;

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