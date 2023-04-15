package data;

import domain.Course;
import domain.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.nimbus.State;

public class CourseRepository {
    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addCourse(Course course) throws SQLException {
        String query = "INSERT INTO Course (CourseID, CourseName, Subject, IntroductionText, Level) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, course.getId());
            statement.setString(2, course.getName());
            statement.setString(3, course.getSubject());
            statement.setString(4, course.getIntroText());
            statement.setString(5, course.getLevel());
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

    // read
    public List<Course> getCourse() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getString("CourseName"),
                        resultSet.getString("Subject"),
                        resultSet.getString("Level"),
                        resultSet.getString("IntroductionText")));
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
        return courses;
    }

    public boolean deleteCourse(String courseName) throws SQLException {
        String query = "DELETE FROM Course WHERE CourseName = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, courseName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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
        return false;

    }

    public boolean updateCourse(Course course, String originalCourseName) throws SQLException {
        String query = "UPDATE Course SET CourseName=?, Subject=?, IntroductionText=?, Level=? WHERE CourseName=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getSubject());
            statement.setString(3, course.getIntroText());
            statement.setString(4, course.getLevel());
            statement.setString(5, originalCourseName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        return false;

    }
}
