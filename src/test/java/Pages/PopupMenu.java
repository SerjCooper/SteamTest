package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class PopupMenu {

    private static WebDriver driver;

    private static By popup_menu_item = By.className("popup_menu_item");
    private static By genre_tab = By.id("genre_tab");
    private By genre_Action_ru = By.linkText("Экшен");
    private By genre_Action = By.linkText("Action");

    public PopupMenu(WebDriver driver) {
        this.driver = driver;
    }

    public void moveToPopupMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(genre_tab)).perform();
    }

    public List<WebElement> getPopupMenuItems() {
        List<WebElement> items = driver.findElements(popup_menu_item);
        return items;
    }

    public WebElement getPopupMenuItem(String lang) {
        if(lang.equalsIgnoreCase("ru"))
            return driver.findElement(genre_Action_ru);
        else
            return driver.findElement(genre_Action);
    }
}
//*[@id="genre_flyout"]/div/a[16]
