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
            System.out.println("Student works");
            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DatabaseConnection connection = new DatabaseConnection();
            CourseHandler courseHandler = new CourseHandler(connection.getConnection());

            // Test adding a course
            Course course = new Course("14", "Intro to Architecture", "Mechanical Engineering", "Beginner",
                    "This course provides an introduction to architecture.");
            courseHandler.addCourse(course);
            System.out.println("Added course: " + course);

            // Test getting all courses
            System.out.println("All courses:");
            courseHandler.getCourses().forEach(System.out::println);

            // Test updating a course
            course.setName("Advanced architecture");
            course.setSubject("Architecture");
            course.setIntroText("This course provides advanced architecture techniques.");
            courseHandler.updateCourse(course);
            System.out.println("Updated course: " + course);

            // Test deleting a course
            courseHandler.deleteCourse(course.getName());
            System.out.println("Deleted course: " + course);
            System.out.println("Course works");
            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
