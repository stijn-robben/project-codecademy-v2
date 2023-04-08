package logic;

import java.sql.*;
import java.util.List;
import domain.Student;
import data.StudentRepository;

public class StudentHandler {
    private final StudentRepository studentRepository;

    public StudentHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) throws SQLException {
        studentRepository.addStudent(student);
    }

    public List<Student> getStudents() throws SQLException {
        return studentRepository.getStudents();
    }

    public void deleteStudent(String email) throws SQLException {
        studentRepository.deleteStudent(email);
    }

    public void updateStudent(Student student) throws SQLException {
        studentRepository.updateStudent(student);
    }
}
