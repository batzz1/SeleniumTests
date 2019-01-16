package com.qa.test.amazon;

import com.qa.base.TestBase;
import com.qa.enums.Amazon;
import com.qa.pages.amazon.HomePage;
import com.qa.pages.amazon.ProductPage;
import com.qa.pages.amazon.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest extends TestBase {

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;

    public AmazonTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization(prop.getProperty("amazonInURL"));
        homePage = new HomePage(driver);
    }

    @Test
    public void purchaseBookOnAmazonTypePaperBack()  {
        String pageTitle = homePage.getPageTitle();
        Assert.assertEquals(pageTitle, Amazon.PAGE_TITLE.getValue());
        searchResultsPage = homePage.searchItem(Amazon.BOOK_TITLE.getValue());
        productPage = searchResultsPage.selectProductFromResults();
        productPage.clickOnBuyNow();
    }

    @Test
    public void purchaseBookOnAmazonTypeKindle() {
        String pageTitle = homePage.getPageTitle();
        Assert.assertEquals(pageTitle, Amazon.PAGE_TITLE.getValue());
        searchResultsPage = homePage.searchItem(Amazon.BOOK_TITLE.getValue());
        productPage = searchResultsPage.selectProductFromResults();
        productPage.buyKindleEditionOfProduct();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
