package com.qa.test.amazon;

import com.qa.base.TestBase;
import com.qa.pages.amazon.HomePage;
import com.qa.pages.amazon.SearchResultsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest extends TestBase {

    HomePage homePage;
    SearchResultsPage searchResultsPage;

    public AmazonTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization(prop.getProperty("amazonInURL"));
    }

    @Test
    public void Test() {
        System.out.println("Test");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
