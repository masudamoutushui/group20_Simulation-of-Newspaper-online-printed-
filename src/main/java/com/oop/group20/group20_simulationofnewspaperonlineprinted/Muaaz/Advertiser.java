package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

public class Advertiser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String companyName;
    private String email;
    private String status;
    private String spendingLimit;
    private List<String> campaigns;

    public Advertiser(String id, String companyName, String email, String status, String spendingLimit, List<String> campaigns) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.status = status;
        this.spendingLimit = spendingLimit;
        this.campaigns = campaigns;
    }

    // Getters
    public String getId() { return id; }
    public String getCompanyName() { return companyName; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
    public String getSpendingLimit() { return spendingLimit; }
    public List<String> getCampaigns() { return campaigns; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setEmail(String email) { this.email = email; }
    public void setStatus(String status) { this.status = status; }
    public void setSpendingLimit(String spendingLimit) { this.spendingLimit = spendingLimit; }
    public void setCampaigns(List<String> campaigns) { this.campaigns = campaigns; }
}
