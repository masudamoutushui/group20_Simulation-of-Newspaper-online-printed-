package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Subscriber> subscribers;
    private List<Payment> payments;
    private List<Campaign> campaigns;

    public ApplicationData() {
        this.subscribers = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.campaigns = new ArrayList<>();
    }


    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}