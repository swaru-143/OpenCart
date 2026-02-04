package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtCnfPassword;
	
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkbxPolicy;
	
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	
	@FindBy(xpath="//div[@id='content']/h1")
	WebElement msgconfirmation;
	
	

	
	//Action Methods
	
	
	public void setFirstName(String fName)
	{
		txtFirstName.sendKeys(fName);
	}
	
	
	public void setLastName(String lName)
	{
		txtLastName.sendKeys(lName);
	}
	
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	
	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	
	public void setCnfPassword(String cnfpwd)
	{
		txtCnfPassword.sendKeys(cnfpwd);
	}
	
	
	public void setPrivacyPolicy()
	{
		chkbxPolicy.click();
	}
	
	
	public void ClickContinue()
	{
		btnContinue.click();                  
	}
	
	
	// SomeTime Click()-not works so, in that case we try with different approach
	
	/*
	
	public void ClickSubmit()
	{
		btnContinue.submit();                   //---->pre-defined action
		
		btnContinue.sendKeys(Keys.RETURN);      //---->KeyBoard Action.
		
		Actions act=new Actions(driver);
		act.moveToElement(btnContinue).click().perform();    //---->Mouse Action
		
		
		JavascriptExecutor js= (JavascriptExecutor) driver;        //---->JavascriptExecutor
		js.executeScript("arguments[0].click();", btnContinue);
		
		
		WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue).click());
			
		
	}
	
	*/	
	
	
	
	
	public String getConfirmationMsg()
	{
		try 
		{
			return msgconfirmation.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
