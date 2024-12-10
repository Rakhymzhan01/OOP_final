package university.core;

import university.news.News;
import university.utils.FileHandler;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Manager extends User {
    private static final String NEWS_FILE = "src/university/data/news.json";

    public Manager(String username, String password, String firstName, String lastName, String department) {
        super(username, password, firstName, lastName);
    }

    // Manager menu
    public void viewMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nManager Menu:");
            System.out.println("1 - View News");
            System.out.println("2 - Add News");
            System.out.println("3 - Delete News");
            System.out.println("4 - Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewNews();   // View all news
                case 2 -> addNews();    // Add new news
                case 3 -> deleteNews(); // Delete news
                case 4 -> {
                    System.out.println("Logging out...");
                    return; // Exit manager menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display all news
    public void viewNews() {
        List<News> newsList = loadNews();
        if (newsList.isEmpty()) {
            System.out.println("No news available.");
        } else {
            System.out.println("\nLatest News:");
            for (int i = 0; i < newsList.size(); i++) {
                System.out.println((i + 1) + ". " + newsList.get(i));
            }
        }
    }

    // Add a new news item
    public void addNews() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the title of the news: ");
        String title = scanner.nextLine();

        System.out.print("Enter the description of the news: ");
        String description = scanner.nextLine();

        News news = new News(title, description);
        List<News> newsList = loadNews();
        newsList.add(news);
        FileHandler.saveToFile(newsList, NEWS_FILE);

        System.out.println("News added successfully!");
    }

    // Delete a news item
    public void deleteNews() {
        List<News> newsList = loadNews();
        if (newsList.isEmpty()) {
            System.out.println("No news available to delete.");
            return;
        }

        viewNews();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of the news to delete: ");
        int newsNumber = scanner.nextInt();

        if (newsNumber < 1 || newsNumber > newsList.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            newsList.remove(newsNumber - 1);
            FileHandler.saveToFile(newsList, NEWS_FILE);
            System.out.println("News deleted successfully!");
        }
    }

    // Load news from JSON
    private List<News> loadNews() {
        Type newsListType = new TypeToken<List<News>>() {}.getType();
        return FileHandler.loadFromFile(NEWS_FILE, newsListType);
    }
}