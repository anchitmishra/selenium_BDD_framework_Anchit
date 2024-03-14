package logic;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import framework.MySession;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.mmtPage;

public class makeMyTripSteps {

    mmtPage tripPage;
    WebDriver driver;

    public makeMyTripSteps() throws InterruptedException, IOException {
        driver = MySession.getWebDriver();
        tripPage = new mmtPage(driver);

    }

    @Given("^user launches MMT website \"([^\"]*)\"$")
    public void user_launches_mmt_website(String url) throws Throwable {
        tripPage.get(url);
    }

    @Given("^the user clicks on \"([^\"]*)\" with title \"([^\"]*)\" tab on homepage$")
    public void selectionAtHomepage(String tab, String title) {
        tripPage.waitForPageToLoad(tripPage.getTitle());
        By tabElement = By.xpath("//span[contains(text(),'" + tab + "')]");
        tripPage.waitForElementToBeClickable(tabElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
                tripPage.findElement(tabElement));
        tripPage.findElement(tabElement).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String expectedTitle = title;
        String actualTitle = tripPage.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Given("^the user searches for \"([^\"]*)\" with title \"([^\"]*)\" on destination selection page$")
    public void selectingTypeOfDest(String dest, String title) {

        By destElement = By.xpath("//h3[contains(text(),'" + dest + "')]//preceding-sibling::span");
        tripPage.waitForElementToBeClickable(destElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
                tripPage.findElement(destElement));
        tripPage.findElement(destElement).click();
        String pageTitle = tripPage.getTitle();
        tripPage.waitForPageToLoad(pageTitle);
        String expectedTitle = title;
        String actualTitle = pageTitle;
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Given("^the user selects \"([^\"]*)\" with title \"([^\"]*)\" on location selection page$")
    public void selectingLocation(String loc, String title) {

        By locationElement = By.xpath("//img[@alt='" + loc + "']");
        tripPage.waitForElementToBeClickable(locationElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                tripPage.findElement(locationElement));
        tripPage.findElement(locationElement).click();
        String pageTitle = tripPage.getTitle();
        tripPage.waitForPageToLoad(pageTitle);
        String expectedTitle = title;
        String actualTitle = pageTitle;
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Given("^the user clicks on packages$")
    public void clickPackage() {
        tripPage.waitForElementToBeClickable(tripPage.packageTab);
        tripPage.findElement(tripPage.packageTab).click();
        tripPage.waitForElementToBeClickable(tripPage.viewPackage);
        tripPage.findElement(tripPage.viewPackage).click();
    }

    @Then("^the user should be able to see all available packages under \"([^\"]*)\"$")
    public void the_user_should_be_able_to_see_all_available_packages(String location) throws InterruptedException {
        if (tripPage.isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
        String pageTitle = tripPage.getTitle();
        tripPage.waitForPageToLoad(pageTitle);
        driver.switchTo().frame(tripPage.findElement(tripPage.modalPopup));
        tripPage.waitForElementToBeClickable(tripPage.closeIframe);
        tripPage.findElement(tripPage.closeIframe).click();
        String expectedString = location;
        tripPage.waitForVisibility(tripPage.baliPackage);
        String actualString = tripPage.findElement(tripPage.baliPackage).getText().toString();
        Assert.assertEquals(expectedString, actualString);

    }
}
