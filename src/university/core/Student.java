package university.core;

import java.util.List; // For List usage
import java.util.ArrayList; // If needed for List initialization
import java.util.Scanner; // For user input
import java.io.Writer; // For writing files
import java.io.FileWriter; // For file writing
import java.io.IOException; // For handling IO exceptions
import university.academics.Grade; // For Grade class
import university.academics.Attendance; // For Attendance class (if defined)
import university.utils.FileHandler; // For FileHandler class
import com.google.gson.reflect.TypeToken; // For TypeToken with Gson
import university.academics.Course;


public class Student extends User {
    private String faculty;

    public Student(String username, String password, String firstName, String lastName, String faculty) {
        super(username, password, firstName, lastName);
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
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
                    return; // Exit the menu loop
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void getTranscript() {
        List<Grade> grades = FileHandler.loadFromFile("src/university/data/grades.json",
                new TypeToken<List<Grade>>() {}.getType());

        StringBuilder transcriptBuilder = new StringBuilder();
        transcriptBuilder.append("Transcript for ").append(getName()).append("\n\n");

        for (Grade grade : grades) {
            if (grade.getStudent().equals(getUsername())) {
                double totalGrade = calculateTotalGrade(grade);
                String letterGrade = getLetterGrade(totalGrade);
                transcriptBuilder.append("- Course: ").append(grade.getCourse())
                        .append("\n  Total Grade: ").append(totalGrade)
                        .append(" (").append(letterGrade).append(")\n");
            }
        }

        String transcript = transcriptBuilder.toString();
        System.out.println(transcript);
    }

    public void saveTranscriptToFile(String fileName) {
        StringBuilder transcriptBuilder = new StringBuilder();
        transcriptBuilder.append("Transcript for ").append(getName()).append("\n\n");

        List<Grade> grades = FileHandler.loadFromFile("src/university/data/grades.json",
                new TypeToken<List<Grade>>() {}.getType());

        for (Grade grade : grades) {
            if (grade.getStudent().equals(getUsername())) {
                double totalGrade = calculateTotalGrade(grade);
                String letterGrade = getLetterGrade(totalGrade);
                transcriptBuilder.append("- Course: ").append(grade.getCourse())
                        .append("\n  Total Grade: ").append(totalGrade)
                        .append(" (").append(letterGrade).append(")\n");
            }
        }

        String transcript = transcriptBuilder.toString();

        try (Writer writer = new FileWriter(fileName)) {
            writer.write(transcript);
            System.out.println("Transcript saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving transcript: " + e.getMessage());
        }
    }

    private double calculateTotalGrade(Grade grade) {
        return grade.getFirstAttestation() * 0.3 +
                grade.getSecondAttestation() * 0.3 +
                grade.getFinalExam() * 0.4;
    }

    private String getLetterGrade(double totalGrade) {
        if (totalGrade >= 90) return "A";
        else if (totalGrade >= 80) return "B";
        else if (totalGrade >= 70) return "C";
        else if (totalGrade >= 60) return "D";
        else return "F";
    }

    public void viewCourses() {
        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new com.google.gson.reflect.TypeToken<List<Course>>() {}.getType());

        System.out.println("\nCourses Enrolled:");
        for (Course course : courses) {
            if (course.getStudentsEnrolled().contains(getUsername())) {
                System.out.println("- " + course.getName());
            }
        }
    }


    public void viewGrades() {
        List<Grade> grades = FileHandler.loadFromFile("src/university/data/grades.json",
                new com.google.gson.reflect.TypeToken<List<Grade>>() {}.getType());

        System.out.println("\nYour Grades:");
        for (Grade grade : grades) {
            if (grade.getStudent().equals(getUsername())) {
                System.out.println("- " + grade.getCourse() + ": First Attestation: " + grade.getFirstAttestation()
                        + ", Second Attestation: " + grade.getSecondAttestation()
                        + ", Final Exam: " + grade.getFinalExam());
            }
        }
    }

}
