package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass
{
	
	//-->Data Driven Test from Excel-File
	
	
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class , groups= {"DataDriven"})
	public void Verify_Login_DDT(String email, String pwd, String exp)
	{
		
        logger.info("***** Starting TC_002_LoginTest *****");
		
	    
        try
        {
        	
        	if (driver.getPageSource().contains("Logout"))
            {
                MyAccountPage map = new MyAccountPage(driver);
                map.ClickLogout();
            }
        	
        	
	    //HomePage
	    HomePage hp=new HomePage(driver);
	
		hp.ClickMyAccount();
		hp.ClickLogin();
	
	
	
	    //LoginPage
	    LoginPage lp=new LoginPage(driver);
	
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.ClickLogin();
		
		
	
	
	
	    //MyAccountPage
	    MyAccountPage map=new MyAccountPage(driver);
	    boolean targetPage=map.isMyAccountPageExists();
	    
	    if(exp.equalsIgnoreCase("Valid"))
	    {
	    	if(targetPage==true)
	    	{
	    		map.ClickLogout();
	    		Assert.assertTrue(true);
	    	}
	    	else
	    	{
	    		Assert.assertTrue(false);
	    	}
	    }
	    
	    
	    if(exp.equalsIgnoreCase("Invalid"))
	    {
	    	if(targetPage==true)
	    	{
	    		map.ClickLogout();
	    		Assert.assertTrue(false);
	    	}
	    	else
	    	{
	    		Assert.assertTrue(true);
	    	}
	    }
	    
        }
        catch(Exception e)
        {
        	Assert.fail();
        }
	    
	
	    logger.info("***** Finished TC_002_LoginTest *****");	
		
		
        }

}
