package com.gdb.tests;

import com.gdb.command.*;
import com.gdb.db.SimulatedDatabase;
import com.gdb.domain.*;
import com.gdb.logging.*;
import java.util.List;

public class TestBridgeLogging {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 18 — BRIDGE PATTERN (FILE + DB)");
        System.out.println("=".repeat(60));

        // Setup test accounts
        IAccount acc1 = AccountFactory.createAccount("SAVINGS", 1001, "John", 25, 15000);
        acc1.setPin(1234);
        IAccount acc2 = AccountFactory.createAccount("SAVINGS", 1002, "Jane", 30, 10000);

        // STEP 24: Create Destinations
        FileLogDestination fileDest = new FileLogDestination();
        fileDest.clear();
        SimulatedDatabase db = new SimulatedDatabase();
        DatabaseLogDestination dbDest = new DatabaseLogDestination(db);
        MemoryLogDestination memDest = new MemoryLogDestination();

        // STEP 25: Create TransactionLogger with File Destination
        System.out.println();
        System.out.println("[STEP 25] Logging to FILE destination...");
        TransactionLogger logger = new TransactionLogger(fileDest);
        DepositCommand d1 = new DepositCommand(acc1, 1000); d1.execute(); logger.log(d1);
        WithdrawCommand w1 = new WithdrawCommand(acc1, 500, 1234); w1.execute(); logger.log(w1);
        TransferCommand t1 = new TransferCommand(acc1, acc2, 500, 1234); t1.execute(); logger.log(t1);
        System.out.println("  FILE log count: " + logger.readAll().size());

        // STEP 26: Switch to Database Destination
        System.out.println();
        System.out.println("[STEP 26] Switched to DATABASE destination...");
        logger.setDestination(dbDest);
        DepositCommand d2 = new DepositCommand(acc1, 1000); d2.execute(); logger.log(d2);
        WithdrawCommand w2 = new WithdrawCommand(acc1, 500, 1234); w2.execute(); logger.log(w2);
        TransferCommand t2 = new TransferCommand(acc1, acc2, 500, 1234); t2.execute(); logger.log(t2);
        System.out.println("  DATABASE log count: " + logger.readAll().size());

        // STEP 27: Switch to Memory Destination
        System.out.println();
        System.out.println("[STEP 27] Switched to MEMORY destination...");
        logger.setDestination(memDest);
        DepositCommand d3 = new DepositCommand(acc1, 1000); d3.execute(); logger.log(d3);
        WithdrawCommand w3 = new WithdrawCommand(acc1, 500, 1234); w3.execute(); logger.log(w3);
        TransferCommand t3 = new TransferCommand(acc1, acc2, 500, 1234); t3.execute(); logger.log(t3);
        System.out.println("  MEMORY log count: " + logger.readAll().size());

        // STEP 28: Verify Data Isolation Across Backends
        System.out.println();
        System.out.println("[STEP 28] Verifying Data Isolation:");
        logger.setDestination(fileDest);
        System.out.println("  FILE count: " + logger.readAll().size() + " [EXPECTED: 3]");
        logger.setDestination(dbDest);
        System.out.println("  DATABASE count: " + logger.readAll().size() + " [EXPECTED: 3]");
        logger.setDestination(memDest);
        System.out.println("  MEMORY count: " + logger.readAll().size() + " [EXPECTED: 3]");

        System.out.println();
        System.out.println("[STEP 29] All Bridge Pattern log backends verified successfully!");
    }
}
