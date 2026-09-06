public class FixedDepositAccount extends Account {
    private int tenureMonths;
    private double interestRate;

    public FixedDepositAccount(int accountNumber, String name, int age,
                               double initialBalance, int tenureMonths) {
        super(accountNumber, name, age, initialBalance, "FIXED_DEPOSIT");
        this.tenureMonths = tenureMonths;
        this.interestRate = 6.5;
    }

    public double calculateMaturityAmount() {
        double principal = getBalance();
        double rate = interestRate / 100;
        int compoundsPerYear = 4;
        double maturity = principal * Math.pow(1 + rate / compoundsPerYear, compoundsPerYear * tenureMonths / 12.0);
        return maturity;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
