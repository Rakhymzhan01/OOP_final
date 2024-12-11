package university.academics;

import java.util.List;

public class LectureAttendance {
    private String lectureId; // Matches "lectureId" in JSON
    private List<StudentAttendance> students; // List of student attendance

    public LectureAttendance(String lectureId, List<StudentAttendance> students) {
        this.lectureId = lectureId;
        this.students = students;
    }

    // Getters and setters
    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public List<StudentAttendance> getStudents() {
        return students;
    }

    public void setStudents(List<StudentAttendance> students) {
        this.students = students;
    }
}
