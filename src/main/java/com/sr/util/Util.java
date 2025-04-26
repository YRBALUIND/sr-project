package com.sr.util;


import com.sr.dto.Employee;
import com.sr.dto.ManagerSalaryStats;
import com.sr.dto.ReportingLevelStats;

import java.util.List;

public class Util {

    private Util() {

    }

    public static double  getAvgSalUnderEmployee(Employee employee) {
        if (employee.getSubordinates().isEmpty()) {
            return 0;
        }

        double totalSalary = 0;
        for (Employee subordinate : employee.getSubordinates()) {
            totalSalary += subordinate.getSalary();
        }

        return totalSalary / employee.getSubordinates().size();
    }

    public static int getReportingLevelLength(Employee employee) {
        int length = 0;
        Employee currentManager = employee.getManager();

        while (currentManager != null) {
            length++;
            currentManager = currentManager.getManager();
        }

        return length;
    }

    public static void printManagerStats(List<ManagerSalaryStats> managerStatsWithIssues){
        System.out.println("Managers threshold  is less:");
        boolean foundUnderpaid = false;
        for (ManagerSalaryStats issue : managerStatsWithIssues) {
            if (issue.isGapThere()) {
                foundUnderpaid = true;
                System.out.printf(" %s getting less salary $%.2f less than threshold%n",
                        issue.getManager(), issue.getAmount());
            }
        }
        if (!foundUnderpaid) {
            System.out.println("Nothing");
        }


        System.out.println("\nManagers earning more than they should:");
        boolean foundOverpaid = false;
        for (ManagerSalaryStats issue : managerStatsWithIssues) {
            if (!issue.isGapThere()) {
                foundOverpaid = true;
                System.out.printf("- %s getting $%.2f more than allowed%n",
                        issue.getManager(), issue.getAmount());
            }
        }
        if (!foundOverpaid) {
            System.out.println("Nothing");
        }
    }

    public static void printLevelStats(List<ReportingLevelStats> levelStatsList){
        System.out.println("\nEmployees with levels more than threshold:");
        if (levelStatsList.isEmpty()) {
            System.out.println("Nothing");
        } else {
            for (ReportingLevelStats issue : levelStatsList) {
                System.out.printf("- %s employee levels of   %d managers threshold exceeded %n",
                        issue.getEmployee(), issue.getExcess());
            }
        }
    }



}
