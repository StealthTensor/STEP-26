public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String name, int age,
                          double initialBalance) {
        super(accountNumber, name, age, initialBalance, "CURRENT");
        this.overdraftLimit = 25000.0;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
