package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Advertiser {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty companyName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private DoubleProperty spendingLimit = new SimpleDoubleProperty();

    private List<String> activeCampaigns = new ArrayList<>();

    public Advertiser(int id, String companyName, String email, String status, double spendingLimit) {
        this.id.set(id);
        this.companyName.set(companyName);
        this.email.set(email);
        this.status.set(status);
        this.spendingLimit.set(spendingLimit);

    }

    // ===== Getters & Setters =====
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getCompanyName() { return companyName.get(); }
    public void setCompanyName(String value) { companyName.set(value); }
    public StringProperty companyNameProperty() { return companyName; }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }

    public double getSpendingLimit() { return spendingLimit.get(); }
    public void setSpendingLimit(double value) { spendingLimit.set(value); }
    public DoubleProperty spendingLimitProperty() { return spendingLimit; }



    public List<String> getActiveCampaigns() { return activeCampaigns; }
    public void setActiveCampaigns(List<String> campaigns) { this.activeCampaigns = campaigns; }
}
