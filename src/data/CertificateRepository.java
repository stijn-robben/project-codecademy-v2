package data;

import domain.Certificate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateRepository {
    private final Connection connection;

    public CertificateRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCertificate(Certificate certificate) throws SQLException {
        String query = "INSERT INTO Certificate (CourseID, Grade, NameEmployee, StudentEmail, RegistrationDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, certificate.getCourseId());
        // statement.setInt(2, certificate.getCertificateID());
        statement.setString(2, certificate.getGrade());
        statement.setString(3, certificate.getApprovingEmployeeName());
        statement.setString(4, certificate.getStudentEmail());
        statement.setString(5, certificate.getRegistrationDate());
        statement.executeUpdate();
        statement.close();
    }

    // needs work
    public List<Certificate> getCertificates() throws SQLException {
        List<Certificate> certificates = new ArrayList<>();
        String query = "SELECT * FROM Certificate";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String registrationDate = resultSet.getString("RegistrationDate");
                String studentEmail = resultSet.getString("StudentEmail");
                int courseId = resultSet.getInt("CourseID");
                String nameEmployee = resultSet.getString("NameEmployee");
                String grade = resultSet.getString("Grade");
                certificates.add(
                        new Certificate(courseId, grade, nameEmployee, studentEmail, registrationDate));

            }
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
        return certificates;
    }

    public boolean deleteCertificate(int certificateID) throws SQLException {
        String query = "DELETE FROM Certificate WHERE CertificateID = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setInt(1, certificateID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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

    public boolean updateCertificate(Certificate certificate, int certificateID) throws SQLException {
        String query = "UPDATE Certificate SET CourseID=?, Grade=?, NameEmployee=?, StudentEmail=?, RegistrationDate=? WHERE CertificateID=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setInt(1, certificate.getCourseId());
            statement.setString(2, certificate.getGrade());
            statement.setString(3, certificate.getApprovingEmployeeName());
            statement.setString(4, certificate.getStudentEmail());
            statement.setString(5, certificate.getRegistrationDate());
            statement.setInt(6, certificateID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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
