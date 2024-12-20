package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.academics.LectureAttendance;
import university.academics.StudentAttendance;
import university.academics.Course;
import university.utils.FileHandler;
import com.google.gson.reflect.TypeToken;
import university.academics.Grade;


public class Teacher extends Employee {
    private String subject;

    public Teacher(String username, String password, String firstName, String lastName, String subject) {
        super(username, password, firstName, lastName);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public void viewMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nTeacher Menu:");
            System.out.println("1 - Mark Attendance");
            System.out.println("2 - View Attendance");
            System.out.println("3 - Assign Grades");
            System.out.println("4 - Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> markAttendance();
                case 2 -> {
                    System.out.print("Enter the course ID to view attendance: ");
                    String courseId = scanner.nextLine();
                    viewAttendance(courseId);
                }
                case 3 -> assignGrades();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private List<String> getEnrolledStudents(String courseId) {
        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new TypeToken<List<Course>>() {}.getType());

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course.getStudentsEnrolled();
            }
        }

        return List.of();
    }

    public void markAttendance() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the course ID to mark attendance: ");
        String courseId = scanner.nextLine();

        System.out.print("Enter the lecture identifier (e.g., Lecture 1): ");
        String lecture = scanner.nextLine();

        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new TypeToken<List<Course>>() {}.getType());

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                LectureAttendance lectureAttendance = new LectureAttendance(lecture, new ArrayList<>());

                for (String student : course.getStudentsEnrolled()) {
                    System.out.print("Mark attendance for " + student + " (P/A): ");
                    String status = scanner.nextLine().equalsIgnoreCase("P") ? "Present" : "Absent";

                    lectureAttendance.getStudents().add(new StudentAttendance(student, status));
                }

                course.getAttendanceRecords().add(lectureAttendance);
                FileHandler.saveToFile(courses, "src/university/data/courses.json");
                System.out.println("Attendance marked successfully for " + lecture);
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void viewAttendance(String courseId) {
        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new TypeToken<List<Course>>() {}.getType());

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                System.out.println("Attendance Records for Course: " + courseId);

                if (course.getAttendanceRecords() == null || course.getAttendanceRecords().isEmpty()) {
                    System.out.println("No attendance records available for this course.");
                    return;
                }

                for (LectureAttendance lectureAttendance : course.getAttendanceRecords()) {
                    System.out.println("Lecture: " + lectureAttendance.getLectureId());
                    for (StudentAttendance studentAttendance : lectureAttendance.getStudents()) {
                        System.out.println("- " + studentAttendance.getStudent() + ": " + studentAttendance.getStatus());
                    }
                }
                return;
            }
        }
        System.out.println("Course not found.");
    }



    public void assignGrades() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the course ID: ");
        String courseId = scanner.nextLine();

        System.out.print("Enter the student username: ");
        String studentUsername = scanner.nextLine();

        System.out.print("Enter marks for the first attestation: ");
        double firstAtt = scanner.nextDouble();

        System.out.print("Enter marks for the second attestation: ");
        double secondAtt = scanner.nextDouble();

        System.out.print("Enter marks for the final exam: ");
        double finalExam = scanner.nextDouble();

        Grade grade = new Grade(studentUsername, courseId, firstAtt, secondAtt, finalExam);

        // Load existing grades
        List<Grade> grades = FileHandler.loadFromFile("src/university/data/grades.json",
                new com.google.gson.reflect.TypeToken<List<Grade>>() {}.getType());

        grades.add(grade);

        // Save updated grades
        FileHandler.saveToFile(grades, "src/university/data/grades.json");

        System.out.println("Grades assigned successfully!");
    }
}
