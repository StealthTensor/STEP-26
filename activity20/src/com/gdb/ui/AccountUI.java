package com.gdb.ui;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;
import com.gdb.command.TransactionCommand;
import com.gdb.service.AccountService;
import com.gdb.exceptions.AccountException;

import java.util.*;

public class AccountUI {
    private final AccountService service;
    private final Scanner scanner;

    public AccountUI(AccountService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMainMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1: handleOpenAccount(); break;
                    case 2: handleDeposit(); break;
                    case 3: handleWithdraw(); break;
                    case 4: handleTransfer(); break;
                    case 5: handleCloseAccount(); break;
                    case 6: handleViewAccount(); break;
                    case 7: handleViewTransactions(); break;
                    case 8: System.out.println("Thank you! Goodbye."); return;
                    default: System.out.println("Invalid choice. Please enter 1-8.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Global Digital Bank ===");
        System.out.println("1. Open Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Close Account");
        System.out.println("6. View Account Details");
        System.out.println("7. View Transaction History");
        System.out.println("8. Exit");
    }

    private void handleOpenAccount() throws Exception {
        String type = readString("Account Type (Savings/Current/FixedDeposit/Salary): ");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        double amount = readDouble("Initial Balance: ");
        IAccount acc = service.openAccount(type, name, age, amount);
        System.out.println("SUCCESS: " + acc.getAccountInfo());
        int pin = readInt("Set 4-digit PIN: ");
        acc.setPin(pin);
    }

    private void handleDeposit() throws Exception {
        int accNo = readInt("Account Number: ");
        double amount = readDouble("Amount to deposit: ");
        Transaction txn = service.deposit(accNo, amount);
        System.out.println("Deposit SUCCESS | New Balance: Rs. " + txn.getBalanceAfter());
    }

    private void handleWithdraw() throws Exception {
        int accNo = readInt("Account Number: ");
        double amount = readDouble("Amount to withdraw: ");
        int pin = readInt("PIN: ");
        Transaction txn = service.withdraw(accNo, amount, pin);
        System.out.println("Withdraw SUCCESS | New Balance: Rs. " + txn.getBalanceAfter());
    }

    private void handleTransfer() throws Exception {
        int fromAcc = readInt("From Account: ");
        int toAcc = readInt("To Account: ");
        double amount = readDouble("Amount: ");
        int pin = readInt("PIN: ");
        Transaction txn = service.transfer(fromAcc, toAcc, amount, pin);
        System.out.println("Transfer SUCCESS | " + txn.getDescription());
    }

    private void handleCloseAccount() throws Exception {
        int accNo = readInt("Account Number: ");
        int pin = readInt("PIN: ");
        service.closeAccount(accNo, pin);
        System.out.println("Account #" + accNo + " closed successfully.");
    }

    private void handleViewAccount() {
        int accNo = readInt("Account Number: ");
        IAccount acc = service.getAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found: " + accNo);
        } else {
            System.out.println(acc.getAccountInfo());
        }
    }

    private void handleViewTransactions() {
        List<TransactionCommand> history = service.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions logged.");
        } else {
            System.out.println("Transaction History (" + history.size() + " records):");
            for (int i = 0; i < history.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + history.get(i).getTransaction());
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
