package com.sr.service;

import com.sr.dto.Employee;
import com.sr.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class OrgHierarchyAnalysisServiceTest {

    OrgHierarchyAnalysisService service = new OrgHierarchyAnalysisService();
    @Test
    void createEmployeesFromCsv() {
    }

    @Test
    void getManagerStatsWithIssuesWithLess() {
        Employee employee = new Employee("1", "1First", "1Last",100, null );
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("2", "2First", "2Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("3", "3First", "3Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        service.buildOrgHierarchy();
        Util.printManagerStats(service.getManagerStatsWithIssues());
    }

    @Test
    void getManagerStatsWithIssuesNoLess() {
        Employee employee = new Employee("1", "1First", "1Last",120, null );
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("2", "2First", "2Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("3", "3First", "3Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        service.buildOrgHierarchy();
        Util.printManagerStats(service.getManagerStatsWithIssues());
    }

    @Test
    void getManagerStatsWithIssuesMore() {
        Employee employee = new Employee("1", "1First", "1Last",160, null );
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("2", "2First", "2Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("3", "3First", "3Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        service.buildOrgHierarchy();
        Util.printManagerStats(service.getManagerStatsWithIssues());
    }

    @Test
    void getManagerStatsWithIssuesNoMore() {
        Employee employee = new Employee("1", "1First", "1Last",150, null );
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("2", "2First", "2Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("3", "3First", "3Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        service.buildOrgHierarchy();
        Util.printManagerStats(service.getManagerStatsWithIssues());
    }

    @Test
    void getReportingLevelStats_levelsMore() {
        Employee employee = new Employee("1", "1First", "1Last",160, null );
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("2", "2First", "2Last",100, "1");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("3", "3First", "3Last",100, "2");
        service.getEmployees().put(employee.getId(),employee);

        employee = new Employee("4", "4First", "5Last",100, "3");
        service.getEmployees().put(employee.getId(),employee);


        employee = new Employee("5", "5First", "5Last",100, "4");
        service.getEmployees().put(employee.getId(),employee);


        employee = new Employee("6", "6First", "6Last",100, "5");
        service.getEmployees().put(employee.getId(),employee);
        service.buildOrgHierarchy();
        Util.printLevelStats(service.getReportingLevelStats());
    }


}