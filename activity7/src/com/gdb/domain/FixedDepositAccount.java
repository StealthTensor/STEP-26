package com.gdb.domain;

public class FixedDepositAccount extends Account {
    private int tenureMonths = 12;
    private double interestRate = 6.5;

    public FixedDepositAccount(String accountNumber, String name, int age, double balance, String status, String pin) {
        super(accountNumber, name, age, balance, "FIXED_DEPOSIT", status, pin);
    }

    public FixedDepositAccount(String accountNumber, String name, int age, double balance, String status, String pin, int tenureMonths, double interestRate) {
        super(accountNumber, name, age, balance, "FIXED_DEPOSIT", status, pin);
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
    }

    public double calculateMaturityAmount() {
        double monthlyRate = (interestRate / 100.0) / 12.0;
        return this.balance * Math.pow(1.0 + monthlyRate, tenureMonths);
    }

    public int getTenureMonths() {
        return this.tenureMonths;
    }

    public double getInterestRate() {
        return this.interestRate;
    }
}
