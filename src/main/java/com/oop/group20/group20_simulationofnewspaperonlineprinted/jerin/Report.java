package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.util.Date;

public class Report {
    private String reportId;
    private Date generatedDate;

    public Report(String reportId) {
        this.reportId = reportId;
        this.generatedDate = new Date();
    }

    public void generate() {
        System.out.println("Generating report: " + this.reportId);
    }

    public void export(String format) {
        System.out.println("Exporting report " + this.reportId + " to " + format);
    }
}
