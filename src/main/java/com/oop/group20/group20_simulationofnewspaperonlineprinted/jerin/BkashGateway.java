package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

public class BkashGateway implements PaymentGateway {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Bkash payment of: " + amount);
        return true; // Simulate success
    }

    @Override
    public boolean issueRefund(String transactionId, double amount) {
        System.out.println("Refunding " + amount + " via Bkash for transaction: " + transactionId);
        return true;
    }
}
