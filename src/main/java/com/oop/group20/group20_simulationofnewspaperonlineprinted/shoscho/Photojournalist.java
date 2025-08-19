package com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

import java.time.LocalDate;

public class Photojournalist {
    private String imageName;
    private String category;
    private LocalDate date;
    private String status;

    public Photojournalist() {}


    public Photojournalist(String imageName, String category, LocalDate date, String status) {
        this.imageName = imageName;
        this.category = category;
        this.date = date;
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "imageName='" + imageName + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
