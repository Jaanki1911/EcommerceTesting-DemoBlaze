package pageobjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import testbase.BasePage;

public class HomePage extends BasePage {
	@FindBy(xpath = "//img[@src='bm.png']")
	private WebElement logoImage;
	@FindBy(id = "nava")
	private WebElement brandLink;
	@FindBy(css = "a.nav-link[href='index.html']")
	private WebElement homeLink;
	@FindBy(css = "a.nav-link[data-target='#exampleModal']")
	private WebElement contactLink;
	@FindBy(xpath = "//a[text()='About us']")
	private WebElement aboutUsLink;
	@FindBy(id = "cartur")
	private WebElement cartLink;
	@FindBy(css = "a#login2")
	private WebElement loginLink;
	@FindBy(id = "signin2")
	private WebElement signUpLink;
	@FindBy(css = ".navbar-nav .nav-link")
	private List<WebElement> navBarLinks;
	@FindBy(css = "span.carousel-control-next-icon")
	private WebElement nextButton;
	@FindBy(css = "span.carousel-control-prev-icon")
	private WebElement previousButton;
	@FindBy(css = ".carousel-item.active img")
	private WebElement activeImage;
	@FindBy(className = "list-group-item")
	private List<WebElement> sidebarLinks;
	@FindBy(css = "div.card a[href]")
	private List<WebElement> productLinks;
	@FindBy(id = "prev2")
	private WebElement prevButton;
	@FindBy(id = "next2")
	private WebElement nextBtn;
	@FindBy(css = "footer.py-5.bg-inverse")
	private WebElement footer;
	@FindBy(css = "div.col-sm-4.col-lg-4.col-md-4 .caption h4.grrrr")
	private WebElement aboutUsSection;
	@FindBy(css = "div.col-sm-4.col-lg-4.col-md-4 .caption p")
	private WebElement aboutUsText;
	@FindBy(css = "div.col-sm-3.col-lg-3.col-md-3 .caption h4.grrrr")
	private WebElement getInTouchSection;
	@FindBy(xpath = "//div[contains(@class, 'col-sm-3')]/div/div/p[1]")
	private WebElement address;
	@FindBy(xpath = "//div[contains(@class, 'col-sm-3')]/div/div/p[2]")
	private WebElement phone;
	@FindBy(xpath = "//div[contains(@class, 'col-sm-3')]/div/div/p[3]")
	private WebElement email;
	@FindBy(css = "div.col-sm-4.col-lg-4.col-md-4 .caption img")
	private WebElement logo;
	@FindBy(xpath = "//div[@class='caption']/h4[contains(text(),'PRODUCT STORE')]")
	private WebElement productStoreText;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void display() {
		System.out.println(logoImage.isDisplayed());
		System.out.println(brandLink.isDisplayed());
		System.out.println(brandLink.getText());
		System.out.println(homeLink.isDisplayed());
		System.out.println(homeLink.getText());
		System.out.println(contactLink.isDisplayed());
		System.out.println(contactLink.getText());
		System.out.println(aboutUsLink.isDisplayed());
		System.out.println(aboutUsLink.getText());
		System.out.println(cartLink.isDisplayed());
		System.out.println(cartLink.getText());
		System.out.println(loginLink.isDisplayed());
		System.out.println(loginLink.getText());
		System.out.println(signUpLink.isDisplayed());
		System.out.println(signUpLink.getText());
	}

	public void testHyperlinksClickable() {

		for (WebElement link : navBarLinks) {
			if (link.isDisplayed() && link.isEnabled()) {
				System.out.println("Clickable link: " + link.getText() + " - " + link.getAttribute("href"));
			} else {
				System.out.println("Link is not clickable: " + link.getText());
			}
		}
	}

	public void slideShowNext() {
		for (int i = 0; i < 3; i++) {
			nextButton.click();
			System.out.println("Clicked next button " + (i + 1) + " times.");
		}
	}

	public void slideShowPrev() {
		for (int i = 0; i < 3; i++) {
			previousButton.click();
			System.out.println("Clicked previous button " + (i + 1) + " times.");
		}
	}

	public void clickAndCheckImage() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			nextButton.click();
			System.out.println("Clicked next button " + (i + 1) + " times.");
			Thread.sleep(1000);
			if (activeImage.isDisplayed()) {
				System.out.println("Image is displayed after click " + (i + 1));
			} else {
				System.out.println("Image is NOT displayed after click " + (i + 1));
			}
		}
	}

	public void sideNavBar() {
		String originalTab = driver.getWindowHandle();
		for (WebElement link : sidebarLinks) {
			String href = link.getAttribute("href");
			if (link.isDisplayed() && link.isEnabled()) {
				System.out.println("Link: " + href + " is clickable.");
				((JavascriptExecutor) driver).executeScript("window.open('" + href + "','_blank');");
				Set<String> windowHandles = driver.getWindowHandles();
				for (String handle : windowHandles) {
					if (!handle.equals(originalTab)) {
						driver.switchTo().window(handle);
						break;
					}
				}
				System.out.println("Opened page: " + driver.getTitle());
				driver.close();
				driver.switchTo().window(originalTab);
			} else {
				System.out.println("Link: " + href + " is not clickable.");
			}
		}
	}

	public void checkProductLinks() throws InterruptedException {
		List<WebElement> pl = wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
		System.out.println(pl.size());
		for (WebElement link : pl) {
			String originalWindow = driver.getWindowHandle();
			String linkHref = link.getAttribute("href");
			((JavascriptExecutor) driver).executeScript("window.open(arguments[0]);", linkHref);
			Thread.sleep(5000);
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				if (!window.equals(originalWindow)) {
					driver.switchTo().window(window);
					break;
				}
			}
			System.out.println("Opened: " + driver.getCurrentUrl());
			driver.close();
			driver.switchTo().window(originalWindow);
		}
	}

	public void buttonCheck() {
		if (nextBtn.isEnabled()) {
			System.out.println("Next button is clickable.");
			nextBtn.click();
		} else {
			System.out.println("Next button is not clickable.");
		}
		if (prevButton.isEnabled()) {
			System.out.println("Previous button is clickable.");
			prevButton.click();
		} else {
			System.out.println("Previous button is not clickable.");
		}
	}

	public void footerCheck() {

		if (aboutUsSection.isDisplayed()) {
			System.out.println("About Us section is displayed.");
			System.out.println("About Us text: " + aboutUsText.getText());
		} else {
			System.out.println("About Us section is not displayed.");
		}

		if (getInTouchSection.isDisplayed()) {
			System.out.println("Get in Touch section is displayed.");
			System.out.println("Address: " + address.isDisplayed() + address.getText());
			System.out.println("Phone: " + phone.isDisplayed() + phone.getText());
			System.out.println("Email: " + email.isDisplayed() + email.getText());
		} else {
			System.out.println("Get in Touch section is not displayed.");
		}
		if (logo.isDisplayed()) {
			System.out.println("Logo image is displayed.");
			if (productStoreText.getText().contains("PRODUCT STORE")) {
				System.out.println("PRODUCT STORE text is displayed beside the logo.");
			} else {
				System.out.println("PRODUCT STORE text is not displayed correctly.");
			}
		} else {
			System.out.println("Logo image is not displayed.");
		}
	}

	public void copyRightCheck() {

		String footerText = footer.getText();
		if (footerText.contains("Copyright © Product Store")) {
			System.out.println("Copyright content is correctly provided in the footer.");
		} else {
			System.out.println("Copyright content is missing or incorrect in the footer.");
		}
	}

}
