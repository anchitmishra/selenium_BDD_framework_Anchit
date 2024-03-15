package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.CommonMethods;

public class mmtPage extends CommonMethods {

    public mmtPage(WebDriver driver) throws IOException {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public By packageTab = (By.xpath("//a[contains(text(),'Packages')]"));
    public By viewPackage = (By.xpath("//div[@id='Packages']//a[contains(text(),'VIEW ALL ')]"));
    public By closeIframe = (By.xpath("//span[@class='close closeIcon']"));
    public By baliPackage = (By.xpath("//h1[contains(text(),'Bali Packages')]"));
    public By modalPopup = (By.xpath("//iframe[@src='https://www.googletagmanager.com/ns.html?id=GTM-PHR9S8W']")); 
}
