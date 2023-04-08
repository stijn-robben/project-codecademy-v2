package logic;

import data.CourseRepository;
import domain.Course;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseHandler {

    private final CourseRepository courseRepository;

    public CourseHandler(Connection connection) {
        this.courseRepository = new CourseRepository(connection);
    }

    public void addCourse(Course course) throws SQLException {
        courseRepository.addCourse(course);
    }

    public List<Course> getCourses() throws SQLException {
        return courseRepository.getCourse();
    }

    public void deleteCourse(String courseName) throws SQLException {
        courseRepository.deleteCourse(courseName);
    }

    public void updateCourse(Course course) throws SQLException {
        courseRepository.updateCourse(course);
    }

}