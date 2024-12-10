package university.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final Gson gson = new Gson();

    // Load data from a JSON file
    public static <T> List<T> loadFromFile(String fileName, Type typeOfList) {
        File file = new File(fileName);

        // If the file does not exist, create it with an empty array
        if (!file.exists()) {
            try (Writer writer = new FileWriter(file)) {
                writer.write("[]"); // Initialize with an empty JSON array
            } catch (IOException e) {
                System.err.println("Error initializing file: " + fileName);
                e.printStackTrace();
            }
        }

        // Load the data from the file
        try (Reader reader = new FileReader(file)) {
            List<T> data = gson.fromJson(reader, typeOfList);
            return data != null ? data : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save data to a JSON file
    public static <T> void saveToFile(List<T> data, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    // Add a single entry to a JSON file
    public static <T> void addEntry(T entry, String fileName, Type typeOfList) {
        List<T> data = loadFromFile(fileName, typeOfList);
        data.add(entry);
        saveToFile(data, fileName);
    }

    // Remove an entry from a JSON file
    public static <T> void removeEntry(T entry, String fileName, Type typeOfList) {
        List<T> data = loadFromFile(fileName, typeOfList);
        data.remove(entry);
        saveToFile(data, fileName);
    }

    // Update an entry in a JSON file
    public static <T> void updateEntry(T oldEntry, T newEntry, String fileName, Type typeOfList) {
        List<T> data = loadFromFile(fileName, typeOfList);
        int index = data.indexOf(oldEntry);
        if (index != -1) {
            data.set(index, newEntry);
        }
        saveToFile(data, fileName);
    }
}
