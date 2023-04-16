package data;

import domain.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public boolean addCourse(Course course) throws SQLException {
        String query = "INSERT INTO Course (CourseName, Subject, IntroductionText, Level) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getSubject());
            statement.setString(3, course.getIntroText());
            statement.setString(4, course.getLevel());
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

    public List<Course> getCourse() throws SQLException {

        String query = "SELECT * FROM Course";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {

                String name = resultSet.getString("CourseName");
                String subject = resultSet.getString("Subject");
                String level = resultSet.getString("Level");
                String introText = resultSet.getString("IntroductionText");

                courses.add(
                        new Course(name, subject, level, introText));

            }
            return courses;
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

    public boolean deleteCourse(int courseID) throws SQLException {
        String query = "DELETE FROM Course WHERE CourseID = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setInt(1, courseID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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

    public boolean updateCourse(Course course, int courseID) throws SQLException {
        String query = "UPDATE Course SET CourseName=?, Subject=?, IntroductionText=?, Level=? WHERE CourseID=?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getSubject());
            statement.setString(3, course.getIntroText());
            statement.setString(4, course.getLevel());
            statement.setInt(5, courseID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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

    public Course readCourse(String courseName) {
        String query = "SELECT * FROM Course WHERE CourseName = ? ";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {

                String name = resultSet.getString("CourseName");
                String subject = resultSet.getString("Subject");
                String level = resultSet.getString("Level");
                String introText = resultSet.getString("IntroductionText");

                courses.add(
                        new Course(name, subject, level, introText));

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
}
