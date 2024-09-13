package testscripts;

import org.testng.annotations.Test;

import testbase.BasePage;

public class HomeTest extends BasePage {
	@Test(priority = 1)
	public void dispCheck() {
		hp.display();
	}

	@Test(priority = 2)
	public void HyperLinksCheck() {
		hp.testHyperlinksClickable();
	}

	@Test(priority = 3)
	public void slideShowCheck() {
		hp.slideShowNext();
		hp.slideShowPrev();
	}

	@Test(priority = 4)
	public void imageCheck() throws InterruptedException {
		hp.clickAndCheckImage();

	}

	@Test(priority = 5)
	public void sideNavBarCheck() {
		hp.sideNavBar();

	}

	@Test(priority = 6)
	public void productLinksCheck() throws InterruptedException {
		hp.checkProductLinks();

	}

	@Test(priority = 7)
	public void btnCheck() {
		hp.buttonCheck();

	}

	@Test(priority = 8)
	public void footer() {
		hp.footerCheck();

	}

	@Test(priority = 9)
	public void copyRight() {
		hp.copyRightCheck();

	}

}
