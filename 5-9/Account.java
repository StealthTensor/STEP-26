public class Account {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType)
            throws IllegalArgumentException {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: " + age);
        }
        String normalizedType = accountType.toUpperCase();
        if (!normalizedType.equals("SAVINGS") && !normalizedType.equals("CURRENT") &&
            !normalizedType.equals("FIXED_DEPOSIT") && !normalizedType.equals("SALARY")) {
            throw new IllegalArgumentException("Invalid account type. Provided: " + accountType);
        }
        double minBalance = getMinimumBalance(accountType);
        if (initialBalance < minBalance) {
            throw new IllegalArgumentException(accountType + " account requires minimum balance of " + minBalance + ". Provided: " + initialBalance);
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.status = "Active";
        this.pin = null;
    }

    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {
        validateActive();
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive. Provided: " + amount);
        }
        this.balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException, InsufficientBalanceException,
                   MinimumBalanceViolationException, InactiveAccountException,
                   InvalidPinException {
        validateActive();
        if (this.pin == null) {
            throw new InvalidPinException("PIN not set for this account");
        }
        if (this.pin != pin) {
            throw new InvalidPinException("Incorrect PIN");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: " + amount);
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Insufficient balance. Available: " + this.balance + ", Requested: " + amount);
        }
        double minBalance = getMinimumBalance(this.accountType);
        if (this.balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Cannot withdraw. Minimum balance of " + minBalance + " required. Available after withdrawal: " + (this.balance - amount));
        }
        this.balance -= amount;
    }

    public void closeAccount() throws IllegalStateException {
        if ("Inactive".equals(this.status)) {
            throw new IllegalStateException("Account is already closed");
        }
        this.status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {
        if ("Active".equals(this.status)) {
            throw new IllegalStateException("Account is already active");
        }
        this.status = "Active";
    }

    public void setPin(int pin) throws IllegalArgumentException {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number between 1000 and 9999. Provided: " + pin);
        }
        this.pin = pin;
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

    private double getMinimumBalance(String accountType) {
        if ("Savings".equals(accountType)) {
            return MIN_BALANCE_SAVINGS;
        } else {
            return MIN_BALANCE_CURRENT;
        }
    }

    private void validateActive() throws InactiveAccountException {
        if ("Inactive".equals(this.status)) {
            throw new InactiveAccountException("Account is inactive. Please reopen the account or contact support.");
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
