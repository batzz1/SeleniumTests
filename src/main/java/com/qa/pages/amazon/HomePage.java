package com.qa.pages.amazon;

import com.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {



    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBar;

    @FindBy(xpath = "//input[@value='Go']")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public SearchResultsPage searchItem(String itemName) {
        waitForElementToBeVisible(searchBar);
        searchBar.sendKeys(itemName);
        searchBtn.click();
        return new SearchResultsPage(driver);
    }
}
