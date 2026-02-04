package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	
	
	@Test(groups= {"Sanity", "Master"})
	public void Verify_account_registration()
	{
		
		logger.info("***** Starting TC_001_AccountRegistrationTest *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.ClickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		hp.ClickRegister();
		logger.info("Clicked on Register Link...");
		
		
		AccountRegistrationPage register=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details...");
		
		register.setFirstName(randomString().toUpperCase());
		register.setLastName(randomString().toUpperCase());
		
		register.setEmail(randomString()+"@gmail.com");
		register.setTelephone(randomNumber());
		
		 String password=randomAlphaNumeric();
		
		register.setPassword(password);
		register.setCnfPassword(password);
		
		register.setPrivacyPolicy();
		register.ClickContinue();
		
		
		logger.info("Validating Expected Message...");
	
		String ConfirmMsg=register.getConfirmationMsg();
		if(ConfirmMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		 
		//Assert.assertEquals(ConfirmMsg, "Your Account Has Been Created!!!"); --->Not needed.
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("***** Finished TC_001_AccountRegistrationTest *****");
		
	}
	

}
