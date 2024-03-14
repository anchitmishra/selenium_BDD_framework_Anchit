package framework;

// import cucumber.api.Scenario;
// import cucumber.api.java.After;
// import cucumber.api.java.Before;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class MySession {

	/**
	 * This class contains wedriver creation and quitting methods. These are
	 * required while
	 * running each and every scenario. Methods are defined under @Before and @After
	 * hooks
	 * to get initialized at start and end of the test.
	 * 
	 * @author shanky
	 *
	 */

	/**
	 * ThreadLocal variable which contains the webdriver instance which is used to
	 * perform browser interactions with.
	 */

	public static WebDriver driver;

	/**
	 * method to create webdriver instance.
	 * 
	 * @throws InterruptedException
	 */
	@Before
	public static void createDriver() throws InterruptedException, IOException {
		System.out.println("-----------------------------------Inside Windows -----------------------------");
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		// chromeOptions.addExtensions(new File("./extensions/AdBlock.crx"));
		driver = new ChromeDriver(chromeOptions);
		getWebDriver().manage().window().maximize();
	}

	/**
	 * @return the webdriver for the current thread
	 */
	public static WebDriver getWebDriver() throws InterruptedException, IOException {
		return driver;
	}

	/**
	 * method executes at the end of each scenario and takes screenshot in case of
	 * scenario failure.
	 * Also, quit the webdriver
	 * 
	 * @param scenario to verify if scenarios has passed or failed
	 */
	@After
	public void teardown(Scenario scenario) {

		// Here will compare if test is failing then only it will enter into if
		// condition

		if (scenario.isFailed()) {
			try {
				// Create reference of TakesScreenshot
				TakesScreenshot ts = (TakesScreenshot) getWebDriver();

				// Call method to capture screenshot
				File source = ts.getScreenshotAs(OutputType.FILE);

				FileUtils.copyFile(source, new File(".//src//test//java//outputFiles//" + "FailScreenshot" +
						new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss")
								.format(new GregorianCalendar().getTime())
						+ ".png"));
				Log.info("Scenario failed and screenshot saved in outputFiles folder");
			} catch (Exception e) {

				Log.info("Exception while taking screenshot " + e.getMessage());

			}
		}

		Log.info("Shutting down driver" + "\n" + "----------------------------------------------");
		System.out.println("\n");
		// quitting the webdriver
		driver.quit();
	}

}
