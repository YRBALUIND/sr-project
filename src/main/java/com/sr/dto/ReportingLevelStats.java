package com.sr.dto;

public class ReportingLevelStats {
    private  Employee employee;
    private  int excess;


    public Employee getEmployee() {
        return employee;
    }

    public int getExcess() {
        return excess;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setExcess(int excess) {
        this.excess = excess;
    }
}
