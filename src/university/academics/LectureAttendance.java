package university.academics;

import java.util.List;

public class LectureAttendance {
    private String lecture; // e.g., "Lecture 1", "Lecture 2"
    private List<StudentAttendance> students;

    public LectureAttendance(String lecture, List<StudentAttendance> students) {
        this.lecture = lecture;
        this.students = students;
    }

    // Getters and setters
    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public List<StudentAttendance> getStudents() {
        return students;
    }

    public void setStudents(List<StudentAttendance> students) {
        this.students = students;
    }
}
