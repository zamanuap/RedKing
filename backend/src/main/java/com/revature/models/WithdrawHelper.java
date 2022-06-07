package com.revature.models;

public class WithdrawHelper {
    private int userId;
    private double amount;

    public WithdrawHelper() {
    }

    public WithdrawHelper(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public WithdrawHelper userId(int userId) {
        setUserId(userId);
        return this;
    }

    public WithdrawHelper amount(double amount) {
        setAmount(amount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WithdrawHelper)) {
            return false;
        }
        WithdrawHelper withdrawHelper = (WithdrawHelper) o;
        return userId == withdrawHelper.userId && amount == withdrawHelper.amount;
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }


}
