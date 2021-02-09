package com.gittigidiyor.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;


public class GittigidiyorTest {
    public static WebDriver driver;
    public static WebElement element = null;
    public static String mainUrl = "https://www.gittigidiyor.com";
    public static String mainTitle = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
    public static WebDriverWait wait = null;

    public static WebDriverWait initWait(WebDriver driver){
        wait = new WebDriverWait(driver, 10);
        return wait;
    }

    public static  WebElement LoginPopup(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@data-cy='header-user-menu']"));
        return element;
    }

    public static WebElement LoginPageButton(WebDriver driver){
        initWait(driver);
        element =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-cy='header-login-button']")));
        return element;
    }

    public static WebElement UserName(WebDriver driver){
        element = driver.findElement(By.id("L-UserNameField"));
        return element;
    }

    public static WebElement Password(WebDriver driver){
        element = driver.findElement(By.id("L-PasswordField"));
        return element;
    }

    public static WebElement LoginButton(WebDriver driver){
        element = driver.findElement(By.id("gg-login-enter"));
        return element;
    }

    public static WebElement SearchInput(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@data-cy='header-search-input']"));
        return element;
    }

    public static WebElement SearchButton(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@data-cy='search-find-button']"));
        return element;
    }

    public static WebElement CookieCloseButton(WebDriver driver){
        element = driver.findElement(By.className("policy-alert-close"));
        return element;
    }

    public static WebElement WisCloseButton(WebDriver driver){
        try {
            element = driver.findElement(By.className("wis-clsbtn-72716"));
            element.click();
        } catch (Exception ex) {
        }
        return element;
    }

    public static WebElement GotoPage(WebDriver driver, int pageNumber){
        String PageLocate = String.format("//div[contains(@class, 'pager')]/ul/li[%d]/a", pageNumber);
        element = driver.findElement(By.xpath(PageLocate));
        return element;
    }

    public static WebElement GotoItemPage(WebDriver driver, int itemNumber, Boolean isRandom){
        String locator;
        if (isRandom) {
            Random rand = new Random();
            // create locator with max item number
            locator = String.format("//li[contains(@class, 'srp-item-list')][%d]/a", rand.nextInt(48));
        } else {
            // create locator with given item number
            locator = String.format("//li[contains(@class, 'srp-item-list')][%d]/a", itemNumber);
        }

        element = driver.findElement(By.xpath(locator));
        return element;
    }

    public static WebElement HighPriceInfo(WebDriver driver){
        element = driver.findElement(By.id("sp-price-highPrice"));
        return element;
    }

    public static WebElement LowPriceInfo(WebDriver driver){
        element = driver.findElement(By.id("sp-price-lowPrice"));
        return element;
    }

    public static WebElement AddToBasket(WebDriver driver){
        element = driver.findElement(By.id("add-to-basket"));
        return element;
    }

    public static WebElement Basket(WebDriver driver){
        element = driver.findElement(By.className("dIB"));
        return element;
    }

    public static WebElement BasketPriceInfo(WebDriver driver){
        element = driver.findElement(By.xpath("//div[contains(@class, 'total-price')]/strong[contains(@class, 'real-discounted-price')] | //div[contains(@class, 'total-price')]/strong"));
        return element;
    }

    public static WebElement IncreaseItemCount(WebDriver driver){
        element = driver.findElement(By.className("plus"));
        return element;
    }

    public static WebElement ItemAmount(WebDriver driver){
        element = driver.findElement(By.className("amount"));
        return element;
    }

    public static WebElement RemoveItemToBasket(WebDriver driver){
        element = driver.findElement(By.className("btn-delete"));
        return element;
    }

}
