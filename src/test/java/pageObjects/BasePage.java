package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


// Constructor Re-Useablity:
// we need condtructor for every pageObjects Classes. 
// This is the Constructor Class commonly available for all PageObject Classes.
// This will be invoked by every page Object Class Constructor.


public class BasePage 
{
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

}
