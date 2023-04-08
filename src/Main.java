import java.sql.SQLException;

import data.*;
import logic.*;
import domain.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            StudentRepository studentRepository = new StudentRepository(connection.getConnection());
            StudentHandler studentHandler = new StudentHandler(studentRepository);

            // Test adding a student
            Student student = new Student("student15@example.com", "Bob Wood", "2000-05-10", "M", "5 Oak St",
                    "New York", "USA", "1234 AB");
            studentHandler.addStudent(student);
            System.out.println("Added student: " + student);

            // Test getting all students
            System.out.println("All students:");
            studentHandler.getStudents().forEach(System.out::println);

            // Test updating a student
            student.setName("Jane Doe");
            student.setAddress("456 Elm St");
            student.setCity("San Francisco");
            student.setCountry("USA");
            studentHandler.updateStudent(student);
            System.out.println("Updated student: " + student);

            // Test deleting a student
            studentHandler.deleteStudent(student.getEmail());
            System.out.println("Deleted student: " + student);

            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
