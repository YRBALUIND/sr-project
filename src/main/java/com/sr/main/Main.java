package com.sr.main;

import com.sr.dto.ManagerSalaryStats;
import com.sr.dto.ReportingLevelStats;
import com.sr.service.OrgHierarchyAnalysisService;
import com.sr.util.Util;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter the path to the CSV file .");
            return;
        }
        
        String csvFilePath = args[0];
        OrgHierarchyAnalysisService analyzer = new OrgHierarchyAnalysisService();
        
        try {
            analyzer.createEmployeesFromCsv(csvFilePath);
            
            // Find managers with salary issues
            List<ManagerSalaryStats> managerStatsWithIssues = analyzer.getManagerStatsWithIssues();


            Util.printManagerStats(managerStatsWithIssues);
            
            // Find employees with long reporting lines
            List<ReportingLevelStats> levelStatsList = analyzer.getReportingLevelStats();

            Util.printLevelStats(levelStatsList);

            
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}