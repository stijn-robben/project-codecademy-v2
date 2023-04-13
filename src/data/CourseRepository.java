package data;

import domain.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO Course (CourseID, CourseName, Subject, IntroductionText, Level) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getId());
        statement.setString(2, course.getName());
        statement.setString(3, course.getSubject());
        statement.setString(4, course.getIntroText());
        statement.setString(5, course.getLevel());
        statement.executeUpdate();
        statement.close();
    }

    public List<Course> getCourse() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String id = resultSet.getString("Id");
            String name = resultSet.getString("CourseName");
            String subject = resultSet.getString("Subject");
            String level = resultSet.getString("Level");
            String introText = resultSet.getString("IntroductionText");
            courses.add(new Course(id, name, subject, level, introText));
        }
        resultSet.close();
        statement.close();
        return courses;
    }

    public void deleteCourse(String courseName) throws SQLException {
        String query = "DELETE FROM Course WHERE CourseName = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseName);
        statement.executeUpdate();
        statement.close();
    }

    public void updateCourse(Course course) throws SQLException {
        String query = "UPDATE Course SET CourseName=?, Subject=?, IntroductionText=?, Level=? WHERE Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getName());
        statement.setString(2, course.getSubject());
        statement.setString(3, course.getIntroText());
        statement.setString(4, course.getLevel());
        statement.setString(5, course.getId());
        statement.executeUpdate();
        statement.close();
    }
}
