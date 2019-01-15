package com.qa.pages.amazon;

import com.qa.base.BasePage;
import com.qa.enums.Amazon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'All Categories')]")
    private WebElement allCategories;

    private final String productResults = "//h2[@class='a-size-medium s-inline  s-access-title  a-text-normal']";
    @FindBy(xpath = productResults)
    private List<WebElement> searchResults;

    private final String productPrices = "//h3[contains(text(),'Kindle Edition')]/parent::a/parent::div/following-sibling::div/child::a/child::span/following-sibling::span";
    @FindBy(xpath = productPrices)
    private List<WebElement> searchresultPrices;

    @FindBy(id = "sort")
    private WebElement sortResults;



    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Selects Item By Using Filter : Low To High
     */
    public ProductPage selectProductFromResults(){
        try{
            if(allCategories.isDisplayed())
            allCategories.click();
        } catch(Exception ignore){
            //Ignore
        }

        waitForElementToBeVisible(sortResults);
        Select dropdown = new Select(sortResults);
        dropdown.selectByVisibleText("Price: Low to High");
        waitForPresenceOfAllElements(By.xpath(productResults));
        searchResults.stream()
                .filter(webElement -> webElement.getText().equals(Amazon.BOOK_TITLE.getValue()))
                .forEach(WebElement::click);
        switchToNewTab();
        return new ProductPage(driver);
    }
}
