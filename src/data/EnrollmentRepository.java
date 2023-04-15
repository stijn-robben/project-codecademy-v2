package data;

import domain.Enrollment;
import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    public boolean addEnrollment(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO Registration (RegistrationDate, StudentEmail, CourseID) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, enrollment.getRegistrationDate());
            statement.setString(2, enrollment.getStudentEmail());
            statement.setInt(3, enrollment.getCourseId());

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    public List<Enrollment> getEnrollments() throws SQLException {

        String query = "SELECT * FROM Registration";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            List<Enrollment> enrollments = new ArrayList<>();
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String registrationDate = resultSet.getString("RegistrationDate");
                String studentEmail = resultSet.getString("StudentEmail");
                int courseId = resultSet.getInt("CourseId");

                enrollments.add(new Enrollment(registrationDate, studentEmail, courseId));
            }
            return enrollments;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public boolean deleteEnrollment(String email, int courseId) throws SQLException {
        String query = "DELETE FROM Registration WHERE StudentEmail = ? AND CourseId = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, courseId);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

    }

    public boolean updateEnrollment(Enrollment enrollment) throws SQLException {
        String query = "UPDATE Registration SET RegistrationDate=? WHERE CourseID=? AND StudentEmail=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, enrollment.getRegistrationDate());
            statement.setInt(2, enrollment.getCourseId());
            statement.setString(3, enrollment.getStudentEmail());
            System.out.println(statement);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

    }

}
