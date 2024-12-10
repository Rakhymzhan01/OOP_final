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
            for (News news : newsList) {
                System.out.println(news);
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

        System.out.print("Enter the date of the news (format: YYYY-MM-DD or any format you prefer): ");
        String date = scanner.nextLine();

        News news = new News(title, description, date);
        List<News> newsList = loadNews();
        news.setId(newsList.size() + 1); // Assign sequential ID
        newsList.add(news);
        FileHandler.saveToFile(newsList, NEWS_FILE);

        System.out.println("News added successfully!");
    }

    // Delete a news item and reassign IDs
    public void deleteNews() {
        List<News> newsList = loadNews(); // Load existing news from the file
        if (newsList.isEmpty()) {
            System.out.println("No news available to delete.");
            return;
        }

        // Display news items
        System.out.println("\nNews List:");
        for (News news : newsList) {
            System.out.println(news);
        }

        // Ask the manager to select a news item by ID
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the news to delete: ");
        int newsId = scanner.nextInt();

        // Remove the selected news
        boolean removed = newsList.removeIf(news -> news.getId() == newsId);

        if (removed) {
            // Reassign IDs after deletion
            for (int i = 0; i < newsList.size(); i++) {
                newsList.get(i).setId(i + 1); // Reassign sequential IDs
            }
            FileHandler.saveToFile(newsList, NEWS_FILE); // Save updated news list to the file
            System.out.println("News deleted and IDs reassigned successfully!");
        } else {
            System.out.println("No news found with the given ID. Please try again.");
        }
    }

    // Load news from JSON
    private List<News> loadNews() {
        Type newsListType = new TypeToken<List<News>>() {}.getType();
        return FileHandler.loadFromFile(NEWS_FILE, newsListType);
    }
}