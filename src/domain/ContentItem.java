package domain;

public class ContentItem {

    private int id;
    private String publicationDate;
    private String status;
    private String description;
    private String title;

    public ContentItem(int id, String publicationDate, String status, String description, String title) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.status = status;
        this.description = description;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    } 
}
