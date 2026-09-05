public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");

        // Test 1: Valid Account Creation
        System.out.println(">>> Test 1: Valid Account Creation");
        System.out.println();
        try {
            Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
            System.out.println("SUCCESS: Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (" + acc1.getAge() + " yrs) | " + acc1.getAccountType() + " | " + acc1.getBalance() + " | " + acc1.getStatus() + " | PIN: " + (acc1.hasPin() ? "Yes" : "No"));
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 2: Invalid Age (under 18)
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println();
        try {
            Account acc2 = new Account(1002, "Young Kid", 16, 500.0, "Savings");
            System.out.println("SUCCESS: Account created");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 3: Invalid Account Type
        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println();
        try {
            Account acc3 = new Account(1003, "Test User", 25, 500.0, "Invalid");
            System.out.println("SUCCESS: Account created");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 4: Minimum Balance on Creation
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println();
        System.out.println("Creating Savings account with 300");
        try {
            Account acc4 = new Account(1004, "Bob Wilson", 25, 300.0, "Savings");
            System.out.println("SUCCESS: Account created");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 5: Valid Deposit and Withdrawal
        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        System.out.println();
        try {
            Account acc5 = new Account(1005, "Alice Brown", 30, 1000.0, "Current");
            System.out.println("Account: Account #" + acc5.getAccountNumber() + " | " + acc5.getName() + " (" + acc5.getAge() + " yrs) | " + acc5.getAccountType() + " | " + acc5.getBalance() + " | " + acc5.getStatus() + " | PIN: " + (acc5.hasPin() ? "Yes" : "No"));

            acc5.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");

            acc5.deposit(500.0);
            System.out.println("Depositing 500.0: SUCCESS");
            System.out.println("Balance after deposit: " + acc5.getBalance());

            acc5.withdraw(200.0, 1234);
            System.out.println("Withdrawing 200.0: SUCCESS");
            System.out.println("Balance after withdrawal: " + acc5.getBalance());

            System.out.println("Account #" + acc5.getAccountNumber() + " | " + acc5.getName() + " (" + acc5.getAge() + " yrs) | " + acc5.getAccountType() + " | " + acc5.getBalance() + " | " + acc5.getStatus() + " | PIN: " + (acc5.hasPin() ? "Yes" : "No"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 6: Invalid Deposit (Negative Amount)
        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        System.out.println();
        try {
            Account acc6 = new Account(1006, "Charlie Green", 35, 500.0, "Savings");
            acc6.setPin(1234);
            System.out.println("Attempting to deposit -100.0");
            acc6.deposit(-100.0);
            System.out.println("Deposit successful");
        } catch (InvalidAmountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 7: Insufficient Balance
        System.out.println(">>> Test 7: Insufficient Balance");
        System.out.println();
        try {
            Account acc7 = new Account(1007, "Diana Prince", 28, 1000.0, "Savings");
            acc7.setPin(1234);
            System.out.println("Account: Account #" + acc7.getAccountNumber() + " | " + acc7.getName() + " (" + acc7.getAge() + " yrs) | " + acc7.getAccountType() + " | " + acc7.getBalance() + " | " + acc7.getStatus() + " | PIN: " + (acc7.hasPin() ? "Yes" : "No"));

            System.out.println("Attempting to withdraw 2000.0");
            acc7.withdraw(2000.0, 1234);
            System.out.println("Withdrawal successful");
        } catch (InsufficientBalanceException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 8: Minimum Balance Violation
        System.out.println(">>> Test 8: Minimum Balance Violation");
        System.out.println();
        try {
            Account acc8 = new Account(1008, "Eve Wilson", 32, 1000.0, "Savings");
            acc8.setPin(1234);
            System.out.println("Account: Account #" + acc8.getAccountNumber() + " | " + acc8.getName() + " (" + acc8.getAge() + " yrs) | " + acc8.getAccountType() + " | " + acc8.getBalance() + " | " + acc8.getStatus() + " | PIN: " + (acc8.hasPin() ? "Yes" : "No"));

            System.out.println("Attempting to withdraw 600.0");
            acc8.withdraw(600.0, 1234);
            System.out.println("Withdrawal successful");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 9: Inactive Account Operations
        System.out.println(">>> Test 9: Inactive Account Operations");
        System.out.println();
        try {
            Account acc9 = new Account(1009, "Frank Miller", 40, 2000.0, "Current");
            System.out.println("Account: Account #" + acc9.getAccountNumber() + " | " + acc9.getName() + " (" + acc9.getAge() + " yrs) | " + acc9.getAccountType() + " | " + acc9.getBalance() + " | " + acc9.getStatus() + " | PIN: " + (acc9.hasPin() ? "Yes" : "No"));

            acc9.closeAccount();
            System.out.println("Closing account: SUCCESS");

            System.out.println("Attempting to deposit 100.0 on closed account");
            acc9.deposit(100.0);
            System.out.println("Deposit successful");
        } catch (InactiveAccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        try {
            Account acc9b = new Account(1009, "Frank Miller", 40, 2000.0, "Current");
            acc9b.closeAccount();
            acc9b.reopenAccount();
            System.out.println("Reopening account: SUCCESS");

            acc9b.deposit(100.0);
            System.out.println("Depositing 100.0 after reopen: SUCCESS");
            System.out.println("Balance after deposit: " + acc9b.getBalance());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 10: PIN Verification
        System.out.println(">>> Test 10: PIN Verification");
        System.out.println();
        try {
            Account acc10 = new Account(1010, "Grace Lee", 29, 1500.0, "Savings");
            System.out.println("Account: Account #" + acc10.getAccountNumber() + " | " + acc10.getName() + " (" + acc10.getAge() + " yrs) | " + acc10.getAccountType() + " | " + acc10.getBalance() + " | " + acc10.getStatus() + " | PIN: " + (acc10.hasPin() ? "Yes" : "No"));

            acc10.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");

            acc10.withdraw(200.0, 1234);
            System.out.println("Withdrawing 200.0 with correct PIN: SUCCESS");
            System.out.println("Balance: " + acc10.getBalance());

            System.out.println("Attempting to withdraw 100.0 with incorrect PIN (9999)");
            acc10.withdraw(100.0, 9999);
            System.out.println("Withdrawal successful");
        } catch (InvalidPinException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        try {
            Account acc10b = new Account(1010, "Grace Lee", 29, 1500.0, "Savings");
            System.out.println("Attempting to withdraw 100.0 without PIN set");
            acc10b.withdraw(100.0, 1234);
            System.out.println("Withdrawal successful");
        } catch (InvalidPinException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 11: All Accounts Summary
        System.out.println(">>> Test 11: All Accounts Summary");
        System.out.println();
        try {
            Account[] accounts = new Account[6];
            accounts[0] = new Account(1001, "John Doe", 25, 1000.0, "Savings");
            accounts[1] = new Account(1005, "Alice Brown", 30, 1300.0, "Current");
            accounts[1].setPin(1234);
            accounts[2] = new Account(1006, "Charlie Green", 35, 500.0, "Savings");
            accounts[2].setPin(1234);
            accounts[3] = new Account(1007, "Diana Prince", 28, 1000.0, "Savings");
            accounts[3].setPin(1234);
            accounts[4] = new Account(1008, "Eve Wilson", 32, 2000.0, "Current");
            accounts[5] = new Account(1009, "Frank Miller", 40, 1300.0, "Savings");
            accounts[5].setPin(1234);

            for (Account acc : accounts) {
                System.out.println("Account #" + acc.getAccountNumber() + " | " + acc.getName() + " (" + acc.getAge() + " yrs) | " + acc.getAccountType() + " | " + acc.getBalance() + " | " + acc.getStatus() + " | PIN: " + (acc.hasPin() ? "Yes" : "No"));
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}
