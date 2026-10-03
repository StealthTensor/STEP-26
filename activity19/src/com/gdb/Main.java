package com.gdb;

import com.gdb.domain.IAccount;
import com.gdb.logging.FileLogDestination;
import com.gdb.logging.LogDestination;
import com.gdb.logging.TransactionLogger;
import com.gdb.service.AccountService;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Global Digital Bank — Service Demo");
        System.out.println("=".repeat(60));

        LogDestination dest = new FileLogDestination();
        TransactionLogger logger = new TransactionLogger(dest);
        AccountService service = new AccountService(logger);

        IAccount acc1 = service.openAccount("SAVINGS", "John Doe", 25, 15000);
        acc1.setPin(1234);
        service.deposit(acc1.getAccountNumber(), 5000);
        System.out.println("Demo completed successfully!");
    }
}
