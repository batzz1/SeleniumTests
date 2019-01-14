package com.qa.test.cleartrip;

import com.qa.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClearTripTest extends TestBase {

    public ClearTripTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization(prop.getProperty("cleartripURL"));
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
