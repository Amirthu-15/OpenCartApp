package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailAddress;

	public void setEmailAddress(String keysToSend) {

		emailAddress.sendKeys(keysToSend);
	}

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	public void setPassword(String keysToSend) {

		password.sendKeys(keysToSend);
	}

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;

	public void clickLoginButton() {

		loginButton.click();

	}

}
