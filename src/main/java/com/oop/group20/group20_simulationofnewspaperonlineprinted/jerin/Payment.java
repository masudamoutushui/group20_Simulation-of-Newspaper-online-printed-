package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.util.Date;

public class Payment {
    private String paymentId;
    private double amount;
    private String status;
    private Date date;

    public Payment(double amount) {
        this.paymentId = "PAY" + System.currentTimeMillis();
        this.amount = amount;
        this.status = "Pending";
        this.date = new Date();
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
