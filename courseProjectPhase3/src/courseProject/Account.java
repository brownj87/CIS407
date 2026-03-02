// Jordan Brown, CIS407, CourseProject

package courseProject;

public abstract class Account {
    private String accountNumber;
    private double balance;

    // Getters and setters
    public void setAccountNumber(String accountNumber) throws Exception {
        if (accountNumber == null || accountNumber.isEmpty())
            throw new Exception("Account Number cannot be blank.");
        if (accountNumber.length() > 5)
            throw new Exception("Account Number cannot exceed 5 characters.");
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() { return accountNumber; }

    public void setBalance(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\nBalance: " + balance;
    }

    // --- Abstract methods including date ---
    public abstract void deposit(double amount, String date) throws Exception;
    public abstract void withdrawal(double amount, String date) throws Exception;
    public abstract double balance();
}
