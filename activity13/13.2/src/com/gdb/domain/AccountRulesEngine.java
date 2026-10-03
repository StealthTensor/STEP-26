package com.gdb.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountRulesEngine {
    private static final Map<String, Double> minBalanceMap = new HashMap<>();
    private static final Map<String, Double> interestRateMap = new HashMap<>();

    static {
        minBalanceMap.put("NEW", 10000.0);
        minBalanceMap.put("STANDARD", 7500.0);
        minBalanceMap.put("PREMIUM", 5000.0);
        minBalanceMap.put("PRIVILEGE", 2500.0);

        interestRateMap.put("NEW", 2.70);
        interestRateMap.put("STANDARD", 3.00);
        interestRateMap.put("PREMIUM", 3.50);
        interestRateMap.put("PRIVILEGE", 4.00);
    }

    public static String getSavingsBucket(int tenureYears) {
        if (tenureYears >= 5) return "PRIVILEGE";
        if (tenureYears >= 3) return "PREMIUM";
        if (tenureYears >= 1) return "STANDARD";
        return "NEW";
    }

    public static double getSavingsMinBalance(int tenureYears) {
        return minBalanceMap.getOrDefault(getSavingsBucket(tenureYears), 10000.0);
    }

    public static double getSavingsInterestRate(int tenureYears) {
        return interestRateMap.getOrDefault(getSavingsBucket(tenureYears), 2.70);
    }

    public static double getCurrentOverdraftLimit(double monthlyTurnover) {
        return Math.max(25000.0, 2.5 * monthlyTurnover);
    }

    public static double getFDInterestRate(int months) {
        if (months >= 36) return 7.50;
        if (months >= 12) return 6.50;
        return 5.00;
    }
}
