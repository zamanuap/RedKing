package com.revature.models;

public class DepositHelper {
    private int userId;
    private double amount;

    public DepositHelper( int userId, double amount ) {
        this.userId = userId;
        this.amount = amount;
    }

    public DepositHelper() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount( double amount ) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DepositHelper{" +
                "userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
