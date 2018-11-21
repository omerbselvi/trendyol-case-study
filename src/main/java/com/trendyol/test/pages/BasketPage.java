package com.trendyol.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }
    By basketBtn = By.cssSelector("#myBasketListItem .icon-container");
    By basketProductDesc = By.cssSelector(".productsContainer .basketlist-productinfo-description");

    //Sepete gider
    public void clickToBasket() {
        System.out.println("Sepetime gidiliyor...");
        waitElementClickable(basketBtn, driver);
    }

    //Sepetteki ürünün ismini gönderir (Assert için)
    public String getBasketProductName() {
        wait(basketProductDesc);
        return driver.findElement(basketProductDesc).getText();
    }
}
