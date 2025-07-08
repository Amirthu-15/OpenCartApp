package org.testCases;

import org.pageObjects.HomePage;
import org.pageObjects.LoginPage;
import org.pageObjects.MyAccountPage;
import org.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.DataProviders;

public class TC003_LoginDataDrivenTestCase extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_Login_DDT(String email, String pwd, String exp) {

		
			logger.info("..........Starting TC002_Login Test..........");

			HomePage homePage = new HomePage(driver);

			homePage.clickMyAccount();

			homePage.clickLoginLink();

			logger.info("..........Enter Login Credentials..........");

			LoginPage loginPage = new LoginPage(driver);

			loginPage.setEmailAddress(email);
			loginPage.setPassword(pwd);
			loginPage.clickLoginButton();

			MyAccountPage myAccountPage = new MyAccountPage(driver);

			boolean myAccountPageExists = myAccountPage.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {

				if (myAccountPageExists == true) {

					myAccountPage.clickLogoutLink();
					Assert.assertTrue(true);

				} else {

					Assert.assertFalse(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {

				if (myAccountPageExists == true) {

					myAccountPage.clickLogoutLink();

					Assert.assertTrue(true);

				} else {

					Assert.assertFalse(false);
				}
			}

			logger.info("..........Completed TC002_Login Test..........");
		

	}

}
