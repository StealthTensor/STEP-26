public class TestAccountEnhanced {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ENHANCED ACCOUNT TEST (BOOLEAN RETURNS)");
        System.out.println("============================================================");

        System.out.println(">>> Test 1: Valid Account Creation");
        System.out.println();
        AccountEnhanced acc1 = new AccountEnhanced(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println("Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (" + acc1.getAge() + " yrs) | " + acc1.getAccountType() + " | " + acc1.getBalance() + " | " + acc1.getStatus() + " | PIN: " + (acc1.hasPin() ? "Yes" : "No"));

        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");
        System.out.println("Age auto-corrected to: 18");
        System.out.println();
        AccountEnhanced acc2 = new AccountEnhanced(1002, "Young Kid", 16, 500.0, "Savings");
        System.out.println("Account #" + acc2.getAccountNumber() + " | " + acc2.getName() + " (" + acc2.getAge() + " yrs) | " + acc2.getAccountType() + " | " + acc2.getBalance() + " | " + acc2.getStatus() + " | PIN: " + (acc2.hasPin() ? "Yes" : "No"));

        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println();
        System.out.println("Creating account with type \"Invalid\"");
        System.out.println("Account type defaulted to: Savings");
        System.out.println();
        AccountEnhanced acc3 = new AccountEnhanced(1003, "Test User", 25, 500.0, "Invalid");
        System.out.println("Account #" + acc3.getAccountNumber() + " | " + acc3.getName() + " (" + acc3.getAge() + " yrs) | " + acc3.getAccountType() + " | " + acc3.getBalance() + " | " + acc3.getStatus() + " | PIN: " + (acc3.hasPin() ? "Yes" : "No"));

        System.out.println(">>> Test 4: Minimum Balance Enforcement on Creation");
        System.out.println("Creating Savings account with 300 (below minimum)");
        System.out.println("Balance auto-corrected to minimum: 500.0");
        System.out.println();
        AccountEnhanced acc4 = new AccountEnhanced(1004, "Bob Wilson", 25, 300.0, "Savings");
        System.out.println("Account #" + acc4.getAccountNumber() + " | " + acc4.getName() + " (" + acc4.getAge() + " yrs) | " + acc4.getAccountType() + " | " + acc4.getBalance() + " | " + acc4.getStatus() + " | PIN: " + (acc4.hasPin() ? "Yes" : "No"));

        System.out.println(">>> Test 5: Withdrawal with Minimum Balance");
        System.out.println();
        AccountEnhanced acc5 = new AccountEnhanced(1005, "Alice Brown", 30, 1000.0, "Current");
        acc5.setPin(1234);
        System.out.println("Initial: Account #" + acc5.getAccountNumber() + " | " + acc5.getName() + " (" + acc5.getAge() + " yrs) | " + acc5.getAccountType() + " | " + acc5.getBalance() + " | " + acc5.getStatus() + " | PIN: " + (acc5.hasPin() ? "Yes" : "No"));

        boolean wd1 = acc5.withdraw(200.0, 1234);
        System.out.println("Withdrawing 200.0: " + (wd1 ? "SUCCESS" : "FAILED (Minimum balance violation)"));
        System.out.println("New balance: " + acc5.getBalance());
        System.out.println();
        System.out.println("After withdrawal: Account #" + acc5.getAccountNumber() + " | " + acc5.getName() + " (" + acc5.getAge() + " yrs) | " + acc5.getAccountType() + " | " + acc5.getBalance() + " | " + acc5.getStatus() + " | PIN: " + (acc5.hasPin() ? "Yes" : "No"));

        boolean wd2 = acc5.withdraw(900.0, 1234);
        System.out.println("Withdrawing 900.0 (would leave -100): " + (wd2 ? "SUCCESS" : "FAILED (Minimum balance violation)"));
        System.out.println("Current balance: " + acc5.getBalance());

        System.out.println(">>> Test 6: Account Status Management");
        System.out.println();
        AccountEnhanced acc6 = new AccountEnhanced(1006, "Charlie Green", 35, 2000.0, "Savings");
        System.out.println("Initial: Account #" + acc6.getAccountNumber() + " | " + acc6.getName() + " (" + acc6.getAge() + " yrs) | " + acc6.getAccountType() + " | " + acc6.getBalance() + " | " + acc6.getStatus() + " | PIN: " + (acc6.hasPin() ? "Yes" : "No"));

        boolean closed = acc6.closeAccount();
        System.out.println("Closing account: " + (closed ? "SUCCESS" : "FAILED"));
        System.out.println();
        System.out.println("After close: Account #" + acc6.getAccountNumber() + " | " + acc6.getName() + " (" + acc6.getAge() + " yrs) | " + acc6.getAccountType() + " | " + acc6.getBalance() + " | " + acc6.getStatus() + " | PIN: " + (acc6.hasPin() ? "Yes" : "No"));

        boolean depClosed = acc6.deposit(500.0);
        System.out.println("Depositing 500.0 to closed account: " + (depClosed ? "SUCCESS" : "FAILED (Account inactive)"));

        boolean reopened = acc6.reopenAccount();
        System.out.println("Reopening account: " + (reopened ? "SUCCESS" : "FAILED"));
        System.out.println();
        System.out.println("After reopen: Account #" + acc6.getAccountNumber() + " | " + acc6.getName() + " (" + acc6.getAge() + " yrs) | " + acc6.getAccountType() + " | " + acc6.getBalance() + " | " + acc6.getStatus() + " | PIN: " + (acc6.hasPin() ? "Yes" : "No"));

        System.out.println(">>> Test 7: PIN Protection");
        System.out.println();
        AccountEnhanced acc7 = new AccountEnhanced(1007, "Diana Prince", 28, 1500.0, "Savings");
        boolean pinSet = acc7.setPin(1234);
        System.out.println("Setting PIN 1234: " + (pinSet ? "SUCCESS" : "FAILED"));

        boolean wdPin = acc7.withdraw(200.0, 1234);
        System.out.println("Withdrawing 200.0 with correct PIN (1234): " + (wdPin ? "SUCCESS" : "FAILED (Incorrect PIN)"));
        System.out.println("New balance: " + acc7.getBalance());

        boolean wdWrongPin = acc7.withdraw(100.0, 9999);
        System.out.println("Withdrawing 100.0 with incorrect PIN (9999): " + (wdWrongPin ? "SUCCESS" : "FAILED (Incorrect PIN)"));

        AccountEnhanced accNoPin = new AccountEnhanced(1008, "Eve Wilson", 32, 2000.0, "Current");
        boolean wdNoPin = accNoPin.withdraw(100.0, 1234);
        System.out.println("Withdrawing 100.0 with PIN not set: " + (wdNoPin ? "SUCCESS" : "FAILED (PIN not set)"));

        System.out.println(">>> Test 8: All Accounts Summary");
        System.out.println();
        System.out.println("Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (" + acc1.getAge() + " yrs) | " + acc1.getAccountType() + " | " + acc1.getBalance() + " | " + acc1.getStatus() + " | PIN: " + (acc1.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc2.getAccountNumber() + " | " + acc2.getName() + " (" + acc2.getAge() + " yrs) | " + acc2.getAccountType() + " | " + acc2.getBalance() + " | " + acc2.getStatus() + " | PIN: " + (acc2.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc3.getAccountNumber() + " | " + acc3.getName() + " (" + acc3.getAge() + " yrs) | " + acc3.getAccountType() + " | " + acc3.getBalance() + " | " + acc3.getStatus() + " | PIN: " + (acc3.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc4.getAccountNumber() + " | " + acc4.getName() + " (" + acc4.getAge() + " yrs) | " + acc4.getAccountType() + " | " + acc4.getBalance() + " | " + acc4.getStatus() + " | PIN: " + (acc4.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc5.getAccountNumber() + " | " + acc5.getName() + " (" + acc5.getAge() + " yrs) | " + acc5.getAccountType() + " | " + acc5.getBalance() + " | " + acc5.getStatus() + " | PIN: " + (acc5.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc6.getAccountNumber() + " | " + acc6.getName() + " (" + acc6.getAge() + " yrs) | " + acc6.getAccountType() + " | " + acc6.getBalance() + " | " + acc6.getStatus() + " | PIN: " + (acc6.hasPin() ? "Yes" : "No"));
        System.out.println("Account #" + acc7.getAccountNumber() + " | " + acc7.getName() + " (" + acc7.getAge() + " yrs) | " + acc7.getAccountType() + " | " + acc7.getBalance() + " | " + acc7.getStatus() + " | PIN: " + (acc7.hasPin() ? "Yes" : "No"));

        System.out.println("============================================================");
        System.out.println("ENHANCED TEST COMPLETED!");
        System.out.println("============================================================");
    }
}
