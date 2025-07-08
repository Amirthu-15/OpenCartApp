package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountHeading;

	public boolean isMyAccountPageExists() {

		try {
			return myAccountHeading.isDisplayed();

		} catch (Exception e) {

			return false;
		}

	}

	@FindBy(xpath = "//a[@class='list-group-item'] [text()='Logout']")
	WebElement logoutLink;

	public void clickLogoutLink() {

		logoutLink.click();

	}
}
