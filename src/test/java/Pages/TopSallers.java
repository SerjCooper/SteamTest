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
    private static By sellersRows = By.xpath("//*[@id=\"TopSellersRows\"]");

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

       /* elements = driver.findElement(sellersRows).findElements(By.className("discount_prices"));
        List<Double> el_double = new ArrayList<>();
        for(WebElement d : elements) {
            el_double
                    .add(Double
                            .parseDouble(d
                                    .findElement(By
                                            .className("discount_final_price"))
                                    .getText()
                                    .replaceAll("[^0-9,.]+.", "")
                                    .replace(",", ".")) %
                            Double
                                    .parseDouble(d
                                            .findElement(By
                                                    .className("discount_original_price")) //не у всех есть оригинальная цена
                                            .getText().replaceAll("[^0-9,.]+.", "")
                                            .replace(",", ".")));
        }
        maxDiscSum = Collections.max(el_double);*/
        return maxDiscProc;
    }

    public void maxDiscountGameClick() {
        //getMaxDiscount();
        driver.findElement(sellersRows)
                .findElement(By.partialLinkText("-" + maxDiscProc + "%"))
                .click();
    }

    public void tabClick() {
        driver.findElement(topSellersTab).click();
    }

    public double getFinalPrice() {
        List<WebElement> elements = driver
                //.findElement(sellersRows)
                .findElements(By
                        .xpath("//body[contains(@class,'v6 tag_page game_bg contenthub_page responsive_page')]/div[contains(@class,'responsive_page_frame with_header')]/div[contains(@class,'responsive_page_content')]/div[contains(@class,'responsive_page_template_content')]/div[contains(@class,'contenthub_page_background')]/div[contains(@class,'page_contenthub_content')]/div[contains(@class,'page_content_ctn')]/div[contains(@class,'page_content')]/div[contains(@class,'leftcol sub')]/div[contains(@class,'tabarea')]/div[contains(@class,'tab_content_ctn sub')]/div[@id='tab_content_TopSellers']/div[@id='TopSellersTable']/div[@id='TopSellersRows']/a/div[@class='discount_block tab_item_discount']"));
        WebElement divWithMaxDisc = null;
        try {
            for (WebElement d : elements) {
                // System.out.println(d.getText());
                divWithMaxDisc = d.findElement(finalPrice);
                if (d.findElement(By.partialLinkText("-" + maxDiscProc + "%")).isDisplayed()) {
                    divWithMaxDisc = d.findElement(By.className("discount_final_price"));
                    break;
                }
            }
        }catch (org.openqa.selenium.NoSuchElementException e) {

        }
        return Double
                .parseDouble(divWithMaxDisc
                   //     .findElement(finalPrice)
                        .getText()
                        .replaceAll("[^0-9,.]+.", "")
                        .replace(",", "."));  //отрезаем от цены валюту (руб.) и возвращаем стоимость со скидкой
    }

    public double getMaxDiscSum() {
        return maxDiscSum;
    }
}