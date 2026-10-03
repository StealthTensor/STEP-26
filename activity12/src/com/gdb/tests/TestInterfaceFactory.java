package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 12: Factory-Driven System Suite ===");

        try {
            IAccount acc1 = AccountFactory.createAccount("SAVINGS", "SAV1001", "Rajesh Sharma", 28, 5000.0, "ACTIVE", "1234");
            acc1.deposit(2000.0);
            boolean p1 = (acc1.getBalance() == 7000.0);
            System.out.println("[Test 1] Savings Account Creation & Deposit: " + (p1 ? "[PASS]" : "[FAIL]"));
        } catch (AccountException e) {
            System.out.println("[Test 1] [FAIL]");
        }

        try {
            IAccount acc2 = AccountFactory.createAccount("CURRENT", "CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678");
            acc2.withdraw(8000.0, "5678");
            boolean p2 = (acc2.getBalance() == -3000.0);
            System.out.println("[Test 2] Current Account Overdraft Withdrawal: " + (p2 ? "[PASS]" : "[FAIL]"));
        } catch (AccountException e) {
            System.out.println("[Test 2] [FAIL]");
        }

        try {
            IAccount acc3 = AccountFactory.createAccount("FIXED_DEPOSIT", "FD1001", "Amit Kumar", 45, 50000.0, "ACTIVE", "1111");
            acc3.withdraw(5000.0, "1111");
            System.out.println("[Test 3] [FAIL]");
        } catch (AccountException e) {
            System.out.println("[Test 3] Fixed Deposit Premature Withdrawal Block: [PASS]");
        }

        try {
            AccountFactory.createAccount("INVALID_TYPE", "INV001", "Test", 30, 1000.0, "ACTIVE", "0000");
            System.out.println("[Test 4] [FAIL]");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test 4] Invalid Type Rejection: [PASS]");
        }

        System.out.println("Factory-driven architecture successfully verified!");
    }
}
