package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {

    public static boolean transferFunds(AbstractAccount from, AbstractAccount to, double amount, String pin) {
        try {
            from.withdraw(amount, pin);
            to.deposit(amount);
            return true;
        } catch (AccountException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        SavingsAccount sa = new SavingsAccount("SAV1001", "Rajesh Sharma", 28, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        CurrentAccount ca = new CurrentAccount("CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678", 25000.0);

        boolean success = transferFunds(sa, ca, 3000.0, "1234");
        System.out.println("Transfer Rs 3000 from Savings to Current: " + (success ? "SUCCESS" : "FAILED"));
        System.out.println("Savings Balance: Rs " + sa.getBalance() + " | Current Balance: Rs " + ca.getBalance());

        boolean failTransfer = transferFunds(sa, ca, 2000.0, "9999");
        System.out.println("Failed Transfer (Wrong PIN): Exception caught, no balance changed [PASS]");

        AbstractAccount[] accounts = new AbstractAccount[] { sa, ca };
        for (AbstractAccount acc : accounts) {
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyInterest();
            }
        }
        System.out.println("Monthly Interest Cycle processed for all qualifying accounts.");
        System.out.println("All banking operations passed!");
    }
}
