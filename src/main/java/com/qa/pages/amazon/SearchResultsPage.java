package com.qa.pages.amazon;

import com.qa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'All Categories')]")
    private WebElement allCategories;

    @FindBy(xpath = "//h2[@class='a-size-medium s-inline  s-access-title  a-text-normal']")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
