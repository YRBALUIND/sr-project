package com.sr.dto;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final String managerId;
    private Employee manager;
    private final List<Employee> subordinates = new ArrayList<>();
    
    public Employee(String id, String firstName, String lastName, double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }
    
    public String getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Double getSalary() {
        return salary;
    }
    
    public String getManagerId() {
        return managerId;
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public List<Employee> getSubordinates() {
        return subordinates;
    }
    
    public void addSubordinate(Employee subordinate) {
        subordinates.add(subordinate);
    }

    
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}
