package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testbase.BasePage;

public class AboutUsPage extends BasePage {
	@FindBy(linkText = "About us")
	private WebElement aboutUsLink;
	@FindBy(id = "videoModalLabel")
	private WebElement videoModal;
	@FindBy(className = "vjs-big-play-button")
	private WebElement playButton;
	@FindBy(css = ".vjs-mute-control")
	private WebElement volumeButton;
	@FindBy(css = ".vjs-volume-bar")
	private WebElement volumeBar;
	@FindBy(css = ".vjs-picture-in-picture-control")
	private WebElement pipButton;
	@FindBy(css = ".vjs-fullscreen-control")
	private WebElement fullscreen;
	@FindBy(id = "example-video_html5_api")
	private WebElement videoElement;
	@FindBy(css = ".vjs-play-control")
	private WebElement playPause;
	@FindBy(xpath = "//*[@id=\"videoModal\"]/div/div/div[3]/button")
	private WebElement Conclude;

	public AboutUsPage() {
		PageFactory.initElements(driver, this);
	}

	public void aboutLink() throws InterruptedException {
		aboutUsLink.click();
		WebElement modalLabel = wait.until(ExpectedConditions.visibilityOf(videoModal));
		System.out.println(modalLabel.getText());
		WebElement play = wait.until(ExpectedConditions.elementToBeClickable(playButton));
		play.click();
		WebElement volume = wait.until(ExpectedConditions.elementToBeClickable(volumeButton));
		volume.click();
		actions.clickAndHold(volumeBar).moveByOffset(-50, 0).release().perform();
		actions.clickAndHold(volumeBar).moveByOffset(50, 0).release().perform();
		WebElement pBtn = wait.until(ExpectedConditions.elementToBeClickable(pipButton));
		pBtn.click();
		Thread.sleep(3000);
		pBtn.click();
		WebElement fullscreenButton = wait.until(ExpectedConditions.elementToBeClickable(fullscreen));
		fullscreenButton.click();
		Thread.sleep(3000);
		fullscreenButton.click();
		actions.moveToElement(videoElement).perform();
		Thread.sleep(2000);
		WebElement playPauseButton = wait.until(ExpectedConditions.elementToBeClickable(playPause));
		String title = playPauseButton.getAttribute("title");
		if ("Pause".equals(title)) {
			playPauseButton.click();
		}
		String newTitle = playPauseButton.getAttribute("title");
		System.out.println("Button title after click: " + newTitle);

		WebElement concludeButton = wait.until(ExpectedConditions.elementToBeClickable(Conclude));
		concludeButton.click();
	}
}
