package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.util.HashMap;
import java.util.Map;

public class NewspaperSubscriptionSimulation {

    public static void main(String[] args) {
        // Initialize the main system
        NewspaperSubscriptionSystem system = new NewspaperSubscriptionSystem();

        // --- Subscription Manager Simulation ---
        System.out.println("--- Subscription Manager Workflow ---");
        SubscriptionManager manager = new SubscriptionManager("Admin");
        if (manager.login("password")) {
            System.out.println("Subscription Manager logged in.");

            // Goal 1: Drive New Subscription Acquisition
            Map<String, String> campaignDetails = new HashMap<>();
            campaignDetails.put("campaignId", "C123");
            campaignDetails.put("name", "Summer Sale");
            Campaign newCampaign = manager.createCampaign(campaignDetails);
            newCampaign.launch();
            newCampaign.trackPerformance();

            manager.logout();
        }

        System.out.println("\n--- Payment Gateway Representative Workflow ---");

        // --- Payment Gateway Representative Simulation ---
        PaymentGatewayRepresentative pgr = new PaymentGatewayRepresentative("FinanceUser");
        if (pgr.login("password123")) {
            System.out.println("Payment Gateway Representative logged in.");

            // Goal 1: Manage Subscription Payment Records
            pgr.managePaymentRecords();

            // Goal 3: Generate Subscription Revenue Reports
            Report revenueReport = pgr.generateRevenueReport();
            revenueReport.export("PDF");

            // Goal 4: Manage Simulated Refund Processes
            pgr.processRefund("PAY12345");


            pgr.logout();
        }

        System.out.println("\n--- Subscriber and Payment Workflow ---");

        // --- Subscriber Registration and Payment ---
        Map<String, String> subscriberDetails = new HashMap<>();
        subscriberDetails.put("name", "John Doe");
        subscriberDetails.put("email", "john.doe@example.com");
        Subscriber subscriber = system.registerSubscriber(subscriberDetails);

        Subscription subscription = new Subscription("Premium", "Online", 12.99);
        subscriber.setSubscription(subscription);
        system.processSubscription(subscription);

        Payment bkashPayment = new Payment(12.99);
        PaymentGateway bkashGateway = new BkashGateway();
        if (bkashGateway.processPayment(12.99)) {
            bkashPayment.setStatus("Successful");
        } else {
            bkashPayment.setStatus("Failed");
        }
    }
}
