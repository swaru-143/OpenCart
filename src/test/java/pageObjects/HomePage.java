package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	
	public HomePage(WebDriver driver)                  //----> using constructor from BasePage Class by extends.
	{
		super(driver);
	}
	
	
	//Locators
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement lnkMyaccount;
	
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	
	
	//Action Methods
	
	public void ClickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	
	public void ClickRegister()
	{
		lnkRegister.click();
	}
	
	
	public void ClickLogin()
	{
		lnkLogin.click();
	}
	
	
	

}
