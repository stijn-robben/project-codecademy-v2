package domain;

public class Course {

    private String name;
    private String subject;
    private String level;
    private String introText;

    public Course(String name, String subject, String level, String introText) {
        this.name = name;
        this.subject = subject;
        this.level = level;
        this.introText = introText;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getLevel() {
        return this.level.toString();
    }

    public String getIntroText() {
        return introText;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    @Override
    public String toString() {
        return "" + name + ", " + subject + ", " + level + ", " + introText + "";
    }

}
