package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.util.Map;

public class Subscriber {
    private String subscriberId;
    private String name;
    private String email;
    private String subscriptionStatus;
    private Subscription subscription;

    public Subscriber(String name, String email) {
        this.subscriberId = "SUB" + System.currentTimeMillis(); // Mock ID
        this.name = name;
        this.email = email;
        this.subscriptionStatus = "Inactive";
    }

    public Subscription viewSubscription() {
        return this.subscription;
    }

    public void updateDetails(Map<String, String> details) {
        if (details.containsKey("name")) {
            this.name = details.get("name");
        }
        if (details.containsKey("email")) {
            this.email = details.get("email");
        }
        System.out.println("Subscriber details updated for " + this.name);
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
        this.subscriptionStatus = "Active";
    }

    public String getName() {
        return name;
    }
}
