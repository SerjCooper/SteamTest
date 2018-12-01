package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AgeCheckPage {

    private static WebDriver driver;

    private static By ageDayXpath = By.xpath("//*[@id=\"ageDay\"]");
    private static By ageMonthXpath = By.xpath("//*[@id=\"ageMonth\"]");
    private static By ageYearXpath = By.xpath("//*[@id=\"ageYear\"]");

    private static By openPageButton = By.xpath("//*[@id=\"app_agegate\"]/div[1]/div[3]/a[1]/span");

    private String ageDay = "1";
    private String ageMonth = "January";
    private String ageYear = "1990";

    public AgeCheckPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectValidAge() {
        Select select = new Select(driver.findElement(ageYearXpath));
        select.selectByValue(ageYear);
        openPageButtonClick();
    }

    private void openPageButtonClick() {
        driver.findElement(openPageButton).click();
    }

}
