package com.trendyol.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @Before
    public void onStartUp(){
        String browserName = getParameter("browser");
        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.trendyol.com");
    }
    @After
    public void onShutdown(){
        driver.quit();
    }

    private String getParameter(String name) {
        String value = System.getProperty(name);

        if (value == null){
            throw new RuntimeException(name + " is not a parameter!");
        }

        if (value.isEmpty()){
            throw new RuntimeException(name + " is empty!");
        }

        return value;
    }
}
