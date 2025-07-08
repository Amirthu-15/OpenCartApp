package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {

		super(driver);

	}
	

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerLink;

	@FindBy(how = How.XPATH, using = "//a[text()='Login']")
	WebElement loginLink;

	public void clickMyAccount() {

		myAccount.click();

	}

	public void clickRegisterLink() {

		registerLink.click();

	}

	public void clickLoginLink() {

		loginLink.click();

	}

}
