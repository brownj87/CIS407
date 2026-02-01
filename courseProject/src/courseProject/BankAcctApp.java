//Jordan Brown, CIS407, CourseProject
package courseProject;
import java.util.ArrayList;
public class BankAcctApp {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        String choice;
        System.out.println("Welcome to the Course Project Banking Application");
        do {
            Customer cust = new Customer();
            cust.setCustomerId(
                    DataEntry.getStringWithLength("Enter Customer ID (max 5): ", 5));
            cust.setSsn(
                    DataEntry.getNumericString("Enter SSN (9 digits): ", 9));
            cust.setLastName(
                    DataEntry.getStringWithLength("Enter Last Name (max 20): ", 20));
            cust.setFirstName(
                    DataEntry.getStringWithLength("Enter First Name (max 15): ", 15));
            cust.setStreet(
                    DataEntry.getStringWithLength("Enter Street (max 20): ", 20));
            cust.setCity(
                    DataEntry.getStringWithLength("Enter City (max 20): ", 20));
            cust.setState(
                    DataEntry.getStringWithLength("Enter State (2 characters): ", 2));
            cust.setZip(
                    DataEntry.getNumericString("Enter Zip Code (5 digits): ", 5));
            cust.setPhone(
                    DataEntry.getNumericString("Enter Phone Number (10 digits): ", 10));
            customers.add(cust);
            choice = DataEntry.getString("Add another customer? (Y/N): ");

        } while (choice.equalsIgnoreCase("Y"));

        // Output results
        System.out.println("\n========================================");
        System.out.println("Customer Information Report");
        System.out.println("========================================");

        for (Customer c : customers) {
            System.out.println(c);
            System.out.println("----------------------------------------");
        }
    }
}