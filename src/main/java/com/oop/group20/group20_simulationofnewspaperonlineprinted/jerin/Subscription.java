package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.Serializable;
import java.util.Date;

public class Subscription implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subscriptionId;
    private String tier;
    private String type;
    private double price;
    private Date startDate;


    public Subscription(String tier, String type, double price) {
        this.subscriptionId = "SUB" + System.currentTimeMillis(); // Creates a unique mock ID
        this.tier = tier;
        this.type = type;
        this.price = price;
        this.startDate = new Date(); // Sets the start date to the moment it's created
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

    public String getTier() {
        return tier;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }
}