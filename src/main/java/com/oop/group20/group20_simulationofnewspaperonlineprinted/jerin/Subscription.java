package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.util.Date;

public class Subscription {
    private String subscriptionId;
    private String tier;
    private String type;
    private double price;
    private Date startDate;

    public Subscription(String tier, String type, double price) {
        this.subscriptionId = "SUB" + System.currentTimeMillis();
        this.tier = tier;
        this.type = type;
        this.price = price;
        this.startDate = new Date();
    }

    public void renew() {
        System.out.println("Subscription " + this.subscriptionId + " has been renewed.");
    }

    public void cancel() {
        System.out.println("Subscription " + this.subscriptionId + " has been canceled.");
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
