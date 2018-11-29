package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenrePage {

    private WebDriver driver;

    public TopSallers topSallers;


    public GenrePage(WebDriver driver) {
        this.driver = driver;
        this.topSallers = getTopSallers();
    }

    public TopSallers getTopSallers() {
        return new TopSallers(driver);
    }
}
