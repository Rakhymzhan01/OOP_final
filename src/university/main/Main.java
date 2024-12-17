package university.main;

import university.utils.FileHandler;
import university.core.Student;
import university.core.Teacher;
import university.core.Manager;
import university.finance.FinanceManager;
import university.news.News;
import university.core.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String STUDENT_FILE = "src/university/data/students.json";
    private static final String TEACHER_FILE = "src/university/data/teachers.json";
    private static final String MANAGER_FILE = "src/university/data/managers.json";
    private static final String FINANCE_MANAGER_FILE = "src/university/data/budget.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the University Management System!");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Registration method
    public static void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a role to register:");
        System.out.println("1 - Student");
        System.out.println("2 - Teacher");
        System.out.println("3 - Manager");
        System.out.println("4 - Finance Manager");
        System.out.println("5 - Exit");
        System.out.print("Enter your choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (roleChoice == 5) {
            return;
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        switch (roleChoice) {
            case 1 -> {
                System.out.print("Enter faculty: ");
                String faculty = scanner.nextLine();

                System.out.print("Is the student on grant? (yes/no): ");
                boolean onGrant = scanner.nextLine().equalsIgnoreCase("yes");

                System.out.print("Does the student have a scholarship? (yes/no): ");
                boolean scholarship = scanner.nextLine().equalsIgnoreCase("yes");

                Student student = new Student(username, password, firstName, lastName, faculty, onGrant, scholarship);
                saveUser(student, STUDENT_FILE, new TypeToken<List<Student>>() {}.getType());
                System.out.println("Student registered successfully!");
            }
            case 2 -> {
                System.out.print("Enter subject: ");
                String subject = scanner.nextLine();
                Teacher teacher = new Teacher(username, password, firstName, lastName, subject);
                saveUser(teacher, TEACHER_FILE, new TypeToken<List<Teacher>>() {}.getType());
                System.out.println("Teacher registered successfully!");
            }
            case 3 -> {
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                Manager manager = new Manager(username, password, firstName, lastName, department);
                saveUser(manager, MANAGER_FILE, new TypeToken<List<Manager>>() {}.getType());
                System.out.println("Manager registered successfully!");
            }
            case 4 -> {
                FinanceManager financeManager = new FinanceManager(username, password, firstName, lastName, 0.0);
                saveUser(financeManager, FINANCE_MANAGER_FILE, new TypeToken<List<FinanceManager>>() {}.getType());
                System.out.println("Finance Manager registered successfully!");
            }
            default -> System.out.println("Invalid role choice. Registration failed.");
        }
    }

    // Login method
    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check for Student login
        Student student = getUser(username, password, STUDENT_FILE, new TypeToken<List<Student>>() {}.getType());
        if (student != null) {
            System.out.println("Welcome, " + student.getFirstName() + "!");
            student.viewMenu();
            return;
        }

        // Check for Teacher login
        Teacher teacher = getUser(username, password, TEACHER_FILE, new TypeToken<List<Teacher>>() {}.getType());
        if (teacher != null) {
            System.out.println("Welcome, " + teacher.getFirstName() + "!");
            teacher.viewMenu();
            return;
        }

        // Check for Manager login
        Manager manager = getUser(username, password, MANAGER_FILE, new TypeToken<List<Manager>>() {}.getType());
        if (manager != null) {
            System.out.println("Welcome, " + manager.getFirstName() + "!");
            manager.viewMenu();
            return;
        }

        // Check for Finance Manager login
        FinanceManager financeManager = getUser(username, password, FINANCE_MANAGER_FILE, new TypeToken<List<FinanceManager>>() {}.getType());
        if (financeManager != null) {
            System.out.println("Welcome, " + financeManager.getFirstName() + "!");
            financeManager.viewMenu();
            return;
        }

        System.out.println("Invalid credentials. Please try again.");
    }

    private static <T> T getUser(String username, String password, String fileName, Type type) {
        List<T> users = FileHandler.loadFromFile(fileName, type);
        for (T user : users) {
            if (user instanceof User u && u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Save user to JSON file
    private static <T> void saveUser(T user, String fileName, Type type) {
        List<T> users = FileHandler.loadFromFile(fileName, type);
        users.add(user);
        FileHandler.saveToFile(users, fileName);
    }
}