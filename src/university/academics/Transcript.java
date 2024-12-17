package university.academics;

import university.grades.Mark;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Transcript {
    private String name;
    private String surname;
    private String studentID;
    private int yearOfStudy;
    private List<Mark> marks;

    public Transcript(String name, String surname, String studentID, int yearOfStudy, List<Mark> marks) {
        this.name = name;
        this.surname = surname;
        this.studentID = studentID;
        this.yearOfStudy = yearOfStudy;
        this.marks = marks;
    }

    public String showTranscript() {
        StringBuilder transcriptBuilder = new StringBuilder();
        transcriptBuilder.append("Transcript for ").append(name).append(" ").append(surname).append("\n");
        transcriptBuilder.append("Student ID: ").append(studentID).append("\n");
        transcriptBuilder.append("Year of Study: ").append(yearOfStudy).append("\n\n");

        for (Mark mark : marks) {
            transcriptBuilder.append(mark.getCourseName())
                    .append(": Total Grade: ").append(mark.getTotalMark())
                    .append(" (").append(mark.getLiteralMark()).append(")\n");
        }

        return transcriptBuilder.toString();
    }

    public void saveTranscriptToFile(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(showTranscript());
            System.out.println("Transcript saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving transcript: " + e.getMessage());
        }
    }

    public double calcGPA() {
        if (marks == null || marks.isEmpty()) return 0.0;

        double totalGradePoints = 0.0;
        for (Mark mark : marks) {
            totalGradePoints += mark.getTotalMark(); // Simplified GPA calculation
        }

        return totalGradePoints / marks.size();
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}