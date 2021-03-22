package com.selenium.course.base;

import driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    public WebDriver driver;
    private String url;
    private int implicitWait;
    public String browser;


    @BeforeSuite
    public void readConfigPropeties() {
        try (
                FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("browser");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() {
        setupBrowserDriver();
        loadUrl();
        browser();

    }

    private void browser() {

    }

    private void loadUrl() {
        driver.get(url);
    }

    private void setupBrowserDriver() {
        switch (browser) {
            case "firefox":
                driver = DriverFactory.getFirefoxDriver(implicitWait);
                break;
            case "chrome":
            default:
                driver = DriverFactory.getChromeDriver(implicitWait);


        }



    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
