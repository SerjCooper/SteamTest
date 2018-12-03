package Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;

public class InitConfig {

    public static String BROWSER_NAME;
    public static String TARGET_URL;
    public static String DRIVER_PATH;
    public static int TIMEOUT_IMPLICITLY;
    public static String LANGUAGE;
    public static String DOWNLOAD_PATH;

    private static String CONFIG_PATH = "./src/test/resources/config.json";
    private static String POSTFIX_DRIVER = "driver.exe";

    public InitConfig() { //метод для чтения json

        JsonParser parser = new JsonParser();

        BROWSER_NAME = null;
        TARGET_URL = null;
        DRIVER_PATH = null;
        TIMEOUT_IMPLICITLY = 10;            //по умолчанию берём 10, если не задано другое
        LANGUAGE = Locale.getDefault().getLanguage();       //по умолчанию берём язык системы
        DOWNLOAD_PATH = null;

        try {
            System.out.println("Читаем файл...");
            JsonArray jArray = (JsonArray) parser.parse(new FileReader(CONFIG_PATH));
            for (Object o : jArray) {
                JsonObject root = (JsonObject) o;
               // root.getAsJsonArray();
                BROWSER_NAME = root.get("BROWSER_NAME").getAsString();
                TARGET_URL = root.get("TARGET_URL").getAsString();
                DRIVER_PATH = root.get("DRIVER_PATH").getAsString() + BROWSER_NAME + POSTFIX_DRIVER;
                TIMEOUT_IMPLICITLY = root.get("TIMEOUT_IMPLICITLY").getAsInt();
                LANGUAGE = root.get("LANGUAGE").getAsString();
                DOWNLOAD_PATH = root.get("DOWNLOAD_PATH").getAsString();
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getWebDriver(String browserName) {
        WebDriver driver;
        if("gecko".equalsIgnoreCase(browserName)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--lang=" + LANGUAGE);
            driver = new FirefoxDriver(firefoxOptions);
        }else{
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--lang=" + LANGUAGE);
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }
}
