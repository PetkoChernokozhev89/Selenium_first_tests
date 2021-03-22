package com.selenium.course.test.course;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void executeSimpleTest() {
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInPut = driver.findElement(By.id("user-name"));
        userNameInPut.sendKeys("standard_user");

        WebElement passWordInPut = driver.findElement(By.id("password"));
        passWordInPut.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginButton.click();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("product_label1")));

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchCookieException.class);
        fluentWait.until(ExpectedConditions.invisibilityOf(loginButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", loginButton);
        js.executeScript("arguments[0].click()", loginButton);

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
