package com.sr.service;

import com.sr.dto.Employee;
import com.sr.dto.ManagerSalaryStats;
import com.sr.dto.ReportingLevelStats;
import com.sr.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrgHierarchyAnalysisService {
    private static final double SALARY_MIN_THRESHOLD = 1.2; // at least 20% more
    private static final double SALARY_MAX_THRESHOLD = 1.5; //  50% more
    private static final int LEVELS_MAX = 4; // Maximum number of levels before  CEO
    
    private  Map<String, Employee> employees = new HashMap<>();
    private Employee rootManager;

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public Employee getRootManager() {
        return rootManager;
    }

    public void setRootManager(Employee rootManager) {
        this.rootManager = rootManager;
    }

    public void createEmployeesFromCsv(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                double salary = Double.parseDouble(parts[3]);
                String managerId = parts.length > 4 && !parts[4].isEmpty() ? parts[4] : null;
                
                Employee employee = new Employee(id, firstName, lastName, salary, managerId);
                employees.put(id, employee);
                
                if (managerId == null || managerId.isEmpty()) {
                    rootManager = employee;
                }
            }
            

            // Build the hierarchy
            buildOrgHierarchy();


        }
    }

    public void buildOrgHierarchy(){
        employees.values().forEach(this::populateMangerAndSubordinates);

    }

    private void populateMangerAndSubordinates(Employee employee){
        String managerId = employee.getManagerId();
        if (managerId != null && !managerId.isEmpty()) {
            Employee manager = employees.get(managerId);
            if (manager != null) {
                employee.setManager(manager);
                manager.addSubordinate(employee);
            }
        }
    }

    public List<ManagerSalaryStats> getManagerStatsWithIssues() {
        List<ManagerSalaryStats> employeeSalaryStatsList = new ArrayList<>();
        
        for (Employee employee : employees.values()) {
            if (!employee.getSubordinates().isEmpty()) {
                double avgSubordinateSalary = Util.getAvgSalUnderEmployee(employee);
                double minExpectedSalary = avgSubordinateSalary * SALARY_MIN_THRESHOLD;
                double maxExpectedSalary = avgSubordinateSalary * SALARY_MAX_THRESHOLD;
                double actualSalary = employee.getSalary();
                
                if (actualSalary < minExpectedSalary) {
                    double diff = minExpectedSalary - actualSalary;
                    employeeSalaryStatsList.add( createStats(employee, diff, true));
                } else if (actualSalary > maxExpectedSalary) {
                    double excess = actualSalary - maxExpectedSalary;
                    employeeSalaryStatsList.add(createStats(employee, excess, false));
                }
            }
        }
        
        return employeeSalaryStatsList;
    }

    private ManagerSalaryStats createStats(Employee employee, double diff, boolean isGapThere){
        ManagerSalaryStats employeeSalaryStats = new ManagerSalaryStats();
        employeeSalaryStats.setAmount(diff);
        employeeSalaryStats.setManager(employee);
        employeeSalaryStats.setGapThere(isGapThere);
        return employeeSalaryStats;
    }
    
    public List<ReportingLevelStats> getReportingLevelStats() {
        List<ReportingLevelStats> reportingLevelStatsList = new ArrayList<>();
        
        for (Employee employee : employees.values()) {
            int lineLength = Util.getReportingLevelLength(employee);
            if (lineLength > LEVELS_MAX) {
                int excess = lineLength - LEVELS_MAX;
                reportingLevelStatsList.add(createReportingLevelStats(employee, excess));
            }
        }
        
        return reportingLevelStatsList;
    }


    private ReportingLevelStats createReportingLevelStats(Employee employee, int levelsExceeded){
        ReportingLevelStats reportingLevelStats = new ReportingLevelStats();
        reportingLevelStats.setEmployee(employee);
        reportingLevelStats.setExcess(levelsExceeded);
        return  reportingLevelStats;
    }

    

}
