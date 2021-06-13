package com.vudqfx.humanresources.department;

import java.util.Formatter;

public class Department {
    private String id; //ma bo phan
    private String name; // ten bo phan
    private int employee; // so luong nhan vien

    public Department(String id, String name, int employee){
        this.id = id;
        this.name = name;
        this.employee = employee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getEmployee() {
        return employee;
    }

    @Override
    public String toString(){
        Formatter formatter = new Formatter();
        formatter.format("|%4s|%17s|%8s|", id, name, employee);
        return formatter + "";
    }
}
