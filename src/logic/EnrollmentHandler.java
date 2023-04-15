package logic;

import java.sql.*;
import java.util.List;
import domain.Enrollment;
import data.EnrollmentRepository;

public class EnrollmentHandler {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentHandler(Connection connection) {
        this.enrollmentRepository = new EnrollmentRepository();
    }

    public void addEnrollment(Enrollment enrollment) throws SQLException {
        enrollmentRepository.addEnrollment(enrollment);
    }

    public List<Enrollment> getEnrollments() throws SQLException {
        return enrollmentRepository.getEnrollments();
    }

    public void deleteEnrollment(String email, int courseId) throws SQLException {
        enrollmentRepository.deleteEnrollment(email, courseId);
    }

    public void updateEnrollment(Enrollment enrollment) throws SQLException {
        enrollmentRepository.updateEnrollment(enrollment);
    }
}
