package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopSallers {

    private static WebDriver driver;

    private static By topSellersTab = By.xpath("//*[@id=\"tab_select_TopSellers\"]");
    private static By discount = By.className("discount_pct");
    private static By sellersRows = By.id("TopSellersRows");

    private int maxDiscProc = -1;
    private double maxDiscSum = -1;

    public TopSallers(WebDriver driver) {
        this.driver = driver;
    }

    private int getMaxDiscount() {
        List<WebElement> elements;
        List<Integer> el_int = new ArrayList<>();

        elements = driver.findElement(sellersRows).findElements(discount);
        for(WebElement d : elements) {
            el_int.add(Integer.parseInt(d.getText().replaceAll("\\D", "")));
        }
        maxDiscProc = (Collections.max(el_int));
        return maxDiscProc;
    }

    public void maxDiscountGameClick() {
        getMaxDiscount();
        driver.findElement(sellersRows)
                .findElement(By.partialLinkText("-" + maxDiscProc + "%"))
                .click();
    }

    public void tabClick() {
        driver.findElement(topSellersTab).click();
    }

    public void setDiscountProc(int discountProc) {
        this.maxDiscProc = discountProc;
    }

    public double getDicountSum() {
        return maxDiscSum;
    }

    public void setDicountSum(double dicountSum) {
        this.maxDiscSum = dicountSum;
    }
}
