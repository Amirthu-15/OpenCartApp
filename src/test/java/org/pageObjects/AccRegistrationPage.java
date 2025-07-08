package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccRegistrationPage extends BasePage {

	public AccRegistrationPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(id = "input-firstname")
	WebElement firstName;

	public void setFirstName(String keysToSend) {

		firstName.sendKeys(keysToSend);
	}

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;

	public void setLastName(String keysToSend) {

		lastName.sendKeys(keysToSend);
	}

	@FindBy(xpath = "//input[@type='email']")
	WebElement emailBox;

	public void setEmail(String keysToSend) {

		emailBox.sendKeys(keysToSend);

	}

	@FindBy(xpath = "//input[@placeholder='Telephone']")
	WebElement telNumber;

	public void setTelephoneumber(String keysToSend) {

		telNumber.sendKeys(keysToSend);

	}

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordBox;

	public void setPassword(String keysToSend) {

		passwordBox.sendKeys(keysToSend);

	}

	@FindBy(xpath = "//input[@placeholder='Password Confirm']")
	WebElement confirmPasswordBox;

	public void setConfirmPassword(String keysToSend) {

		confirmPasswordBox.sendKeys(keysToSend);

	}

	@FindBy(xpath = "//input[@name='agree']")
	WebElement clickPrivacyBox;

	public void clickPrivacyCheckBox() {

		clickPrivacyBox.click();

	}

	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueButton;

	public void clickContinueButton() {

		continueButton.click();

	}
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement registrationConfirmMsg;
	
	public String getConfirmMsg() {

		String text = registrationConfirmMsg.getText();
		
		return text;

	}
}
