package testbase;

import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.AboutUsPage;
import pageobjects.CartPage;
import pageobjects.ContactPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.SignUpPage;
import utilities.PropertiesOperations;

public class BasePage extends ObjectRepositories {
	
	public static void launch() throws Exception {
		//String browser = "chrome";
		//String url = "https://www.demoblaze.com/index.html";
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }
		driver.manage().window().maximize();

		driver.get(url);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@BeforeMethod
	public void open() throws Exception {
		launch();
		 hp = new HomePage();
		 cp = new ContactPage();
		 ap=  new AboutUsPage();
		 ct = new CartPage();
		 lp = new LoginPage();
		 sp = new SignUpPage();
	}

	@AfterMethod
	public void closeAllBrowsers() {
		driver.quit();
	}

}
