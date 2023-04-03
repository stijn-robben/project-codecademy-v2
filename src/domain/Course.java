package domain;

import java.util.List;

public class Course {

    private String id;
    private String name;
    private String subject;
    private String level;
    private String introText;
    private List<ContentItem> contentItems;

    public Course(String id, String name, String subject, String level, String introText,
            List<ContentItem> contentItems) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.level = level;
        this.introText = introText;
        this.contentItems = contentItems;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
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
