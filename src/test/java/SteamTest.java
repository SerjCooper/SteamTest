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

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configs.TIMEOUT_IMPLICITLY, SECONDS);
        driver.get(configs.TARGET_URL);// + "?l=" + configs.LANGUAGE);
    }

    @Test
    private void firstTestCase() {
        HomePage homePage = new HomePage(driver);
        if (!Locale.getDefault().getLanguage().equalsIgnoreCase(configs.LANGUAGE))          //Меняем язык на выбранный в конфиге, если системный язык и выбранный не совпадают
            homePage.changeLang(configs.LANGUAGE);
        Assert.assertTrue(homePage.getLogo_img().isDisplayed());
        homePage.popupMenu.moveToPopupMenu();
        homePage.popupMenu.getPopupMenuItem(configs.LANGUAGE).click();

        GenrePage genrePage = new GenrePage(driver);
        genrePage.topSallers.tabClick();
        genrePage.topSallers.maxDiscountGameClick();

      //  double fp = genrePage.topSallers.getFinalPrice(); пока не работает
      //  int md = genrePage.topSallers.getMaxDiscount();пока не работает

        if(driver.getCurrentUrl().contains("agecheck")) {                               //Если попадаем на страницу с проверкой возраста, то указываем валидный
            AgeCheckPage ageCheckPage = new AgeCheckPage(driver);
            ageCheckPage.selectValidAge();
        }
        if(driver.getCurrentUrl().contains("bundle")) {
            BundlePage bundlePage = new BundlePage(driver);
          //  Assert.assertEquals(bundlePage.getFinalPrice(), fp);пока не работает
         //   Assert.assertEquals(bundlePage.getDiscount(), md);пока не работает
        }else if(driver.getCurrentUrl().contains("app")) {
            GamePage gamePage = new GamePage(driver);
         //   Assert.assertEquals(gamePage.getFinalPrice(), fp);пока не работает
          //  Assert.assertEquals(gamePage.getDiscount(), md);пока не работает
        }

    }



    @AfterClass
    private static void tearDown() {
        driver.close();
    }
}
