package com.vudqfx.humanresources.department;

public class Position {
    private String id;
    private String position;
    private double resSalary;//responsibility salary
    private String keywordDepartment;

    public Position(String id, String position, double resSalary, String keywordDepartment) {
        this.id = id;
        this.position = position;
        this.resSalary = resSalary;
        this.keywordDepartment = keywordDepartment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setResSalary(double resSalary) {
        this.resSalary = resSalary;
    }

    public void setKeywordDepartment(String keywordDepartment) {
        this.keywordDepartment = keywordDepartment;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public double getResSalary() {
        return resSalary;
    }

    public String getKeywordDepartment() {
        return keywordDepartment;
    }
}
