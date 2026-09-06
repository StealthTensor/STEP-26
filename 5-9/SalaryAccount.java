public class SalaryAccount extends Account {
    private String employerName;
    private int inactiveMonths;

    public SalaryAccount(int accountNumber, String name, int age,
                         double initialBalance, String employerName) {
        super(accountNumber, name, age, initialBalance, "SALARY");
        this.employerName = employerName;
        this.inactiveMonths = 0;
    }

    public String getEmployerName() {
        return employerName;
    }

    public int getInactiveMonths() {
        return inactiveMonths;
    }

    public void setInactiveMonths(int inactiveMonths) {
        this.inactiveMonths = inactiveMonths;
    }
}
