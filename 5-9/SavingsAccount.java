public class SavingsAccount extends Account {
    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String name, int age,
                          double initialBalance) {
        super(accountNumber, name, age, initialBalance, "SAVINGS");
        this.minBalance = 1000.0;
        this.interestRate = 4.0;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        try {
            deposit(interest);
        } catch (Exception e) {
            System.out.println("Error applying interest: " + e.getMessage());
        }
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
