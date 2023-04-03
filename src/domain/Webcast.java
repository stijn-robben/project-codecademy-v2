package domain;

public class Webcast extends ContentItem {

    private String speakerName;
    private int speakerOrganization;
    private int duration;
    private String title;
    private String url;

    public Webcast(int id, String publicationDate, String status, String description, String title, String speakerName,
            int speakerOrganization, int duration, String url) {
        super(id, publicationDate, status, description, title);
        this.speakerName = speakerName;
        this.speakerOrganization = speakerOrganization;
        this.duration = duration;
        this.url = url;
    }

}