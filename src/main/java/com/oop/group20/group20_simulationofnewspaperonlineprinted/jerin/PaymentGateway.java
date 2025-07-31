package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

public interface PaymentGateway {
    boolean processPayment(double amount);
    boolean issueRefund(String transactionId, double amount);
}
