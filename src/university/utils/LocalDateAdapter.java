package university.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    // Serialize LocalDate to JSON
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.toString()); // Converts LocalDate to an ISO-8601 string (e.g., "2024-01-15")
    }

    // Deserialize JSON to LocalDate
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString()); // Parses ISO-8601 string back to LocalDate
    }
}
