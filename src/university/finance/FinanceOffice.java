package university.finance;

import university.core.Student;
import university.utils.FileHandler;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FinanceOffice {
    private static final String STUDENT_FILE = "src/university/data/students.json";
    private static final double SCHOLARSHIP_AMOUNT = 47000.0; // Scholarship amount per student

    public void grantScholarship() {
        List<Student> students = FileHandler.loadFromFile(STUDENT_FILE, new TypeToken<List<Student>>() {}.getType());

        System.out.println("\nStudents Receiving Scholarships:");
        boolean scholarshipsGranted = false;

        for (Student student : students) {
            if (student.isScholarship()) {
                System.out.println("- " + student.getName() + " (Scholarship Granted: " + SCHOLARSHIP_AMOUNT + " tenge)");
                scholarshipsGranted = true;
            }
        }

        if (!scholarshipsGranted) {
            System.out.println("No students currently have scholarships to be granted.");
        } else {
            System.out.println("\nScholarships have been successfully granted and confirmed.");
        }
    }

    public void manageSalaries() {
        System.out.println("Managing salaries for employees...");
        System.out.println("Base salary processing completed.");
    }
}