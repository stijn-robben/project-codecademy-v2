package domain;

public class Enrollment {

    private String registrationDate;
    private String studentEmail;
    private int courseId;

    public Enrollment(String registrationDate, String studentEmail, int courseId) {
        this.registrationDate = registrationDate;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "" + registrationDate + ", " + studentEmail + ", "
                + courseId;
    }

}