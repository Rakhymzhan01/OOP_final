package university.academics;

public class Transcript {
    private String name;
    private String surname;
    private String studentID;
    private int yearOfStudy;

    public Transcript(String name, String surname, String studentID, int yearOfStudy) {
        this.name = name;
        this.surname = surname;
        this.studentID = studentID;
        this.yearOfStudy = yearOfStudy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String showTranscript() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Student ID: " + studentID + "\n" +
                "Year of Study: " + yearOfStudy;
    }

    public double calcGPA() {
        return 3.5; // Placeholder for GPA calculation
    }

    public void generateTranscriptPDF() {
        System.out.println("Generating transcript PDF for " + name + " " + surname);
    }
}