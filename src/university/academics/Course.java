package university.academics;

import university.core.Teacher;
import java.util.List;
import university.utils.FileHandler;
import com.google.gson.reflect.TypeToken;


public class Course {
    private String id;
    private String name;
    private int credits;
    private Teacher teacher;
    private List<String> studentsEnrolled; // Store student usernames

    public Course(String id, String name, int credits, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<String> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    private List<String> getEnrolledStudents(String courseId) {
        List<Course> courses = FileHandler.loadFromFile("src/university/data/courses.json",
                new TypeToken<List<Course>>() {}.getType());

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course.getStudentsEnrolled();
            }
        }

        return List.of(); // Return an empty list if no course matches
    }

}
