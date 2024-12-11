package university.academics;

public class StudentAttendance {
    private String student; // Username
    private String status; // "Present" or "Absent"

    public StudentAttendance(String student, String status) {
        this.student = student;
        this.status = status;
    }

    // Getters and setters
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
