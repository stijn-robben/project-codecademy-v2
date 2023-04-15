package domain;

public class Certificate {

    private String certificateID;
    private Double grade;
    private String approvingEmployeeName;
    private Enrollment enrollment;

    public Certificate(double grade, String approvingEmployeeName, Enrollment enrollment) {
        //this.certificateID = certificateID;
        this.grade = grade;
        this.approvingEmployeeName = approvingEmployeeName;
        this.enrollment = enrollment;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public String getCourseID() {
        return enrollment.getCourse().getId();
    }

    public double getGrade() {
        return grade;
    }

    public String getGradeInString() {
        return grade.toString();
    }

    public String getApprovingEmployeeName() {
        return approvingEmployeeName;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public String getStudentEmail() {
        return enrollment.getStudent().getEmail();
    }

    public String getRegistrationDate() {
        return enrollment.getEnrollmentDate();
    }

    

    
    
}