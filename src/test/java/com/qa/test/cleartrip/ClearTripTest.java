package com.qa.test.cleartrip;

import com.qa.base.TestBase;
import com.qa.pages.cleartrip.FlightSearchPage;
import com.qa.pages.cleartrip.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClearTripTest extends TestBase {

    HomePage homePage;
    FlightSearchPage flightSearchPage;

    public ClearTripTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization(prop.getProperty("cleartripURL"));
        homePage = new HomePage(driver);
    }

    @Test
    public void bookFlightOnClearTrip() {
        homePage.enterLocationDetails();
        flightSearchPage = homePage.addChildrens();
        flightSearchPage.selectTotalAmount();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
