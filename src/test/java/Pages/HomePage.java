package Pages;

import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    public PopupMenu popupMenu;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.popupMenu = getPopupMenu();
    }

    private PopupMenu getPopupMenu() {
        return new PopupMenu(driver);
    }

}
