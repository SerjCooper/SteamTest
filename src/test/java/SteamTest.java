import Utils.InitConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SteamTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        InitConfig configs = new InitConfig();
        System.setProperty("webdriver." + configs.BROWSER_NAME +".driver", configs.DRIVER_PATH);
        configs.getWebDriver(configs.BROWSER_NAME);

        driver.manage().window().maximize(); //передаю веб-драйверу набор методов, для того чтобы ход теста отображался в полностью открытом окне:
        driver.manage().timeouts().implicitlyWait(configs.TIMEOUT_IMPLICITLY, SECONDS); // неявное ожидание Implicit Wait, которое задается вначале теста и будет работать при каждом вызове метода поиска элемента:
        driver.get(configs.TARGET_URL);
    }
}
