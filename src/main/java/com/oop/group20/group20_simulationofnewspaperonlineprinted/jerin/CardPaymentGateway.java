package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

public class CardPaymentGateway implements PaymentGateway {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Card payment of: " + amount);
        return false; // Simulate failure
    }

    @Override
    public boolean issueRefund(String transactionId, double amount) {
        System.out.println("Refunding " + amount + " via Card for transaction: " + transactionId);
        return true;
    }
}
