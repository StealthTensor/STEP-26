package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;
import com.gdb.exceptions.*;

public class TestTransfer {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 15 — TRANSFER WITH DAILY LIMITS");
        System.out.println("=".repeat(60));

        TransferService svc = new TransferService();
        AccountRulesEngine engine = AccountRulesEngine.getInstance();

        // STEP 9: Create Two Accounts And Set PIN
        Account acc1 = (Account) AccountFactory.createAccount("SAVINGS", 1001, "Rajesh Sharma", 30, 100000);
        Account acc2 = (Account) AccountFactory.createAccount("SAVINGS", 1002, "Priya Patel", 28, 20000);
        acc1.setPin(1234);
        System.out.println();
        System.out.println("[STEP 9] " + acc1);
        System.out.println("[STEP 9] " + acc2);
        System.out.println();

        // STEP 10: Successful Transfer
        svc.transfer(acc1, acc2, 5000, 1234);
        System.out.println("[STEP 10] Transfer Rs. 5,000: SUCCESS | acc1 = Rs. " + acc1.getBalance() + " | acc2 = Rs. " + acc2.getBalance());

        // STEP 11: Insufficient Balance
        try {
            svc.transfer(acc1, acc2, 100000, 1234);
        } catch (InsufficientBalanceException e) {
            System.out.println("[STEP 11] Caught InsufficientBalanceException: " + e.getMessage());
        }

        // STEP 12: Daily Limit Breach
        System.out.println("[STEP 12] Daily limit for acc1: Rs. " + acc1.getDailyTransferLimit());
        int count = 1;
        while (true) {
            try {
                svc.transfer(acc1, acc2, 20000, 1234);
                System.out.println("  Transfer #" + count + " of Rs. 20,000: SUCCESS | used today = Rs. " + acc1.getDailyTransferTotal());
                count++;
            } catch (AccountException e) {
                System.out.println("[STEP 12] Caught AccountException: " + e.getMessage());
                break;
            }
        }

        // STEP 13: Print Remaining Limit
        System.out.println("[STEP 13] acc1 used today: Rs. " + acc1.getDailyTransferTotal() + " | remaining today: Rs. " + acc1.getRemainingDailyTransferLimit());
    }
}
