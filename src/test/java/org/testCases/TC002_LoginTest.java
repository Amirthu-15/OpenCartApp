package org.testCases;

import java.io.IOException;

import org.pageObjects.HomePage;
import org.pageObjects.LoginPage;
import org.pageObjects.MyAccountPage;
import org.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginTest extends BaseClass {

	
	
	@Test(groups= {"Sanity", "Master"})
	public void verifyLogin() throws IOException, InterruptedException {
		
		logger.info("..........Starting TC002_Login Test..........");
		
		HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
				
		homePage.clickLoginLink();
				
		logger.info("..........Enter Login Credentials..........");

		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setEmailAddress(readDataFromPropertiesFile("email"));
				
		loginPage.setPassword(readDataFromPropertiesFile("password"));
				
		loginPage.clickLoginButton();
				
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		
		boolean myAccountPageExists = myAccountPage.isMyAccountPageExists();
		
		Assert.assertEquals(myAccountPageExists, true);
		
		logger.info("..........Completed TC002_Login Test..........");

	}
}
