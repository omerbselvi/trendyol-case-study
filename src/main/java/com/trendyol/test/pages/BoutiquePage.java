package com.trendyol.test.pages;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BoutiquePage extends BasePage {
    public BoutiquePage(WebDriver driver) {
        super(driver);
    }

    By boutiqueImages = By.cssSelector(".boutique-large-list-tmpl-result .butik-large-image img");
    By clickableBoutiqueImage = By.cssSelector(".boutique-large-list-tmpl-result .butik-large-image");

    //Tabları dolaşır ve butik resimlerini kontrol eder
    public void checkBoutiquesAndCheckBoutiqueImages(int tabSize) {
        System.out.println("(Hata varsa log basılacaktır)");
        for (int i = 0; i < tabSize; i++){
            wait(By.cssSelector("#item"+(Integer)(i+1)+" > a"));
            driver.findElement(By.cssSelector("#item"+(Integer)(i+1)+" > a")).click();
            List<WebElement> images = driver.findElements(boutiqueImages);
            System.out.println((Integer)(i+1) + " Numaralı tabın butik resimleri kontrol ediliyor...");
            isImagesLoaded(images);
        }
    }

    //Random bir butiğe girer
    public void clickToRandomBoutique() {
        System.out.println("Rastgele bir butiğe girildi... ");
        List<WebElement> images = driver.findElements(clickableBoutiqueImage);
        images.get(RandomUtils.nextInt(images.size())).click();
    }
}
