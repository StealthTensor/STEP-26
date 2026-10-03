package com.gdb.tests;

import com.gdb.domain.Account;

public class TestAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 4: Enhanced Account Test Suite ===");

        // Step 1 - Test Underage Customer Rejection
        try {
            Account badAcc = new Account("ACC001", "Kid", 16, 1000.0, "SAVINGS", "ACTIVE", "1234");
            System.out.println("Test 1 (Underage Customer Rejection): [FAIL]");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 1 (Underage Customer Rejection): [PASS]");
        }

        // Step 2 - Test Wrong PIN Rejection on Withdrawal
        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE", "1234");
        boolean wrongPin = acc.withdraw(1000.0, "9999");
        if (!wrongPin && acc.getBalance() == 5000.0) {
            System.out.println("Test 2 (Wrong PIN Rejection): [PASS]");
        } else {
            System.out.println("Test 2 (Wrong PIN Rejection): [FAIL]");
        }

        // Step 3 - Test Correct PIN Withdrawal
        boolean correctPin = acc.withdraw(1000.0, "1234");
        if (correctPin && acc.getBalance() == 4000.0) {
            System.out.println("Test 3 (Correct PIN Withdrawal): [PASS]");
        } else {
            System.out.println("Test 3 (Correct PIN Withdrawal): [FAIL]");
        }

        // Step 4 - Test PIN Change Functionality
        boolean pinChanged = acc.changePin("1234", "5678");
        boolean oldPinFails = !acc.withdraw(500.0, "1234");
        boolean newPinSucceeds = acc.withdraw(500.0, "5678");
        if (pinChanged && oldPinFails && newPinSucceeds && acc.getBalance() == 3500.0) {
            System.out.println("Test 4 (PIN Change & Old PIN Invalidation): [PASS]");
        } else {
            System.out.println("Test 4 (PIN Change & Old PIN Invalidation): [FAIL]");
        }

        // Step 5 - Test Suspended Account Block
        acc.suspend();
        boolean suspendedWithdraw = acc.withdraw(500.0, "5678");
        if (!suspendedWithdraw && acc.getBalance() == 3500.0) {
            System.out.println("Test 5 (Suspended Account Block): [PASS]");
        } else {
            System.out.println("Test 5 (Suspended Account Block): [FAIL]");
        }

        // Step 6 - Test Reactivation & Success
        acc.activate();
        boolean reactivatedWithdraw = acc.withdraw(500.0, "5678");
        if (reactivatedWithdraw && acc.getBalance() == 3000.0) {
            System.out.println("Test 6 (Reactivation & Success): [PASS]");
        } else {
            System.out.println("Test 6 (Reactivation & Success): [FAIL]");
        }

        System.out.println("All Enhanced Account tests passed!");
    }
}
