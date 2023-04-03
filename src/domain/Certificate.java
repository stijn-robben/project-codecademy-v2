package Domain;

public class Certificate {

    private String id;
    private double grade;
    private String approvingEmployeeName;
    private Enrollment enrollment;

    public Certificate(String id, double grade, String approvingEmployeeName, Enrollment enrollment) {
        this.id = id;
        this.grade = grade;
        this.approvingEmployeeName = approvingEmployeeName;
        this.enrollment = enrollment;
    }

    public String getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }

    public String getApprovingEmployeeName() {
        return approvingEmployeeName;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    

    
    
}