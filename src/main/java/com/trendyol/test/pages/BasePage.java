package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void writeTo(By by, String value){
        driver.findElement(by).sendKeys(value);
    }

    public void clickTo(By by){
        driver.findElement(by).click();
    }

    public void waitElementInvisibility(By by, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(invisibilityOfElementLocated(by));
    }

    public void waitElementClickable(By by, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(elementToBeClickable(by)).click();
    }

    public void waitElementSendKeys(By by, String value, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(by)).sendKeys(value);
    }

    public void wait(By by){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(visibilityOfElementLocated(by));
    }

    //Resimlerin yüklenip yüklenmediğini kontrol eden method
    public void isImagesLoaded(List<WebElement> products) {
        for (WebElement image : products){
            Object result = ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].complete && "+
                            "typeof arguments[0].naturalWidth != \"undefined\" && "+
                            "arguments[0].naturalWidth > 0", image);

            boolean loaded = false;
            if (result instanceof Boolean) {
                loaded = (Boolean) result;
                if(loaded == false){
                    System.out.println("Image error: " + image);
                }
            }
        }
        System.out.println("Butik/Ürün resim kontrolü bitti. ");
    }
}
