package com.selenium.course.test.course;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.base.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvReader;

import java.io.IOException;

public class LoginTest extends TestUtil {
    //private WebDriver driver;

    /*@BeforeTest
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }*/


    @DataProvider(name = "login-Data")
    public static Object[][] dataProviderFromCsv() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-Data")
    public void executeSimpleTest(String userName, String password) {
        //driver.get("https://www.saucedemo.com/");

        WebElement userNameInPut = driver.findElement(By.id("user-name"));
        userNameInPut.clear();
        userNameInPut.sendKeys(userName);

        WebElement passWordInPut = driver.findElement(By.id("password"));
        passWordInPut.clear();
        passWordInPut.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginButton.click();

       /* userNameInPut.clear();
        passWordInPut.clear();*/
    }
   /* @AfterTest
    public void tearDown() {
        driver.quit();
    }*/
}
