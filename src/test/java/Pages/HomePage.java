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
    private static By downloadSteamLink = By.className("header_installsteam_btn_content");
    private static By installSteamButton = By.id("about_install_steam_link");

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

    public void downloadSteam() {
        driver.findElement(downloadSteamLink).click();
        driver.findElement(installSteamButton).click();
    }

}
