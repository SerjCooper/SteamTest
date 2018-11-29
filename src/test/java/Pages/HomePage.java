package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    public PopupMenu popupMenu;

    private static By logoImg = By.className("logo");
    private static By langMenu = By.xpath("//*[@id=\"language_pulldown\"]");
    private static By langRu = By.partialLinkText("Русский");
    private static By langEn = By.partialLinkText("English");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.popupMenu = getPopupMenu();
    }

    public void changeLang(String lang) {
        driver.findElement(langMenu).click();
        if(lang.equalsIgnoreCase("ru") || lang.equalsIgnoreCase("russian"))
            driver.findElement(langRu).click();
        else
            driver.findElement(langEn).click();
    }

    private PopupMenu getPopupMenu() {
        return new PopupMenu(driver);
    }

    public WebElement getLogo_img() {
        return driver.findElement(logoImg);
    }

}
