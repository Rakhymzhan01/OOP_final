package university.core;

import university.academics.Course;
import university.academics.Transcript;
import university.grades.Mark;
import university.utils.FileHandler;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private String faculty;
    private Transcript transcript;

    public Student(String username, String password, String firstName, String lastName, String faculty) {
        super(username, password, firstName, lastName);
        this.faculty = faculty;

        // Load grades from JSON for the transcript
        List<Mark> marks = FileHandler.loadFromFile("src/university/data/grades.json",
                new TypeToken<List<Mark>>() {}.getType());
        if (marks == null) {
            System.out.println("Grades file not found or empty.");
            marks = new ArrayList<>(); // Ensure marks list is initialized
        } else {
            System.out.println("Loaded marks: " + marks);
        }

        // Initialize the transcript
        this.transcript = new Transcript(firstName, lastName, username, 1, marks);
        System.out.println("Transcript initialized successfully.");
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void viewCourses() {
        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new TypeToken<List<Course>>() {}.getType());

        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\nCourses Enrolled:");
        for (Course course : courses) {
            System.out.println("- " + course.getName());
        }
    }

    public void viewGrades() {
        if (transcript == null || transcript.getMarks() == null) {
            System.out.println("No grades available.");
            return;
        }

        List<Mark> marks = transcript.getMarks();

        System.out.println("\nYour Grades:");
        for (Mark mark : marks) {
            System.out.println("- " + mark.getCourseName() + ": First Attestation: " + mark.getFirstAttestation()
                    + ", Second Attestation: " + mark.getSecondAttestation()
                    + ", Final Exam: " + mark.getFinalExam()
                    + ", Total: " + mark.getTotalMark()
                    + " (" + mark.getLiteralMark() + ")");
        }
    }

    public void getTranscript() {
        if (transcript == null) {
            System.out.println("Transcript data is unavailable.");
            return;
        }

        System.out.println(transcript.showTranscript());
        saveTranscriptPrompt();
    }

    private void saveTranscriptPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to save this transcript to a file? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.print("Enter the file name to save the transcript (e.g., transcript.txt): ");
            String fileName = scanner.nextLine().trim();
            transcript.saveTranscriptToFile(fileName);
        } else {
            System.out.println("Transcript not saved.");
        }
    }

    @Override
    public void viewMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1 - View Courses");
            System.out.println("2 - View Grades");
            System.out.println("3 - Get Transcript");
            System.out.println("4 - Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewCourses();
                case 2 -> viewGrades();
                case 3 -> getTranscript();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return "Student Details:\n" +
                "Username: " + getUsername() + "\n" +
                "Name: " + getFirstName() + " " + getLastName() + "\n" +
                "Faculty: " + faculty + "\n";
    }
}