package domain;

import java.util.List;

public class Module extends ContentItem {

 
    private String nameContactPerson;
    private String emailContactPerson;
    private int duration;
    private List<ContentItem> order;

    public Module(int id, String publicationDate, String status, String description, String title,
            String nameContactPerson, String emailContactPerson, int duration, List<ContentItem> order) {
        super(id, publicationDate, status, description, title);
        this.nameContactPerson = nameContactPerson;
        this.emailContactPerson = emailContactPerson;
        this.duration = duration;
        this.order = order;
    }

    public String getNameContactPerson() {
        return nameContactPerson;
    }

    public String getEmailContactPerson() {
        return emailContactPerson;
    }

    public int getDuration() {
        return duration;
    }

    public List<ContentItem> getOrder() {
        return order;
    }

    
}