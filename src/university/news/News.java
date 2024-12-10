package university.news;

public class News {
    private String title;
    private String description;

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\n";
    }
}
