package Domain;

import java.util.List;

public class Enrollment {

    private Student student;
    private Course course;
    private String enrollmentDate;
    private String level;
    private String introText;
    private List<ContentItem> contentItems;

    public Enrollment(Student student, Course course, String enrollmentDate, String level, String introText,
            List<ContentItem> contentItems) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.level = level;
        this.introText = introText;
        this.contentItems = contentItems;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getLevel() {
        return level;
    }

    public String getIntroText() {
        return introText;
    }

    public List<ContentItem> getContentItems() {
        return contentItems;
    }

    
    


}