package data;

import domain.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverviewRepository {

    public String overview1(String gender) {
        String query = "SELECT (COUNT(c.CertificateID) / (SELECT COUNT(*) FROM Registration r JOIN Student s ON r.StudentEmail = s.StudentEmail WHERE s.Gender = ?) * 100) AS 'Percentage' FROM Certificate c JOIN Student s ON c.StudentEmail = s.StudentEmail WHERE s.Gender = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, gender);
            statement.setString(2, gender);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double percentage = resultSet.getDouble("Percentage");
                return String.format("%.2f%%", percentage);
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
        return null;
    }

    public void overview2(int courseID) {

    }

    public void overview3(String email, int courseID) {

    }

    public void overview4(String email) {

    }

    public void overview5() {

    }

    public void overview6() {

    }

    public void overview7(int courseID) {

    }

    public void overview8(int courseID) {

    }
}
