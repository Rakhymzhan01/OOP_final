package university.main;

import university.utils.FileHandler;
import university.core.Student;
import university.core.Teacher;
import university.core.Manager;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the University Management System!");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt(); // Get user input
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return; // Exit the program
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
        System.out.print("Enter your choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

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
                Student student = new Student(username, password, firstName, lastName, faculty);
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
            student.viewMenu(); // Call Student menu
            return; // Prevent returning to the main menu
        }

        // Check for Teacher login
        Teacher teacher = getUser(username, password, TEACHER_FILE, new TypeToken<List<Teacher>>() {}.getType());
        if (teacher != null) {
            System.out.println("Welcome, " + teacher.getFirstName() + "!");
            teacher.viewMenu(); // Call Teacher menu
            return; // Prevent returning to the main menu
        }

        // Check for Manager login
        Manager manager = getUser(username, password, MANAGER_FILE, new TypeToken<List<Manager>>() {}.getType());
        if (manager != null) {
            System.out.println("Welcome, " + manager.getFirstName() + "!");
            manager.viewMenu(); // Call Manager menu
            return; // Prevent returning to the main menu
        }

        // If authentication fails
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

    // Authenticate user
    private static <T> boolean authenticate(String username, String password, String fileName, Type type) {
        List<T> users = FileHandler.loadFromFile(fileName, type);
        for (T user : users) {
            if (user instanceof Student s && s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return true;
            } else if (user instanceof Teacher t && t.getUsername().equals(username) && t.getPassword().equals(password)) {
                return true;
            } else if (user instanceof Manager m && m.getUsername().equals(username) && m.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Display news for students and teachers
    private static void displayNews() {
        System.out.println("Latest News:");
        List<News> newsList = FileHandler.loadFromFile("src/university/data/news.json", new TypeToken<List<News>>() {}.getType());
        if (newsList == null || newsList.isEmpty()) {
            System.out.println("No news available.");
        } else {
            for (News news : newsList) {
                System.out.println(news);
            }
        }
    }

    // Fetch manager object
    private static Manager getManager(String username, String password) {
        List<Manager> managers = FileHandler.loadFromFile(MANAGER_FILE, new TypeToken<List<Manager>>() {}.getType());
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                return manager;
            }
        }
        return null;
    }
}
