// Jordan Brown, CIS407, CourseProject
package courseProject;

import java.util.ArrayList;

public class BankAcctApp {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        String choice;

        System.out.println("Welcome to the Course Project Banking Application");

        // ---------------------------
        // CUSTOMER AND ACCOUNT CREATION
        // ---------------------------
        do {
            Customer cust = new Customer();
            while (true) {
                try {
                    cust.setCustomerId(DataEntry.getString("Enter Customer ID (max 5): "));
                    cust.setSsn(DataEntry.getString("Enter SSN (9 digits): "));
                    cust.setLastName(DataEntry.getString("Enter Last Name (max 20): "));
                    cust.setFirstName(DataEntry.getString("Enter First Name (max 15): "));
                    cust.setStreet(DataEntry.getString("Enter Street (max 20): "));
                    cust.setCity(DataEntry.getString("Enter City (max 20): "));
                    cust.setState(DataEntry.getString("Enter State (2 chars): "));
                    cust.setZip(DataEntry.getString("Enter Zip Code (5 digits): "));
                    cust.setPhone(DataEntry.getString("Enter Phone Number (10 digits): "));
                    break;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            customers.add(cust);

            // ACCOUNT INPUT
            Account acct = null;
            while (true) {
                try {
                    String type = DataEntry.getString("Enter Account Type (CHK/SAV): ").toUpperCase();
                    if (type.equals("CHK")) acct = new CheckingAccount();
                    else if (type.equals("SAV")) acct = new SavingsAccount();
                    else throw new Exception("Account type must be CHK or SAV.");

                    acct.setAccountNumber(DataEntry.getString("Enter Account Number (max 5): "));
                    acct.setBalance(0); // initial balance
                    break;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            accounts.add(acct);

            choice = DataEntry.getString("Add another customer? (Y/N): ");
        } while (choice.equalsIgnoreCase("Y"));

        // ---------------------------
        // TRANSACTIONS
        // ---------------------------
        for (int i = 0; i < accounts.size(); i++) {
            Account acct = accounts.get(i);
            Customer cust = customers.get(i);
            String more;

            System.out.println("\n--- Transactions for Customer ID: " + cust.getCustomerId()
                    + ", Account Number: " + acct.getAccountNumber()
                    + ", Account Type: " + (acct instanceof CheckingAccount ? "Checking" : "Savings") + " ---");

            do {
                // --- Get transaction type ---
                String transType = "";
                while (true) {
                    try {
                        transType = DataEntry.getString("Enter transaction type (DEP/WTH): ").toUpperCase();
                        if (!transType.equals("DEP") && !transType.equals("WTH"))
                            throw new Exception("Invalid transaction type.");
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    }
                }

                // --- Get transaction amount ---
                double amount = 0;
                while (true) {
                    try {
                        amount = DataEntry.getDouble("Enter transaction amount: ");
                        if (amount <= 0) throw new Exception("Amount must be greater than 0.");
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.");
                    }
                }

                // --- Transaction date ---
                String date = DataEntry.getString("Enter transaction date (YYYY-MM-DD): ");

                double feeApplied = 0;
                double overdraftApplied = 0;

                try {
                    if (transType.equals("DEP")) {
                        double oldBalance = acct.getBalance();
                        acct.deposit(amount, date);
                        feeApplied = oldBalance + amount - acct.getBalance();
                    } else {
                        double oldBalance = acct.getBalance();
                        acct.withdrawal(amount, date);
                        feeApplied = oldBalance - acct.getBalance();
                        if (acct instanceof CheckingAccount && acct.getBalance() < 0)
                            overdraftApplied = 30; // included in the fee
                    }

                    // --- Formatted transaction summary ---
                    System.out.println("\n--- Transaction Summary ---");
                    System.out.printf("Customer ID       : %s\n", cust.getCustomerId());
                    System.out.printf("Account Number    : %s\n", acct.getAccountNumber());
                    System.out.printf("Account Type      : %s\n", (acct instanceof CheckingAccount ? "Checking" : "Savings"));
                    System.out.printf("Transaction Date  : %s\n", date);
                    System.out.printf("Transaction Type  : %s\n", transType);
                    System.out.printf("Transaction Amount: %.2f\n", amount);
                    System.out.printf("Fees/Charges      : %.2f\n", feeApplied);
                    if (overdraftApplied > 0) System.out.printf("Overdraft Fee     : %.2f\n", overdraftApplied);
                    System.out.printf("Balance           : %.2f\n", acct.getBalance());
                    System.out.println("-------------------------------\n");

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

                more = DataEntry.getString("Another transaction for this account? (Y/N): ");
            } while (more.equalsIgnoreCase("Y"));
            // --- Ask to add interest ---
            String addInterest = DataEntry.getString("Add interest for this account? (Y/N): ");
            if (addInterest.equalsIgnoreCase("Y")) {
                if (acct instanceof CheckingAccount) ((CheckingAccount) acct).addInterest();
                else if (acct instanceof SavingsAccount) ((SavingsAccount) acct).addInterest();
                System.out.printf("Interest added. New Balance: %.2f\n", acct.getBalance());
            }
        }
        // ---------------------------
        // FINAL REPORT
        // ---------------------------
        System.out.println("\n=== FINAL ACCOUNT SUMMARY ===");
        for (int i = 0; i < accounts.size(); i++) {
            Customer cust = customers.get(i);
            Account acct = accounts.get(i);
            System.out.println(cust);
            System.out.println("Account Number : " + acct.getAccountNumber());
            System.out.println("Account Type   : " + (acct instanceof CheckingAccount ? "Checking" : "Savings"));
            System.out.printf("Balance        : %.2f\n", acct.getBalance());
            System.out.println("----------------------------------------");
        }
    }
}