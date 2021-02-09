package com.gittigidiyor.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.Logger;

public class FirstAutomationTest {
    @Test
    public void swTestAcademyTitleTest() {
        //Instantiate driver object as ChromeDriver
        WebDriver driver = new ChromeDriver();
        Logger logger = Logger.getLogger(String.valueOf(FirstAutomationTest.class));
        logger.info("Test başlatıldı.");
        driver.manage().window().maximize();
        try {
            driver.get(GittigidiyorTest.mainUrl);
            // is the home page opened
            if(driver.getTitle() != null && driver.getTitle().contains(GittigidiyorTest.mainTitle)){
                // Go to login page
                GittigidiyorTest.LoginPopup(driver).click();
                GittigidiyorTest.LoginPageButton(driver).click();
                logger.info("Login page opened.");
                // do login
                GittigidiyorTest.UserName(driver).sendKeys("****@****.com");
                GittigidiyorTest.Password(driver).sendKeys("*******");
                GittigidiyorTest.LoginButton(driver).click();
                // check is the home page
                if(driver.getTitle() != null && driver.getTitle().contains(GittigidiyorTest.mainTitle)){
                    logger.info("Login successfully completed.");
                    GittigidiyorTest.SearchInput(driver).sendKeys("bilgisayar");
                    GittigidiyorTest.SearchButton(driver).click();
                    logger.info("Search has started.");
                    // check for promotion popup and close
                    GittigidiyorTest.WisCloseButton(driver);
                    // close cookie popup, causing blocking GotoPage
                    GittigidiyorTest.CookieCloseButton(driver).click();
                    // goto page with specific page number
                    logger.info("Going 2nd page.");
                    GittigidiyorTest.GotoPage(driver, 2).click();
                    if (driver.getCurrentUrl().contains("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2")) {
                        GittigidiyorTest.GotoItemPage(driver, 0, true).click();
                        String itemPrice = GittigidiyorTest.LowPriceInfo(driver).getText();
                        if (itemPrice.isEmpty()) {
                            logger.info("Item hasn't discount. Getting actual price.");
                            itemPrice = GittigidiyorTest.HighPriceInfo(driver).getText();
                        }
                        logger.info("Item adding to basket.");
                        GittigidiyorTest.AddToBasket(driver).click();
                        logger.info("Going to basket page.");
                        GittigidiyorTest.Basket(driver).click();
                        String basketPrice = GittigidiyorTest.BasketPriceInfo(driver).getText();
                        logger.info("Item price: " + itemPrice);
                        logger.info("Item basket price: " + basketPrice);
                        if (itemPrice.equals(basketPrice)) {
                            logger.info("Item price equals to item basket price.");
                            logger.info("Item amount increasing.");
                            GittigidiyorTest.IncreaseItemCount(driver).click();
                            int amount = Integer.parseInt(GittigidiyorTest.ItemAmount(driver).getAttribute("value"));
                            if (amount == 2) {
                                logger.info("Item amount increased.");
                                GittigidiyorTest.RemoveItemToBasket(driver).click();
                                logger.info("Item deleted to basket.");
                                logger.info("Test Successfully Completed.");
                            } else {
                                logger.error("Amount not true.");
                                Assert.fail();
                            }
                        } else {
                            logger.error("Prices mismatched.");
                            Assert.fail();
                        }
                    } else {
                        logger.error("Second page failed.");
                        Assert.fail();
                    }
                } else {
                    logger.error("Login Failed.");
                    Assert.fail();
                }
            } else {
                logger.error("Home page could not open.");
                Assert.fail();
            }
        } catch (Exception ex) {
            logger.fatal(ex.getMessage());
            Assert.fail();
        }
    }
}
