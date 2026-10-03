public class TestAccount {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");
        System.out.println("==================================================");

        System.out.println(">>> 1. Creating Account");
        Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println("Account created!");
        System.out.println();
        System.out.println("Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (" + acc1.getAge() + " yrs) | " + acc1.getAccountType() + " | " + acc1.getBalance() + " | " + acc1.getStatus());

        System.out.println(">>> 2. Deposit Money");
        System.out.println();
        boolean dep1 = acc1.deposit(500.0);
        System.out.println("Depositing 500.0: " + (dep1 ? "SUCCESS" : "FAILED (Invalid amount)"));
        System.out.println("New balance: " + acc1.getBalance());
        System.out.println();
        boolean dep2 = acc1.deposit(-100.0);
        System.out.println("Depositing -100.0: " + (dep2 ? "SUCCESS" : "FAILED (Invalid amount)"));

        System.out.println(">>> 3. Withdraw Money");
        System.out.println();
        boolean wd1 = acc1.withdraw(200.0);
        System.out.println("Withdrawing 200.0: " + (wd1 ? "SUCCESS" : "FAILED (Insufficient balance)"));
        System.out.println("New balance: " + acc1.getBalance());
        System.out.println();
        boolean wd2 = acc1.withdraw(2000.0);
        System.out.println("Withdrawing 2000.0: " + (wd2 ? "SUCCESS" : "FAILED (Insufficient balance)"));
        System.out.println("Current balance: " + acc1.getBalance());

        System.out.println(">>> 4. Creating Another Account");
        System.out.println();
        Account acc2 = new Account(1002, "Jane Smith", 30, 2000.0, "Current");
        System.out.println("Account #" + acc2.getAccountNumber() + " | " + acc2.getName() + " (" + acc2.getAge() + " yrs) | " + acc2.getAccountType() + " | " + acc2.getBalance() + " | " + acc2.getStatus());

        System.out.println(">>> 5. All Accounts");
        System.out.println();
        System.out.println("Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (" + acc1.getAge() + " yrs) | " + acc1.getAccountType() + " | " + acc1.getBalance() + " | " + acc1.getStatus());
        System.out.println();
        System.out.println("Account #" + acc2.getAccountNumber() + " | " + acc2.getName() + " (" + acc2.getAge() + " yrs) | " + acc2.getAccountType() + " | " + acc2.getBalance() + " | " + acc2.getStatus());

        System.out.println("==================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("==================================================");
    }
}
