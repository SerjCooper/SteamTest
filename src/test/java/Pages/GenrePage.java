package Pages;

import org.openqa.selenium.WebDriver;

public class GenrePage {

    private WebDriver driver;

    public TopSellers topSellers;


    public GenrePage(WebDriver driver) {
        this.driver = driver;
        this.topSellers = getTopSellers();
    }

    public TopSellers getTopSellers() {
        return new TopSellers(driver);
    }
}
