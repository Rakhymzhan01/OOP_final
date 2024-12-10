package university.academics;

public class Grade {
    private String student;
    private String course;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    public Grade(String student, String course, double firstAttestation, double secondAttestation, double finalExam) {
        this.student = student;
        this.course = course;
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFirstAttestation() {
        return firstAttestation;
    }

    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation;
    }

    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student='" + student + '\'' +
                ", course='" + course + '\'' +
                ", firstAttestation=" + firstAttestation +
                ", secondAttestation=" + secondAttestation +
                ", finalExam=" + finalExam +
                '}';
    }
}
