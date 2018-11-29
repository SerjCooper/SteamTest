package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class TopSallers {

    private static WebDriver driver;

    private static By discount = By.className("discount_pct");
    private static By topSallersTab = By.xpath("//*[@id=\"tab_select_TopSellers\"]");
   // private static By maxDiscountGame = By.cssSelector("#NewReleasesRows > a:nth-child(3)")

    public TopSallers(WebDriver driver) {
        this.driver = driver;
    }

    public int getMaxDiscount() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> elementDiscounts = driver.findElements(discount);
        List<String> discounts_str = null;
        List<Integer> discounts = null;

        for(WebElement d : elementDiscounts) {
            discounts_str.add(d.getText().replace("-%", ""));
        }
        System.out.println(discounts_str);
        for(String s : discounts_str) {
            discounts.add(Integer.valueOf(s));
        }
        System.out.println(discounts);
        System.out.println(Collections.max(discounts));
        return Collections.max(discounts);
    }

    public void maxDiscountGameClick() {

    }

    public void tabClick() {
        driver.findElement(topSallersTab).click();
    }
}
