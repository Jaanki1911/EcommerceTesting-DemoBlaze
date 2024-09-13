package testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pageobjects.AboutUsPage;
import pageobjects.CartPage;
import pageobjects.ContactPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.SignUpPage;


public class ObjectRepositories {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions actions;
	public HomePage hp;
	public ContactPage cp;
	public AboutUsPage ap;
	public CartPage ct;
	public LoginPage lp;
	public SignUpPage sp;
	public static ExtentReports extent;
	public static ExtentTest test;
}
