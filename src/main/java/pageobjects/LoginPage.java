package pageobjects;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testbase.BasePage;
import utilities.PropertiesOperations;

public class LoginPage extends BasePage {
	@FindBy(linkText = "Log in")
    private WebElement loginLink;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@onclick='logIn()']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//button[text()='Close']")
    private WebElement exitButton;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;
    
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	 
	public void signIn() throws Exception {
		loginLink.click();
		Thread.sleep(4000);
		usernameField.sendKeys(PropertiesOperations.getPropertyValueByKey("username"));
		passwordField.sendKeys(PropertiesOperations.getPropertyValueByKey("password"));
		loginButton.click();
        Thread.sleep(8000);
        logoutLink.click();
	}
	public void enterLoginData(HashMap<String, String> testData) throws Exception {
		loginLink.click();
	    Thread.sleep(5000);
	    usernameField.sendKeys(testData.get("username"));
	    passwordField.sendKeys(testData.get("password"));
	    loginButton.click();
	    WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
	    logout.click();
	    
	}
}
