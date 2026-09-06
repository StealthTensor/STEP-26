public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 7: Account Subclasses Test ===");

        SavingsAccount savings = new SavingsAccount(1001, "John Doe", 25, 10000.0);
        System.out.println("Savings Account Created: Balance Rs " + savings.getBalance() +
                           " | Min Balance: Rs " + savings.getMinBalance());

        CurrentAccount current = new CurrentAccount(1002, "Jane Smith", 30, 5000.0);
        System.out.println("Current Account Created: Overdraft Limit Rs " + current.getOverdraftLimit());

        FixedDepositAccount fd = new FixedDepositAccount(1003, "Bob Wilson", 35, 50000.0, 12);
        System.out.println("Fixed Deposit Created: Tenure " + fd.getTenureMonths() +
                           " months | Interest: " + fd.getInterestRate() + "%");

        SalaryAccount salary = new SalaryAccount(1004, "Alice Brown", 28, 15000.0, "Infosys");
        System.out.println("Salary Account Created: Employer " + salary.getEmployerName());

        System.out.println("All subclasses instantiated successfully!");
    }
}
