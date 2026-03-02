// Jordan Brown, CIS407, CourseProject
package courseProject;

public class SavingsAccount extends Account implements AccountInterface {

    private final double TRANSACTION_FEE = 0.25;
    private final double INTEREST_RATE = 5.0; // 5%

    @Override
    public void deposit(double amount, String date) throws Exception {
        if (amount <= 0) throw new Exception("Deposit must be positive.");
        setBalance(getBalance() + amount - TRANSACTION_FEE);
        System.out.println("Transaction date: " + date + ", Deposit: " + amount + ", Fee: " + TRANSACTION_FEE);
    }

    @Override
    public void withdrawal(double amount, String date) throws Exception {
        if (amount <= 0) throw new Exception("Withdrawal must be positive.");
        if (amount + TRANSACTION_FEE > getBalance())
            throw new Exception("Insufficient funds for withdrawal.");
        setBalance(getBalance() - amount - TRANSACTION_FEE);
        System.out.println("Transaction date: " + date + ", Withdrawal: " + amount + ", Fee: " + TRANSACTION_FEE);
    }

    @Override
    public double balance() { return getBalance(); }

    public void addInterest() {
        double interest = getBalance() * (INTEREST_RATE / 100);
        setBalance(getBalance() + interest);
        System.out.printf("Interest of %.2f%% applied: %.2f\n", INTEREST_RATE, interest);
    }
}