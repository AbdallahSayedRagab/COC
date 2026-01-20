package Utilities;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;

public class JsonUtil {

    private static final String FILE_PATH =
            System.getProperty("user.dir") +
                    "/src/test/TestData/Data.json";

    public static void writeValue(String key, String value) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json;

            File file = new File(FILE_PATH);
            if (file.exists()) {
                json = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            } else {
                json = new JsonObject();
            }

            json.addProperty(key, value);

            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                gson.toJson(json, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readValue(String key) {
        try {
            JsonObject json = JsonParser.parseReader(
                    new FileReader(FILE_PATH)
            ).getAsJsonObject();

            return json.get(key).getAsString();

        } catch (Exception e) {
            return null;
        }
    }
}