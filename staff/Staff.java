package com.vudqfx.humanresources.staff;

import com.vudqfx.humanresources.department.Department;
import com.vudqfx.humanresources.icalculator.ICalculator;

import java.time.LocalDate;

public abstract class Staff implements ICalculator {
    private String id; //mã nhân viên
    private String name; // tên nhân viên
    private int age; //tuổi nhân viên
    private double payRate; //hệ số lương
    private double baseSalary; // luong co ban
    private String startDate; //ngày vào làm
    private Department department;//bộ phận làm việc
    private int numberDayOff;//số ngày nghỉ phép

    public Staff(){}

    public Staff(String id, String name, int age, double payRate, double baseSalary
            , String startDate, Department department, int numberDayOff) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.payRate = payRate;
        this.baseSalary = baseSalary;
        this.startDate = startDate;
        this.department = department;
        this.numberDayOff = numberDayOff;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setNumberDayOff(int numberDayOff) {
        this.numberDayOff = numberDayOff;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getPayRate() {
        return payRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public Department getDepartment() {
        return department;
    }

    public int getNumberDayOff() {
        return numberDayOff;
    }

    public abstract void displayInformation();//method truu tuong, hien thi thong tin nhan vien
}
