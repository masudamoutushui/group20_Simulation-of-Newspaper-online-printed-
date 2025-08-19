package com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

import java.io.Serializable;

public class Advertisertest implements Serializable {

    private String id;
    private String type;
    private String advertiser;
    private String status;

    public Advertisertest(String id, String type, String advertiser, String status) {
        this.id = id;
        this.type = type;
        this.advertiser = advertiser;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
