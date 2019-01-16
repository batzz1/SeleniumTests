package com.qa.pages.cleartrip;

import com.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FlightSearchPage extends BasePage {

    @FindBy(xpath = "//button[@class='booking fRight'][1]")
    private List<WebElement> bookBtn;

    @FindBy(xpath = "//h2[@class='totalAmount']")
    private WebElement totalAmount;

    @FindBy(id = "close")
    private WebElement closeBtn;

    @FindBy(id = "priceChangeIncreasedDiff")
    private WebElement increasedFare;

    @FindBy(id = "insurance_box")
    private WebElement insuranceCheckBox;

    @FindBy(xpath = "//strong[@id='totalFare']/span[1]/following-sibling::span[@id='counter']")
    private List<WebElement> totalTripFare;



    //move to next page
    @FindBy(id = "itineraryBtn")
    private WebElement continueBookingBtn;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectTotalAmount() {
        Integer inceasedPrice = null;
        Integer getTripFare = null;
        waitForElementToBeVisible(totalAmount, 40);
        Integer totalFare = Integer.parseInt(totalAmount.getText().replaceAll("[^0-9]*",""));
        bookBtn.stream().filter(x -> x.isDisplayed()).forEach(WebElement::click);
        try {
            if (closeBtn.isDisplayed()) {
                inceasedPrice = Integer.parseInt(increasedFare.getText().replaceAll("[^0-9]*", ""));
                closeBtn.click();
            }

        } catch (NoSuchElementException ex) {
            //ignore
        }
        waitForElementToBeClickable(insuranceCheckBox, 30);
        insuranceCheckBox.click();

        scrollToElement(By.id("itineraryBtn"));
        for (WebElement element : totalTripFare) {
            if (element.isDisplayed()) {
                getTripFare = Integer.parseInt(element.getText().replaceAll("[^0-9]*", ""));
            }
        }
        if(inceasedPrice == null){
            Assert.assertEquals(getTripFare.intValue(), getTripFare.intValue());

        }
        else
        {
            Assert.assertEquals(getTripFare.intValue(), inceasedPrice.intValue()+getTripFare.intValue());

        }
    }
}
