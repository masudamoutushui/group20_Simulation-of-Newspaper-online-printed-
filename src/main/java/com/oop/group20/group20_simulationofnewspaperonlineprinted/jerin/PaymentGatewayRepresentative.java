package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;
import java.util.ArrayList;
import java.util.List;
public class PaymentGatewayRepresentative {
    private String userId;
    private String username;

    public PaymentGatewayRepresentative(String username) {
        this.userId = "PGR" + System.currentTimeMillis();
        this.username = username;
    }

    public boolean login(String password) {
        // Mock login
        return "password123".equals(password);
    }

    public void logout() {
        System.out.println("Payment Gateway Representative logged out.");
    }

    public List<Payment> managePaymentRecords() {
        System.out.println("Fetching all payment records.");
        return new ArrayList<>(); // Return a list of payments
    }

    public Report generateRevenueReport() {
        System.out.println("Generating revenue report.");
        return new Report("RevenueReport");
    }

    public boolean processRefund(String paymentId) {
        System.out.println("Processing refund for payment ID: " + paymentId);
        return true;
    }
}
