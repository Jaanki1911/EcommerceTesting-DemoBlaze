package pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testbase.BasePage;

public class ContactPage extends BasePage {
	@FindBy(linkText = "Contact")
	private WebElement contactLink;
	@FindBy(id = "exampleModalLabel")
	private WebElement modalHeader;
	@FindBy(id = "recipient-email")
	private WebElement emailField;
	@FindBy(id = "recipient-name")
	private WebElement nameField;
	@FindBy(id = "message-text")
	private WebElement messageField;
	@FindBy(xpath = "//button[text()='Send message']")
	private WebElement sendMsg;
	@FindBy(css = "button.btn.btn-secondary")
	private WebElement endBtn;

	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	public void cLink() throws InterruptedException {
		contactLink.click();
		WebElement modalLabel = wait.until(ExpectedConditions.visibilityOf(modalHeader));
		System.out.println(modalLabel.getText());
		emailField.sendKeys("john123@gmail.com");
		System.out.println("email: " + emailField.getAttribute("value"));
		nameField.sendKeys("John Smith");
		System.out.println("name: " + nameField.getAttribute("value"));
		messageField.sendKeys("Thank you for approaching us, we will get in touch with you soon");
		System.out.println("message: " + messageField.getAttribute("value"));
		sendMsg.click();
		Thread.sleep(3000);
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	public void entertContactData(HashMap<String, String> testData) throws Exception {
		contactLink.click();
		Thread.sleep(3000);
		emailField.sendKeys(testData.get("contactemail"));
		System.out.println("email: " + emailField.getAttribute("value"));
		nameField.sendKeys(testData.get("contactname"));
		System.out.println("name: " + nameField.getAttribute("value"));
		messageField.sendKeys(testData.get("message"));
		System.out.println("message: " + messageField.getAttribute("value"));
		sendMsg.click();
		Thread.sleep(3000);
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	public void terminate() {
		contactLink.click();
		WebElement end = wait.until(ExpectedConditions.visibilityOf(endBtn));
		end.click();
	}
}
