package com.qa.pages.amazon;

import com.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {


    @FindBy(id = "buy-now-button")
    private WebElement buyNowBtn;

    @FindBy(xpath = "//a[@id='a-autoid-2-announce']")
    private WebElement kindleEdition;

    @FindBy(id = "checkout-button")
    private WebElement buyKindleBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickOnBuyNow() {
        waitForElementToBeClickable(buyNowBtn, 20);
        buyNowBtn.click();
    }

    public void buyKindleEditionOfProduct() {
        waitForElementToBeClickable(kindleEdition);
        kindleEdition.click();
        switchToNewTab();
        waitForElementToBeClickable(buyKindleBtn);
        buyKindleBtn.click();
    }
}
