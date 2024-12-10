package university.grades;

public class Mark {
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
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

    public double getTotalMark() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public String getLiteralMark() {
        // Explicitly cast to integer
        int a = (int) (firstAttestation + secondAttestation + finalExam);

        if (a > 100) {
            return "A";
        } else if (a >= 95) {
            return "A";
        } else if (a >= 90) {
            return "A-";
        } else if (a >= 85) {
            return "B+";
        } else if (a >= 80) {
            return "B";
        } else if (a >= 75) {
            return "B-";
        } else if (a >= 70) {
            return "C+";
        } else if (a >= 65) {
            return "C";
        } else if (a >= 60) {
            return "C-";
        } else if (a >= 55) {
            return "D+";
        } else if (a >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
