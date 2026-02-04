package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;



public class DataProviders 
{
	
	// DataProvider-1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testdata\\OpenCart_LoginData.xlsx";  //--->taking xl file from testData folder.
		
		ExcelUtility xlutil=new ExcelUtility(path);  //--->creating an object for ExcelUtility Class.
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata [][]=new String [totalrows][totalcols];  //--->created for two dimension array which can store 
		
		for(int i=1; i<=totalrows; i++)   //--->Read the data from xl & storing in two dimensional array.
			                                 //---> "1"-- Header part we dont read, so we skiped 0th row in xl.
			for(int j=0; j<totalcols; j++)   //---> "0"-- Always Starts from zero. -Coloumn
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);  //---> Array Index starts from "0", so we should not waste 0th position in Array Index. 
			}
		
		return logindata;
	}

}
