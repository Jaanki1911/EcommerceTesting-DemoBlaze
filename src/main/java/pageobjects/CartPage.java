package pageobjects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testbase.BasePage;

public class CartPage extends BasePage {
	@FindBy(css = "div.card a[href]")
	private List<WebElement> productLinks;
	@FindBy(css = "a.btn.btn-success.btn-lg")
	private WebElement addBtn;
	@FindBy(linkText = "Cart")
	private WebElement cart;
	@FindBy(id = "totalp")
	private WebElement totalBefore;
	@FindBy(xpath = "//*[@id='tbodyid']/tr/td[4]/a")
	private WebElement delete;
	@FindBy(id = "totalp")
	private WebElement totalAfter;
	@FindBy(css = "div.col-lg-8 > h2")
	private WebElement header;
	@FindBy(xpath = "//th[contains(text(),'Pic')]")
	private WebElement picHeader;
	@FindBy(xpath = "//th[contains(text(),'Title')]")
	private WebElement titleHeader;
	@FindBy(xpath = "//th[contains(text(),'Price')]")
	private WebElement priceHeader;
	@FindBy(xpath = "//th[contains(text(),'x')]")
	private WebElement xHeader;
	@FindBy(xpath = "//button[text()='Place Order']")
	private WebElement placeOrderButton;
	@FindBy(id = "name")
	private WebElement nameField;
	@FindBy(id = "country")
	private WebElement countryField;
	@FindBy(id = "city")
	private WebElement cityField;
	@FindBy(id = "card")
	private WebElement cardField;
	@FindBy(id = "month")
	private WebElement monthField;
	@FindBy(id = "year")
	private WebElement yearField;
	@FindBy(xpath = "//button[@onclick='purchaseOrder()']")
	private WebElement purchaseOrderButton;
	@FindBy(css = "div.sweet-alert h2")
	private WebElement thankYouText;
	@FindBy(css = "p.lead.text-muted")
	private WebElement leadText;
	@FindBy(css = "button.confirm")
	private WebElement okButton;

	public CartPage() {
		PageFactory.initElements(driver, this);
	}

	public void addProd() throws InterruptedException {
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
		Set<String> visitedLinks = new HashSet<>();
		int productsAddedToCart = 0;

		for (WebElement link : products) {
			String linkHref = link.getAttribute("href");
			if (visitedLinks.contains(linkHref)) {
				continue;
			}
			visitedLinks.add(linkHref);

			((JavascriptExecutor) driver).executeScript("window.open(arguments[0]);", linkHref);

			String originalWindow = driver.getWindowHandle();
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(originalWindow)) {
					driver.switchTo().window(window);
					break;
				}
			}

			WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addBtn));
			System.out.println("Opened: " + driver.getCurrentUrl());
			addToCartButton.click();

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText();
			System.out.println("Alert message: " + alertText);
			alert.accept();

			productsAddedToCart++;
			driver.close();
			driver.switchTo().window(originalWindow);
		}
		System.out.println("Total number of products added to the cart: " + productsAddedToCart);
		cart.click();
		Thread.sleep(5000);
		System.out.println("Header Text: " + header.getText());
		System.out.println("Pic Header Text: " + picHeader.getText());
		System.out.println("Title Header Text: " + titleHeader.getText());
		System.out.println("Price Header Text: " + priceHeader.getText());
		System.out.println("x Header Text: " + xHeader.getText());
		WebElement Total = wait.until(ExpectedConditions.visibilityOf(totalBefore));

		int totalBeforeDeletion = Integer.parseInt(Total.getText());
		System.out.println("Total price before deletion :" + totalBeforeDeletion);

		WebElement deleteProduct = wait.until(ExpectedConditions.elementToBeClickable(delete));
		deleteProduct.click();
		Thread.sleep(5000);
		WebElement Total1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
		int totalAfterDeletion = Integer.parseInt(Total1.getText());
		System.out.println("Total price after deletion :" + totalAfterDeletion);

		if (totalAfterDeletion < totalBeforeDeletion) {
			System.out.println("The item was successfully deleted from the cart.");
		} else {
			System.out.println("The item is still present in the cart.");
		}
	}

	public void enterCartData(HashMap<String, String> testData) throws Exception {
		addProd();
		placeOrderButton.click();
		Thread.sleep(3000);
		nameField.sendKeys(testData.get("Name"));
		countryField.sendKeys(testData.get("Country"));
		cityField.sendKeys(testData.get("City"));
		cardField.sendKeys(testData.get("Credit Card Number"));
		monthField.sendKeys(testData.get("Month"));
		yearField.sendKeys(testData.get("Year"));
		purchaseOrderButton.click();
		System.out.println("Text: " + thankYouText.getText());
		System.out.println("Details: " + leadText.getText());
		okButton.click();
		Thread.sleep(4000);
	}
}
