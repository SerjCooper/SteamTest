import Pages.*;
import Utils.InitConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SteamTest {

    private static WebDriver driver;
    private static InitConfig configs;

    @BeforeClass
    private static void setup() {
        configs = new InitConfig();
        System.setProperty("webdriver." + configs.BROWSER_NAME +".driver", configs.DRIVER_PATH);
        driver = configs.getWebDriver(configs.BROWSER_NAME);

        driver.manage().window().maximize(); //передаю веб-драйверу набор методов, для того чтобы ход теста отображался в полностью открытом окне:
        driver.manage().timeouts().implicitlyWait(configs.TIMEOUT_IMPLICITLY, SECONDS); // неявное ожидание Implicit Wait, которое задается вначале теста и будет работать при каждом вызове метода поиска элемента:
        driver.get(configs.TARGET_URL);// + "?l=" + configs.LANGUAGE);
    }

    @Test
    private void firstTestCase() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        if (!Locale.getDefault().getLanguage().equalsIgnoreCase(configs.LANGUAGE))          //Меняем язык на выбранный в конфиге, если системный язык и выбранный не совпадают
            homePage.changeLang(configs.LANGUAGE);
        Assert.assertTrue(homePage.getLogo_img().isDisplayed());
        homePage.popupMenu.moveToPopupMenu();
        //homePage.popupMenu.getPopupMenuItems();
        homePage.popupMenu.getPopupMenuItem(configs.LANGUAGE).click();

        GenrePage genrePage = new GenrePage(driver);
        genrePage.topSallers.tabClick();
        //genrePage.topSallers.getMaxDiscount(); пока не работает
    }



    @AfterClass
    private static void tearDown() {
        driver.close();
    }
}
