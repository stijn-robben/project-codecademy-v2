package domain;

public class Certificate {

    private int courseId;
    private String grade;
    private String approvingEmployeeName;
    private String studentEmail;
    private String registrationDate;

    public Certificate(int courseId, String grade, String approvingEmployeeName,
            String studentEmail, String registrationDate) {
        this.courseId = courseId;
        this.grade = grade;
        this.approvingEmployeeName = approvingEmployeeName;
        this.studentEmail = studentEmail;
        this.registrationDate = registrationDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getApprovingEmployeeName() {
        return approvingEmployeeName;
    }

    public void setApprovingEmployeeName(String approvingEmployeeName) {
        this.approvingEmployeeName = approvingEmployeeName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Grade: " + grade
                + ", " + approvingEmployeeName + ", " + studentEmail
                + ", " + registrationDate + "";
    }

}