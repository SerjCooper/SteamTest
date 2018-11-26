package Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class InitConfig {

    public static String BROWSER_NAME;
    public static String TARGET_URL;
    public static String DRIVER_PATH;
    public static int TIMEOUT_IMPLICITLY;

    private static String CONFIG_PATH = "./src/test/resources/config.json";

    public InitConfig() { //метод для чтения json

        JsonParser parser = new JsonParser();

        BROWSER_NAME = null;
        TARGET_URL = null;
        DRIVER_PATH = null;
        TIMEOUT_IMPLICITLY = 10;            //по умолчанию берём 10, если не задано другое

        try {
            System.out.println("Читаем файл...");
            JsonArray jArray = (JsonArray) parser.parse(new FileReader(CONFIG_PATH));
            for (Object o : jArray) {
                JsonObject root = (JsonObject) o;
                root.getAsJsonArray();
                BROWSER_NAME = root.get("BROWSER_NAME").getAsString();
                TARGET_URL = root.get("TARGET_URL").getAsString();
                DRIVER_PATH = root.get("DRIVER_PATH").getAsString() + BROWSER_NAME + "driver.exe";
                TIMEOUT_IMPLICITLY = root.get("TIMEOUT_IMPLICITLY").getAsInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
