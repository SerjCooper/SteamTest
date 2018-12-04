package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GamePage {

    private static WebDriver driver;

    private static By discount = By.className("discount_pct");
    private static By finalPrice = By.className("discount_final_price");
    private String areaPurchase_div = "//*[@id=\"game_area_purchase\"]/div[%s]/div" ;
    private By areaPurchase;
    private static By noticeBox = By.className("notice_box_content");

    public GamePage(WebDriver driver) {
        this.driver = driver;
        if(driver.findElement(noticeBox).isDisplayed())                     //Данный блок сделан для обхода сообщения о покупке в текущем регионе
            areaPurchase = By.xpath(areaPurchase_div.replace("%s", "2"));
        else
            areaPurchase = By.xpath(areaPurchase_div.replace("%s", "1"));
    }

    public int getDiscount() {
        int discountProc = Integer
                .parseInt(driver
                        .findElement(areaPurchase)
                        .findElement(discount)
                        .getText()
                        .replaceAll("\\D", ""));
        return discountProc;
    }

    public double getFinalPrice() {
        return Double
                .parseDouble(driver
                        .findElement(areaPurchase)
                        .findElement(finalPrice)
                        .getText()
                        .replaceAll("[^0-9,.]+.", "")
                        .replace(",", "."));         //отрезаем от цены валюту (руб.) и возвращаем стоимость со скидкой
    }
}
