package university.finance;

import java.util.Scanner;

public class FinanceManager extends university.core.User {
    private double budget;
    private transient FinanceOffice financeOffice; // Marked transient so it's ignored during serialization

    public FinanceManager(String username, String password, String firstName, String lastName, double budget) {
        super(username, password, firstName, lastName);
        this.budget = budget;
        this.financeOffice = new FinanceOffice(); // Initialize FinanceOffice
    }

    // Initialize FinanceOffice after deserialization
    public void initializeFinanceOffice() {
        if (this.financeOffice == null) {
            this.financeOffice = new FinanceOffice();
        }
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public void viewMenu() {
        initializeFinanceOffice(); // Ensure financeOffice is initialized

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFinance Manager Menu:");
            System.out.println("1 - View Budget");
            System.out.println("2 - Update Budget");
            System.out.println("3 - Grant Scholarships");
            System.out.println("4 - Manage Salaries");
            System.out.println("5 - Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewBudget();
                case 2 -> updateBudget();
                case 3 -> financeOffice.grantScholarship();
                case 4 -> financeOffice.manageSalaries();
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // View current budget
    public void viewBudget() {
        System.out.println("Current Budget: " + budget + " tenge");
    }

    // Update the budget
    public void updateBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new budget amount: ");
        double newBudget = scanner.nextDouble();
        setBudget(newBudget);
        System.out.println("Budget updated successfully to " + newBudget + " tenge");
    }
}