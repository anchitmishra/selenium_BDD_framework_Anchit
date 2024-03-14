package logic;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import framework.MySession;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import pages.guru99bankpage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class guru99steps {

    guru99bankpage bankPage;
    WebDriver driver;
    String username;
    String password;
    String welcomeTxt;
    File file;
    PrintWriter pw;

    public guru99steps() throws InterruptedException, IOException {
        driver = MySession.getWebDriver();
        bankPage = new guru99bankpage(driver);

    }

    @Given("^user is on guru99 demo banking page \"([^\"]*)\"$")
    public void launchUrl(String url) throws Throwable {
        bankPage.get(url);
        // bankPage.waitForPageToLoad(bankPage.getTitle());
        // bankPage.findElement(bankPage.adBlock).sendKeys(Keys.CONTROL +"\t");
    }

    @Then("^the user enters the username \"([^\"]*)\"$")
    public void enterUsername(String username) throws Throwable {
        System.out.println("inside users enters step");
        bankPage.waitForPageToLoad(bankPage.getTitle());
        bankPage.waitForVisibility(bankPage.usernameBox);
        bankPage.findElement(bankPage.usernameBox).sendKeys(username);
    }

    @And("^the user enters the password \"([^\"]*)\"$")
    public void enterPassword(String password) throws Throwable {
        bankPage.waitForPageToLoad(bankPage.getTitle());
        bankPage.waitForVisibility(bankPage.passwordBox);
        bankPage.findElement(bankPage.passwordBox).sendKeys(password);
    }

    @And("^the user clicks on login button")
    public void clickLogin() throws UnhandledAlertException {
        try {
            bankPage.waitForPageToLoad(bankPage.getTitle());
            bankPage.waitForVisibility(bankPage.loginBtn);
            bankPage.findElement(bankPage.loginBtn).click();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Then("^the user should be landing on the homepage of the banking application and see manager ID as \"([^\"]*)\"$")
    public void loginVerify(String managerID)
            throws Throwable {
        By managerElement = (By.xpath("//td[contains(text(),'" + managerID + "')]"));
        String ActualTitle = bankPage.findElement(managerElement).getText().toString();
        Assert.assertEquals("Manger Id : " + managerID, ActualTitle);
    }

    @Then("^the user is navigated back to login page$")
    public void backToLogin() throws UnhandledAlertException {
        driver.switchTo().alert().accept();
        bankPage.waitForPageToLoad(bankPage.getTitle());
        String ActualTitle = bankPage.getTitle();
        String ExpectedTitle = "Guru99 Bank Home Page";
        Assert.assertEquals(ExpectedTitle, ActualTitle);
    }

    @And("^the user clicks the reset button$")
    public void clickReset() {
        bankPage.waitForElementToBeClickable(bankPage.resetBtn);
        bankPage.findElement(bankPage.resetBtn).click();
    }

    @Then("^the credentials field should be reset$")
    public void checkReset() {
        String actualString = bankPage.findElement(bankPage.usernameBox).getText();
        String expectedString = "";
        Assert.assertEquals(expectedString, actualString);
    }

    @And("^the user clicks on new account$")
    public void clkNewAcc() {
        bankPage.waitForElementToBeClickable(bankPage.newAccount);
        bankPage.findElement(bankPage.newAccount).click();
    }

    @And("^the user enters new account details with customer id as \"([^\"]*)\" account type as \"([^\"]*)\" and initial deposit as \"([^\"]*)\"$")
    public void enterDetails(String custID, String accType, String deposit) {
        WebElement customerIdElement = bankPage.findElement(bankPage.custID);
        customerIdElement.click();
        customerIdElement.sendKeys(custID);
        // By accTypeElement = (By.xpath("//option[@value="+accType+"]"));
        By accTypeElement = (By.xpath("//option[contains(text()," + accType + ")]"));
        bankPage.findElement(bankPage.accType).click();
        bankPage.findElement(accTypeElement).click();
        WebElement depositElement = bankPage.findElement(bankPage.deposit);
        depositElement.click();
        depositElement.sendKeys(deposit);
    }

    @And("^the user clicks on submit$")
    public void clickSubmit() {
        bankPage.findElement(bankPage.submit).click();
    }

    @And("^the user should be able to see new account details$")
    public void verifyAccCreation() throws InterruptedException {
        bankPage.waitForVisibility(bankPage.accSuccessLbl);
        Thread.sleep(5000);
        String accCreationMessage = bankPage.findElement(bankPage.accSuccessLbl).getText();
        String expectedString = "Account Generated Successfully!!!";
        Assert.assertEquals(accCreationMessage, expectedString);
    }

    @And("^the user clicks on new customer tab$")
    public void clickNewCust() {
        bankPage.waitForElementToBeClickable(bankPage.newCustomer);
        bankPage.findElement(bankPage.newCustomer).click();
    }

    @And("^the user enters basic customer details$")
    public void newCustDetails(DataTable newCustDetails) throws InterruptedException {
        List<List<String>> data = newCustDetails.cells();
        bankPage.waitForVisibility(bankPage.custName);
        bankPage.findElement(bankPage.custName).sendKeys(data.get(1).get(0));
        By genderElement = (By.xpath("//input[@value='" + data.get(1).get(1) + "']"));
        // Thread.sleep(5000);
        bankPage.waitForElementToBeClickable(genderElement);
        bankPage.findElement(genderElement).click();
        // entering through sendkeys, need to try using calender input
        bankPage.findElement(bankPage.DOB).sendKeys(data.get(1).get(2));
        bankPage.findElement(bankPage.custAddress).sendKeys(data.get(1).get(3));
        bankPage.findElement(bankPage.city).sendKeys(data.get(1).get(4));
        bankPage.findElement(bankPage.state).sendKeys(data.get(1).get(5));
        bankPage.findElement(bankPage.PIN).sendKeys(data.get(1).get(6));
        bankPage.findElement(bankPage.mobile).sendKeys(data.get(1).get(7));
        bankPage.findElement(bankPage.email).sendKeys(data.get(1).get(8));
        bankPage.findElement(bankPage.password).sendKeys(data.get(1).get(9));
    }

    @And("^the user validates \"([^\"]*)\" registered successfully message on the screen$")
    public void userConfirm(String custName) throws InterruptedException {
        if (bankPage.isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
        String Actual = bankPage.findElement(By.xpath("//td[contains(text(),'" + custName + "')]")).getText()
                .toString();
        String Expected = custName;
        Assert.assertEquals(Expected, Actual);
        String actualMsg = bankPage.findElement(By.xpath("//p[contains(text(),'Customer Registered Successfully!!!')]"))
                .getText().toString();
        String expctedMsg = "Customer Registered Successfully!!!";
        Assert.assertEquals(actualMsg, expctedMsg);
    }

    @And("^And the user clicks on customised statement$")
    public void customisedStatement(DataTable customisedStatementDetails) {
        List<List<String>> data = customisedStatementDetails.cells();
        bankPage.findElement(bankPage.accNumber).sendKeys(data.get(1).get(0));

    }

}
