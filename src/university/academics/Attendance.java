package university.academics;

public class Attendance {
    private String course;
    private String student;
    private String status; // 'A' for Absent, 'P' for Present

    public Attendance(String course, String student, String status) {
        this.course = course;
        this.student = student;
        this.status = status;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

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

    @Override
    public String toString() {
        return "Student: " + student + ", Course: " + course + ", Status: " + status;
    }
}
