package university.news;

public class News {
    private int id; // Unique ID for each news item
    private String title;
    private String description;
    private String date; // Date as a String

    public News(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date; // User-provided date
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; // Allows ID reassignment
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Date: " + date + "\n";
    }
}