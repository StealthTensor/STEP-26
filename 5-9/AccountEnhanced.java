public class AccountEnhanced {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;

        if (age < MIN_AGE) {
            this.age = MIN_AGE;
        } else {
            this.age = age;
        }

        if (accountType.equals("Savings") || accountType.equals("Current")) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
        }

        double minBalance = getMinimumBalance();
        if (initialBalance < minBalance) {
            this.balance = minBalance;
        } else {
            this.balance = initialBalance;
        }

        this.status = "Active";
        this.pin = null;
    }

    public boolean deposit(double amount) {
        if ("Inactive".equals(this.status)) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if ("Inactive".equals(this.status)) {
            return false;
        }
        if (this.pin == null) {
            return false;
        }
        if (this.pin != pin) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }
        if (amount > this.balance) {
            return false;
        }
        if (this.balance - amount < getMinimumBalance()) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean closeAccount() {
        if ("Inactive".equals(this.status)) {
            return false;
        }
        this.status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if ("Active".equals(this.status)) {
            return false;
        }
        this.status = "Active";
        return true;
    }

    public boolean setPin(int pin) {
        if (pin < 1000 || pin > 9999) {
            return false;
        }
        this.pin = pin;
        return true;
    }

    public boolean verifyPin(int pin) {
        if (this.pin == null) {
            return false;
        }
        return this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    private double getMinimumBalance() {
        if ("Savings".equals(this.accountType)) {
            return MIN_BALANCE_SAVINGS;
        } else {
            return MIN_BALANCE_CURRENT;
        }
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
