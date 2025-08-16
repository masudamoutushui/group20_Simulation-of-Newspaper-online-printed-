package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

// REMOVE: import javafx.util.Subscription; <-- This was the error
// You likely don't need any import if 'Subscription' is in the same package.
// If it were in a different package, it would be: import com.your.package.Subscription;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class Subscriber implements Serializable {
    private String subscriberId;
    private String name;
    private String email;
    private String subscriptionStatus;

    // This must refer to YOUR Subscription class
    private Subscription subscription;

    @Serial
    private static final long serialVersionUID = 1L;

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

    // You should also add getters for the TableView to use
    public String getSubscriberId() {
        return subscriberId;
    }

    public String getEmail() {
        return email;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }
}