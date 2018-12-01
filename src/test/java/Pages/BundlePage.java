package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BundlePage {

    private static WebDriver driver;

    private static By discount = By.className("discount_pct");
    private static By finalPrice = By.className("discount_final_price");
    private static By bundleArea = By.xpath("//*[@id=\"game_area_purchase\"]");

    public BundlePage(WebDriver driver) {
        this.driver = driver;
    }

    public static int getDiscount() {
        int discountProc = Integer
                .parseInt(driver
                .findElement(bundleArea)
                .findElement(discount)
                .getText()
                .replaceAll("\\D", ""));
        return discountProc;
    }

    public static double getFinalPrice() {
        return Double
                .parseDouble(driver
                        .findElement(bundleArea)
                        .findElement(finalPrice)
                        .getText()
                        .replaceAll("[^0-9,.]+.", "")
                        .replace(",", "."));         //отрезаем от цены валюту (руб.) и возвращаем стоимость со скидкой
    }
}
