package data;

import domain.Certificate;
import domain.Enrollment;
import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateRepository {
    private final Connection connection;
    private EnrollmentRepository enrollmentRepository;

    public CertificateRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCertificate(Certificate certificate) throws SQLException {
        String query = "INSERT INTO Certificate (CourseID, CertificateID, Grade, NameEmployee, StudentEmail, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, certificate.getCourseID());
        statement.setString(2, certificate.getCertificateID());
        statement.setString(3, certificate.getGradeInString());
        statement.setString(4, certificate.getApprovingEmployeeName());
        statement.setString(5, certificate.getStudentEmail());
        statement.setString(6, certificate.getRegistrationDate());
        statement.executeUpdate();
        statement.close();
    }

    // needs work
    public List<Certificate> getCertificates() throws SQLException {
        List<Certificate> certificates = new ArrayList<>();
        String query = "SELECT * FROM Certificate";
        Enrollment enrollment;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            // while (resultSet.next()) {
            //     certificates.add(new Certificate(
            //         double grade = resultSet.getDouble("Grade");
            //         String nameEmployee = resultSet.getString("NameEmployee");
            //         Enrollment enrollment2 = enrollment.get
                   
            //         // String studentEmail = resultSet.getString("StudentEmail");
            //         // String registrationDate = resultSet.getString("RegistrationDate");
            //     ));
            // }
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



        // Statement statement = connection.createStatement();
        // ResultSet resultSet = statement.executeQuery(query);
        // while (resultSet.next()) {


        //     // add enrollment

        //     // certificates.add(new Certificate(certificateID, grade, nameEmployee, ));
        // }
        // resultSet.close();
        // statement.close();
        // return certificates;
    }

    public boolean deleteCertificate(String email) throws SQLException {
        String query = "DELETE FROM Certificate WHERE StudentEmail = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
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

    public boolean updateCertificate(Certificate certificate) throws SQLException {
        String query = "UPDATE Certificate SET CourseID=?, Grade=?, NameEmployee=?, StudentEmail=?, RegistrationDate=? WHERE CertificateID=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, certificate.getCourseID());
            statement.setString(2, certificate.getGradeInString());
            statement.setString(3, certificate.getApprovingEmployeeName());
            statement.setString(4, certificate.getStudentEmail());
            statement.setString(5, certificate.getRegistrationDate());
            statement.setString(6, certificate.getCertificateID());
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
