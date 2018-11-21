package com.trendyol.test.pages;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By fancyBox = By.className("fancybox-close");
    By loginBtn = By.className("login-container");
    By email = By.id("email");
    By password = By.id("password");
    By authenticateBtn = By.id("loginSubmit");
    By tabs = By.cssSelector(".main-nav .tabLink");
    By fancyBoxOverlay = By.className("fancybox-overlay");

    //"fancybox" ı(dialog) kapatır
    public void closeFancyBox() {
        waitElementClickable(fancyBox, driver);
        waitElementInvisibility(fancyBoxOverlay, driver);
    }

    //Login tuşuna basar
    public void clickToLogin() {
        System.out.println("Login tuşuna tıklanıyor...");
        waitElementClickable(loginBtn, driver);
    }

    //Inputlara göre login olur
    public void login() {
        waitElementSendKeys(email, "testomer@mailinator.com", driver);
        waitElementSendKeys(password, "asd123", driver);
        waitElementClickable(authenticateBtn, driver);
        waitElementInvisibility(fancyBoxOverlay, driver);
    }

    //Tabların sayısını gönderir
    public int getTabsSize() {
        wait(tabs);
        return driver.findElements(tabs).size();
    }

    //Random bir taba tıklar
    public void getRandomTab(int tabSize) {
        driver.findElement(By.cssSelector("#item"+(Integer)(RandomUtils.nextInt(tabSize)+1)+" > a")).click();
    }
}
