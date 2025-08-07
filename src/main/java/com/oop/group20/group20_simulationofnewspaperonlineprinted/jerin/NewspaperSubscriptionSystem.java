package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class NewspaperSubscriptionSystem {
    private List<Subscriber> subscribers;
    private List<Campaign> campaigns;

    public NewspaperSubscriptionSystem() {
        this.subscribers = new ArrayList<>();
        this.campaigns = new ArrayList<>();
    }

    public Subscriber registerSubscriber(Map<String, String> details) {
        Subscriber newSubscriber = new Subscriber(details.get("name"), details.get("email"));
        this.subscribers.add(newSubscriber);
        System.out.println("New subscriber registered: " + newSubscriber.getName());
        return newSubscriber;
    }

    public void processSubscription(Subscription sub) {
        System.out.println("Processing subscription ID: " + sub.getSubscriptionId());
        // Logic to associate subscription with a subscriber
    }

}
