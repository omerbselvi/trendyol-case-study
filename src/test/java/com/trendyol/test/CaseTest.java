package com.trendyol.test;

import com.trendyol.test.pages.BasketPage;
import com.trendyol.test.pages.BoutiquePage;
import com.trendyol.test.pages.HomePage;
import com.trendyol.test.pages.ProductPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CaseTest extends BaseTest{

    @Test
    public void trendyolCase(){
        //Default açılan dialog u kapatıp login olan bölüm
        HomePage homePage = new HomePage(driver);
        homePage.closeFancyBox();
        homePage.clickToLogin();
        homePage.login();

        //Tabları dolaşıp butik resimlerinin yüklenip yüklenmediğini kontrol eden bölüm
        int tabSize = homePage.getTabsSize();
        BoutiquePage boutiquePage = new BoutiquePage(driver);
        boutiquePage.checkBoutiquesAndCheckBoutiqueImages(tabSize);
        homePage.getRandomTab(tabSize);
        boutiquePage.clickToRandomBoutique();

        //Rastgele bir butiğe girdikten sonra en aşağıya scroll (JavascriptExecutor ile) edip bütün resimlerin yüklenmesini kontrol eden bölüm
        ProductPage productPage = new ProductPage(driver);
        List<WebElement> products = productPage.scrollToEndAndGetProducts();
        productPage.isImagesLoaded(products);

        //Rastgele bir ürüne girip ve sepete atan bölüm
        productPage.clickToRandomProduct(products);
        productPage.addProductToCart();
        String productName = productPage.getProductName();

        //Ürünü sepete attıktan sonra Sepetim e giden ve sepete atılan ürünün sepetteki ile aynı olduğunu assert eden bölüm
        BasketPage basketPage = new BasketPage(driver);
        basketPage.clickToBasket();
        String basketProductName = basketPage.getBasketProductName();
        assertThat("When product added to cart is not same: ", productName, containsString(basketProductName));
    }
}
