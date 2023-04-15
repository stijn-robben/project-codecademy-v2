package data;

import domain.Certificate;
import domain.Enrollment;
import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateRepository {
    private final Connection connection;

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
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String certificateID = resultSet.getString("certificateID");
            double grade = resultSet.getDouble("Grade");
            String nameEmployee = resultSet.getString("NameEmployee");
            String studentEmail = resultSet.getString("StudentEmail");
            String registrationDate = resultSet.getString("RegistrationDate");

            // add enrollment 

           // certificates.add(new Certificate(certificateID, grade, nameEmployee,  ));
        }
        resultSet.close();
        statement.close();
        return certificates;
    }

    public void deleteCertificate(String email) throws SQLException {
        String query = "DELETE FROM Certificate WHERE StudentEmail = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
        statement.close();
    }

    public void updateCertificate(Certificate certificate) throws SQLException {
        String query = "UPDATE Certificate SET CourseID=?, Grade=?, NameEmployee=?, StudentEmail=?, RegistrationDate=? WHERE CertificateID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, certificate.getCourseID());
        statement.setString(2, certificate.getGradeInString());
        statement.setString(3, certificate.getApprovingEmployeeName());
        statement.setString(4, certificate.getStudentEmail());
        statement.setString(5, certificate.getRegistrationDate());
        statement.setString(6, certificate.getCertificateID());
        statement.executeUpdate();
        statement.close();
    }
}
