package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.Serializable;
import java.util.Date;

public class Subscription implements Serializable {
    // A version ID is required for any class that is serializable.
    private static final long serialVersionUID = 1L;

    private String subscriptionId;
    private String tier;
    private String type;
    private double price;
    private Date startDate;

    /**
     * Constructor to create a new subscription plan.
     * @param tier The tier of the subscription (e.g., Premium, Basic).
     * @param type The type of subscription (e.g., Online, Print).
     * @param price The cost of the subscription.
     */
    public Subscription(String tier, String type, double price) {
        this.subscriptionId = "SUB" + System.currentTimeMillis(); // Creates a unique mock ID
        this.tier = tier;
        this.type = type;
        this.price = price;
        this.startDate = new Date(); // Sets the start date to the moment it's created
    }

    // --- Action Methods ---

    public void renew() {
        System.out.println("Subscription " + this.subscriptionId + " has been renewed.");
        // In a real application, you would add logic here to extend the end date.
    }

    public void cancel() {
        System.out.println("Subscription " + this.subscriptionId + " has been canceled.");
        // In a real application, you would add logic here to mark the subscription as inactive.
    }

    // --- Getter Methods ---
    // These are essential for the TableView to display the data.

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