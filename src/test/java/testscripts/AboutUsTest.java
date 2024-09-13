package testscripts;

import org.testng.annotations.Test;

import testbase.BasePage;

public class AboutUsTest extends BasePage {
	@Test(priority = 1)
	public void aboutUsCheck() throws InterruptedException {
		ap.aboutLink();
	}
}
