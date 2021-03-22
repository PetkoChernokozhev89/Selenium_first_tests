package com.selenium.course.test.course;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTests {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }


    @Test
    public void executeSimpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInPut = driver.findElement(By.id("user-name"));
        userNameInPut.sendKeys("standard_user");

        WebElement passWordInPut = driver.findElement(By.id("password"));
        passWordInPut.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginButton.click();

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        Select list = new Select(selectElement);
        list.selectByValue("lohi");
        list.selectByVisibleText("Name (Z to A)");

        WebElement addToCart = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCart.click();

        WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));

        assertEquals(badge.getText(), "1");


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
