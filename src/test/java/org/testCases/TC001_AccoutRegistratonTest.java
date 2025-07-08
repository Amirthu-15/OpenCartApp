package org.testCases;

import org.pageObjects.AccRegistrationPage;
import org.pageObjects.HomePage;
import org.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_AccoutRegistratonTest extends BaseClass {

	public static HomePage homePage;

	public static AccRegistrationPage accRegistrationPage;

	@Test(groups= {"Regression", "Master"})
	public void verifyAccountRegistration() {

		try {
			logger.info("***** Starting TC001_AccoutRegistratonTest");

			homePage = new HomePage(driver);

			logger.info("Clicked on MyAccount Link");

			homePage.clickMyAccount();

			logger.info("Clicked on Registration Link");

			homePage.clickRegisterLink();

			accRegistrationPage = new AccRegistrationPage(driver);

			logger.info("Providing Customer Details");

			accRegistrationPage.setFirstName(randomString().toUpperCase());

			accRegistrationPage.setLastName(randomString().toUpperCase());

			accRegistrationPage.setEmail(randomAlphaNumeric() + "@gmail.com");

			accRegistrationPage.setTelephoneumber(randomNumber());

			String password = randomAlphaNumeric();

			accRegistrationPage.setPassword(password);

			accRegistrationPage.setConfirmPassword(password);

			accRegistrationPage.clickPrivacyCheckBox();

			accRegistrationPage.clickContinueButton();

			logger.info("Validating Expected Message");

			String confirmMsg = accRegistrationPage.getConfirmMsg();

			Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");

		} catch (Exception e) {

			logger.error("Test Failed...");

			logger.debug("Debug logs...");

			Assert.fail();
		}

	}

}
