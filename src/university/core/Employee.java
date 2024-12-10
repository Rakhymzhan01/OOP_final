package university.core;

public class Employee extends User {
    private String department;
    private String role;
    private double salary;

    public Employee(String username, String password, String firstName, String lastName, String department, String role, double salary) {
        super(username, password, firstName, lastName);
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    // Getters and setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Display employee-specific menu
    @Override
    public void viewMenu() {
        System.out.println("\nEmployee Menu:");
        System.out.println("1 - View Profile");
        System.out.println("2 - Update Profile");
        System.out.println("3 - Logout");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> viewProfile();
                case 2 -> updateProfile();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // View profile details
    public void viewProfile() {
        System.out.println("Employee Profile:");
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Username: " + getUsername());
        System.out.println("Department: " + department);
        System.out.println("Role: " + role);
        System.out.println("Salary: $" + salary);
    }

    // Update profile details
    public void updateProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new department (current: " + department + "): ");
        department = scanner.nextLine();

        System.out.print("Enter new role (current: " + role + "): ");
        role = scanner.nextLine();

        System.out.print("Enter new salary (current: $" + salary + "): ");
        salary = scanner.nextDouble();

        System.out.println("Profile updated successfully!");
    }
}
