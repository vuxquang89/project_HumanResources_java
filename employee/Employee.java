package com.vudqfx.humanresources.employee;

import com.vudqfx.humanresources.department.Department;
import com.vudqfx.humanresources.icalculator.ICalculator;
import com.vudqfx.humanresources.staff.Staff;

import java.util.Formatter;

public class Employee extends Staff implements ICalculator {
    private int overtimeHour;

    public Employee(String id, String name, int age, double payRate, String startDate, Department department
            , int numberDayOff, int overtimeHour, double baseSalary){
        super(id, name, age, payRate, baseSalary, startDate, department, numberDayOff);
        this.overtimeHour = overtimeHour;
    }

    public void setOvertimeHour(int overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public int getOvertimeHour() {
        return overtimeHour;
    }

    @Override
    public void displayInformation() {
        Formatter formatter = new Formatter();

        System.out.println(formatter.format("|%4s|%17s|%3s|%12s|%17s|%10s|", super.getId()
                , super.getName(), super.getAge(), super.getDepartment().getName(), "Employee", super.getStartDate()));

    }

    @Override
    public String toString(){
        Formatter formatter = new Formatter();

        formatter.format("|%4s|%17s|%3s|%12s|%17s|%10s|%7s|%8s|%8s|%13s|%15s|", super.getId()
                , super.getName(), super.getAge(), super.getDepartment().getName(), "Employee", super.getStartDate()
                , super.getNumberDayOff(), overtimeHour, super.getPayRate(), super.getBaseSalary(), (int)calculateSalary());
        return formatter + "";
    }

    @Override
    public double calculateSalary() {
        //Hệ số lương * 3,000,000 + số giờ làm thêm * 200,000
        return super.getPayRate() * super.getBaseSalary() + overtimeHour * 200000;
    }
}
