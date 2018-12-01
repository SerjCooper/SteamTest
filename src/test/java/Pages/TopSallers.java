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
    private static By finalPrice = By.className("discount_final_price");
    private static By sellersRows = By.id("TopSellersRows");

    private int maxDiscProc = -1;
    private double maxDiscSum = -1;

    public TopSallers(WebDriver driver) {
        this.driver = driver;
    }

    public int getMaxDiscount() {
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

    public double getFinalPrice() {
        List<WebElement> elements = driver.findElements(sellersRows);
        WebElement divWithMaxDisc = null;
        for(WebElement d : elements) {
            divWithMaxDisc = d.findElement(By.className("discount_block tab_item_discount"));
            if(d.findElement(By.partialLinkText("-" + maxDiscProc + "%")).isDisplayed())
                break;
        }
        return Double
                .parseDouble(divWithMaxDisc
                        .findElement(finalPrice)
                        .getText()
                        .replaceAll("[^0-9,.]+.", "")
                        .replace(",", "."));  //отрезаем от цены валюту (руб.) и возвращаем стоимость со скидкой
    }

}
