package com.qa.pages.cleartrip;

import com.qa.base.BasePage;
import com.qa.enums.ClearTrip;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "RoundTrip")
    private WebElement roundTrip;

    @FindBy(id = "FromTag")
    private WebElement pickUpLocation;

    @FindBy(name = "destination")
    private WebElement destinationLocation;

    @FindBy(id = "DepartDate")
    private WebElement departDate;

    @FindBy(id = "ReturnDate")
    private WebElement returnDate;

    @FindBy(xpath = "//a[@class='ui-state-default ui-state-highlight ui-state-active ']")
    private WebElement highlightedDate;

    @FindBy(id = "Childrens")
    private WebElement childrens;

    @FindBy(id = "SearchBtn")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterLocationDetails() {
        waitForElementToBeClickable(pickUpLocation, 20);
        roundTrip.click();
        selectDestination(pickUpLocation, ClearTrip.PICKUP.getValue());
        selectDestination(destinationLocation, ClearTrip.DESTINATION.getValue());
        selectDate();
    }

    private void selectDestination(WebElement element, String location) {
        waitForElementToBeVisible(element, 20);
        element.sendKeys(location);
        WebElement autoComplete = driver.findElement(By.id("ui-id-1"));
        List<WebElement> autoCompleteList = autoComplete.findElements(By.className("list"));
        autoCompleteList.stream().filter(webElement -> webElement.getText().contains(location))
                .forEach(WebElement::click);
    }

    public void selectDate() {
        String date[] = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).split("/");
        String returnDate = String.valueOf(10 + Integer.parseInt(date[0]));
        departDate.click();
        highlightedDate.click();

        List<WebElement> rows, cols;
        rows = driver.findElement(By.className("calendar")).findElements(By.tagName("tr"));
        int i = 1;
        while (i < rows.size()) {
            cols = rows.get(i).findElements(By.tagName("td"));

            cols.stream().filter(x -> x.getText().equals(returnDate)).forEach(WebElement::click);

            i++;
        }

    }

    public FlightSearchPage addChildrens() {
        Select select = new Select(childrens);
        select.selectByVisibleText("1");
        searchBtn.click();
        return new FlightSearchPage(driver);
    }


}
