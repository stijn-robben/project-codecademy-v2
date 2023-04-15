package data;

import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    

    public boolean addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Student (StudentEmail, Name, DateOfBirth, Gender, Address, City, Country, ZipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, student.getEmail());
            statement.setString(2, student.getName());
            statement.setString(3, student.getBirthDate());
            statement.setString(4, student.getGender());
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getCity());
            statement.setString(7, student.getCountry());
            statement.setString(8, student.getZipCode());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally{
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    public List<Student> getStudents() throws SQLException {
        
        String query = "SELECT * FROM Student";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            List<Student> students = new ArrayList<>();
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String email = resultSet.getString("StudentEmail");
                String name = resultSet.getString("Name");
                String birthDate = resultSet.getString("DateOfBirth");
                String gender = resultSet.getString("Gender");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String zipCode = resultSet.getString("ZipCode");
                students.add(new Student(email, name, birthDate, gender, address, city, country, zipCode));
        }
        return students; 
    } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
        try { if (statement != null) statement.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
    return null;
}

    public boolean deleteStudent(String email) throws SQLException {
        String query = "DELETE FROM Student WHERE StudentEmail = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
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

    public boolean updateStudent(Student student) throws SQLException {
        String query = "UPDATE Student SET Name=?, DateOfBirth=?, Gender=?, Address=?, City=?, Country=?, ZipCode=? WHERE StudentEmail=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getBirthDate());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getCity());
            statement.setString(6, student.getCountry());
            statement.setString(7, student.getZipCode());
            statement.setString(8, student.getEmail());
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

    public Student readStudent(String email){
        String query = "SELECT * FROM Student WHERE StudentEmail = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();


            // Check if there is a result in the set
            if(resultSet.next()){
                return new Student(
                        resultSet.getString("StudentEmail"),
                        resultSet.getString("Name"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Country"),
                        resultSet.getString("ZipCode")    
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        // Return null on error
        return null;
    }
}
