package com.vudqfx.humanresources.manager;

import com.vudqfx.humanresources.department.Department;
import com.vudqfx.humanresources.department.Position;
import com.vudqfx.humanresources.icalculator.ICalculator;
import com.vudqfx.humanresources.staff.Staff;

import java.util.Formatter;

public class Manager extends Staff implements ICalculator {
    private Position position; // chuc danh

    public Manager(String id, String name, int age, double payRate, double baseSalary, String startDate
            , Department department, int numberDayOff, Position position){
        super(id, name, age, payRate, baseSalary, startDate, department, numberDayOff);
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
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
                , super.getName(), super.getAge(), super.getDepartment().getName(), position.getPosition(), super.getStartDate()
                , super.getNumberDayOff(), "", super.getPayRate(), super.getBaseSalary(), (int)calculateSalary());
        return formatter + "";
    }

    @Override
    public double calculateSalary() {
        /*
        Quản lý: Hệ số lương * 5,000,000 + lương trách nhiệm

        Lương trách nhiệm:

        Business Leader = 8,000,000

        Project Leader = 5,000,000

        Technical Leader = 6,000,000
         */
        return super.getBaseSalary() * super.getPayRate() + position.getResSalary();
    }
}
