package pageobjects;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.BasePage;

public class SignUpPage extends BasePage{
	 @FindBy(linkText = "Sign up")
	    WebElement signUpLink;

	    @FindBy(id = "sign-username")
	    WebElement usernameField;

	    @FindBy(id = "sign-password")
	    WebElement passwordField;
        
	    @FindBy(xpath = "//button[text()='Close']")
	    private WebElement exitButton;
	    
	    @FindBy(xpath = "//button[@onclick='register()']")
	    WebElement signUpButton;

	    public SignUpPage() {
			PageFactory.initElements(driver, this);
		}
	    public void register() throws InterruptedException {
	    signUpLink.click();
        Thread.sleep(3000);

        usernameField.sendKeys("Anushka");
        passwordField.sendKeys("Anushka@123");
        signUpButton.click();

        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (alertText.contains("User already exists")) {
            System.out.println("User already exists. Alert message: " + alertText);
        } else {
            System.out.println("User registered successfully. Alert message: " + alertText);
        }
        alert.accept();
    }
	    public void enterSignUpData(HashMap<String, String> testData) throws Exception {
	        signUpLink.click();
	        Thread.sleep(5000);
	        usernameField.sendKeys(testData.get("username"));
	        passwordField.sendKeys(testData.get("password"));
	        signUpButton.click();
	        Thread.sleep(3000);
	       String alertText = driver.switchTo().alert().getText();
	        System.out.println(alertText);
	        driver.switchTo().alert().accept();
	        Thread.sleep(3000);
	    }


	    
}
