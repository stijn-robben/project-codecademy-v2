package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import domain.Enrollment;

public class EnrollmentRepository {

    private StudentRepository studentRepository = new StudentRepository();
    private CourseRepository courseRepository = new CourseRepository();


    // create enrollment
    public boolean createEnrollment(String course, String mail) {
        String query = "INSERT INTO Enrollment VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        String date = LocalDate.now().toString();

        Enrollment enrollment = new Enrollment(
            studentRepository.readStudent(mail),
            courseRepository.readCourse(course),
            date
        );

        try {
            //insert enrollment record
            conn = DatabaseConnection.getConnection();            
            statement = conn.prepareStatement(query);
            statement.setString(1, enrollment.getEnrollmentDate());
            statement.setString(2, mail);
            statement.setString(3, course);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
    }

    // read enrollments
   
     

    
}