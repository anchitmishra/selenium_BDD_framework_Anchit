package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.CommonMethods;

public class guru99bankpage extends CommonMethods {

    public guru99bankpage(WebDriver driver) throws IOException {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public By usernameBox = (By.xpath("//input[@name='uid']"));
    public By passwordBox = (By.xpath("//input[@name='password']"));
    public By loginBtn = (By.xpath("//input[@name='btnLogin']"));
    public By welcomeTxt = (By.xpath("//marquee[contains(text(), \"Welcome To Manager's Page of Guru99 Bank\")]"));
    public By resetBtn = (By.xpath("//input[@name='btnReset']"));
    public By newAccount = (By.xpath("//a[contains(text(),'New Account')]"));
    public By custID = (By.xpath("//input[@name='cusid']"));
    public By accType = (By.xpath("//select[@name='selaccount']"));
    public By deposit = (By.xpath("//input[@name='inideposit']"));
    public By submit = (By.xpath("//input[@type='submit']"));
    public By accSuccessLbl = (By.xpath("//p[contains(text(),'Account Generated Successfully!!!')]"));
    public By newCustomer = (By.xpath("//a[contains(text(),'New Customer')]"));
    public By custName = (By.xpath("//input[@type='text']"));
    public By gender = (By.xpath("//td[contains(text(),'Gender')]"));
    public By DOB = (By.xpath("//input[@id='dob']"));
    public By custAddress = (By.xpath("//textarea[@name='addr']"));
    public By city = (By.xpath("//input[@name='city']"));
    public By state = (By.xpath("//input[@name='state']"));
    public By PIN = (By.xpath("//input[@name='pinno']"));
    public By mobile = (By.xpath("//input[@name='telephoneno']"));
    public By email = (By.xpath("//input[@name='emailid']"));
    public By password = (By.xpath("//input[@name='password']"));
    // public By submitCust = (By.xpath("//input[@name='sub']"));
    public By resetCust = (By.xpath("//input[@name='res']"));
    // public By newCustCreation = (By.xpath("//td[contains(text(),'John Smith')]"));
    public By custCreationMsg = (By.xpath("//p[contains(text(),'Customer Registered Successfully!!!')]"));
    public By accNumber = (By.xpath("//input[@name='accountno']"));
    public By frame1 = (By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
    public By frame2 = (By.id("ad_iframe"));
    public By adBlock = (By.xpath("//h1[contains(text(),\"Thank you for installing AdBlock!\")]"));
    
}
