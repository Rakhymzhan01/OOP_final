package university.academics;

import university.enums.LessonType;
import university.core.Teacher;

public class Lesson {
    private String name;
    private LessonType type;
    private Teacher teacher;

    public Lesson(String name, LessonType type, Teacher teacher) {
        this.name = name;
        this.type = type;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLessonDetails() {
        return "Lesson Name: " + name + "\n" +
                "Lesson Type: " + type + "\n" +
                "Teacher: " + (teacher != null ? teacher.getName() : "None");
    }

    public void changeLessonType(LessonType type) {
        this.type = type;
    }
}