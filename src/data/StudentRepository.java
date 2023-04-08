package data;

import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Student (StudentEmail, Name, DateOfBirth, Gender, Address, City, Country, ZipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, student.getEmail());
        statement.setString(2, student.getName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getGender());
        statement.setString(5, student.getAddress());
        statement.setString(6, student.getCity());
        statement.setString(7, student.getCountry());
        statement.setString(8, student.getZipCode());
        statement.executeUpdate();
        statement.close();
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
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
        resultSet.close();
        statement.close();
        return students;
    }

    public void deleteStudent(String email) throws SQLException {
        String query = "DELETE FROM Student WHERE StudentEmail = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
        statement.close();
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE Student SET Name=?, DateOfBirth=?, Gender=?, Address=?, City=?, Country=?, ZipCode=? WHERE StudentEmail=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, student.getName());
        statement.setString(2, student.getBirthDate());
        statement.setString(3, student.getGender());
        statement.setString(4, student.getAddress());
        statement.setString(5, student.getCity());
        statement.setString(6, student.getCountry());
        statement.setString(7, student.getZipCode());
        statement.setString(8, student.getEmail());
        statement.executeUpdate();
        statement.close();
    }
}
