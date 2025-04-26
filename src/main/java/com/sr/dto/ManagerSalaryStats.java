package com.sr.dto;

public class ManagerSalaryStats {
    private  Employee manager;
    private  double amount;
    private  boolean isGapThere;

    public Employee getManager() {
        return manager;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isGapThere() {
        return isGapThere;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setGapThere(boolean gapThere) {
        isGapThere = gapThere;
    }
}
