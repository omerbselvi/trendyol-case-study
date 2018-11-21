package com.trendyol.test.pages;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By productName = By.className("product-name-text");
    By addToBasketBtn = By.id("addToBasketButton");
    By firstProduct = By.cssSelector("[data-original-index=\"1\"]");
    By productImages = By.cssSelector(".product-box img");
    By addToBasketText = By.className("add-to-basket-text");
    By sizeVariant = By.cssSelector(".variant-titlebox span");
    By productCount = By.cssSelector(".product-count > .count");

    //Ürünlerin hepsinin yüklenmesi için en aşağıya kaydırır ve bütün ürünler gelince listeye atar.
    public List<WebElement> scrollToEndAndGetProducts() {
        System.out.println("Ürünlerin hepsinin List e eklenmesi için aşağıya scroll yapılıyor...");
        System.out.println("(Bu işlem butikteki ürün sayısına göre uzun sürebilir)");

        int productsSize = driver.findElements(productImages).size();
        WebElement scrollToElement = driver.findElements(productImages).get(productsSize-1);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", scrollToElement);
        int productCounter = Integer.parseInt(driver.findElement(productCount).getText());

        System.out.println("Toplam ürün sayısı: " + productCounter);

        while (productCounter > productsSize){
            productsSize = driver.findElements(productImages).size();
            scrollToElement = driver.findElements(productImages).get(productsSize-1);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", scrollToElement);
            System.out.println("Bulunan ürün sayısı: " + productsSize);
        }
        List<WebElement> products = driver.findElements(productImages);
        System.out.println("Bulunan ürünlerin resimleri kontrol ediliyor...");
        System.out.println("(Bu işlem butikteki ürünlerin resimlerinin sayısına göre uzun sürebilir)");
        System.out.println("(Hata varsa log basılacaktır)");
        return products;
    }

    //Random bir ürüne tıklar
    public void clickToRandomProduct(List<WebElement> products) {
        System.out.println("Rastgele bir butik ürününe tıklanıyor... ");
        products.get(RandomUtils.nextInt(products.size())).click();
    }

    //Ürün detay sayfasındaki ürünün ismini gönderir (Assert için)
    public String getProductName() {
        wait(productName);
        return driver.findElement(productName).getText();
    }

    //Ürünü sepete atar, eğer ürün tükendiyse rastgele bir tabdaki rastgele bir butikten rastgele bir ürün seçer.
    //Tükenmeyen ürün gelene kadar bu işlemi tekrarlar
    //Seçilen ürün tükenmemiş ise ürünün "Beden" seçimi kısmı var mı diye kontrol eder.
    //Eğer "Beden" seçim kısmı var ise beden seçip sepete ekler, yoksa direk sepete ekler.
    public void addProductToCart() {
        System.out.println("Ürün sepete ekleniyor... ");
        if(driver.findElement(addToBasketText).getText().equals("Tükendi")){ //Ürün tükendiyse eğer başka ürün seç
            System.out.println("Ürün tükendiği için rastgele başka bir ürün seçiliyor...");
            HomePage homePage = new HomePage(driver);
            homePage.getRandomTab(9);
            BoutiquePage boutiquePage = new BoutiquePage(driver);
            boutiquePage.clickToRandomBoutique();
            List<WebElement> products = driver.findElements(productImages);
            this.clickToRandomProduct(products);
            addProductToCart();
            return;
        }
        if(driver.findElements(sizeVariant).size() > 0){ //Ürünün beden seçim kısmı var ise
            waitElementClickable(addToBasketBtn, driver);
            waitElementClickable(firstProduct, driver);
        }
        else {
            waitElementClickable(addToBasketBtn, driver);
        }
    }
}
