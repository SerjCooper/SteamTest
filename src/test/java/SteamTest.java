import Pages.*;
import Utils.FileDownloader;
import Utils.InitConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SteamTest {

    private static WebDriver driver;
    private static InitConfig configs;

    private static String steamSetupFile = "SteamSetup.exe";

    @BeforeClass
    private static void setup() {
        configs = new InitConfig();
        System.setProperty("webdriver." + configs.BROWSER_NAME +".driver", configs.DRIVER_PATH);
        driver = configs.getWebDriver(configs.BROWSER_NAME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configs.TIMEOUT_IMPLICITLY, SECONDS);
        driver.get(configs.TARGET_URL);
    }

    @Test
    private void firstTestCase() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.getLogo_img().isDisplayed());
        homePage.popupMenu.moveToPopupMenu();
        homePage.popupMenu.getPopupMenuItem(configs.LANGUAGE).click();

        GenrePage genrePage = new GenrePage(driver);
        genrePage.topSellers.tabClick();
        int md = genrePage.topSellers.getMaxDiscount();
        double fp = genrePage.topSellers.getFinalPrice();
        genrePage.topSellers.maxDiscountGameClick();

        if(driver.getCurrentUrl().contains("agecheck")) {                               //Если попадаем на страницу с проверкой возраста, то указываем валидный
            AgeCheckPage ageCheckPage = new AgeCheckPage(driver);
            ageCheckPage.selectValidAge();
        }
        if(driver.getCurrentUrl().contains("bundle")) {
            BundlePage bundlePage = new BundlePage(driver);
            Assert.assertEquals(bundlePage.getFinalPrice(), fp);
            Assert.assertEquals(bundlePage.getDiscount(), md);
        }else if(driver.getCurrentUrl().contains("app")) {
            GamePage gamePage = new GamePage(driver);
            Assert.assertEquals(gamePage.getFinalPrice(), fp);
            Assert.assertEquals(gamePage.getDiscount(), md);
        }
        homePage.downloadSteam();
        try {
            new FileDownloader().waitForFileDownload(configs.TIMEOUT_IMPLICITLY, steamSetupFile, configs.DOWNLOAD_PATH, driver);
            File f = new File(configs.DOWNLOAD_PATH + "\\" + steamSetupFile);
            Assert.assertTrue(f.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeLangTest() {
        HomePage homePage = new HomePage(driver);
        homePage.changeLang(configs.LANGUAGE);
    }

    @AfterClass
    private static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(configs.DOWNLOAD_PATH + "\\" + steamSetupFile)); //если такой файл есть - удаляем
        driver.close();
    }
}
