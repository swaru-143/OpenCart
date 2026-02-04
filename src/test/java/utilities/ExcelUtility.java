package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	
	// Utility Files For Excel
	
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook workbook;
		public static XSSFSheet sheet;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;
	    String path;
		
		
		public ExcelUtility(String path)      //---->Common Constructor path.
		{
			this.path=path;
		}
		
		
		// Excel Utility Files
		
		// User-Defined Method for total "RowCount" in a Excel.
		
		public int getRowCount(String sheetName) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			int RowCount=sheet.getLastRowNum();
			workbook.close();
			fi.close();
			
			return RowCount;
		}	
			
		
		// User-Defined Method for total "CellCount" or Columns in a Excel.
			
		public int getCellCount(String sheetName, int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int CellCount=row.getLastCellNum();
			workbook.close();
			fi.close();
			
			return CellCount;
			
		}
		
		
		// User-defined Method to read data from "perticular Cell" in Excel.
		
		public String getCellData(String sheetName, int rownum, int colnum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(colnum);
			
			String data;
			
			try
			{
			// data=cell.toString(); // Always not Recommended
				
				DataFormatter formatter=new DataFormatter();  // Recommended & Provided by Apache POI
				data=formatter.formatCellValue(cell);  
				
			}
			catch(Exception e)
			{
				data="";
			}
			
			workbook.close();
			fi.close();
			return data;
			
		}
			
			
		// User-defined Method for Setting "CellValue" in Excel.
		
		    public void setCellValue(String sheetName, int rownum, int colnum, String data) throws IOException
		    {
		    	
		    	File xlfile=new File(path);
		    	if(!xlfile.exists())         // If file not exists then create new file.
		    	{
		    		workbook=new XSSFWorkbook();
		    		fo=new FileOutputStream(path);
		    		workbook.write(fo);
		    	}
		    	
		    		
		    	fi=new FileInputStream(path);
		    	workbook=new XSSFWorkbook(fi);
		    	
		    	if(workbook.getSheetIndex(sheetName)==-1)   //---> If Sheet not exists then create new Sheet.
		    		workbook.createSheet(sheetName);	
		    	sheet=workbook.getSheet(sheetName);
		    	
		    	if(sheet.getRow(rownum)==null)     //--->If row not exists then create new Row.
		    		sheet.createRow(rownum);
		    	row=sheet.getRow(rownum);
		    	
		    	cell=row.createCell(colnum);
		    	cell.setCellValue(data);
		    	
		    	fo=new FileOutputStream(path);
		    	workbook.write(fo);
		    	
		    	workbook.close();
		    	fi.close();
		    	fo.close();
		    	
		    	
		    }
		    
		    
		 // User-defined Method for Set or Fill "GreenColour" to a perticular Cell in Excel. 
		    
		    public void fillGreenColour(String sheetName, int rownum, int colnum) throws IOException
		    {
		    	fi=new FileInputStream(path);
		    	workbook=new XSSFWorkbook(fi);
		    	sheet=workbook.getSheet(sheetName);
		    	
		    	row=sheet.getRow(rownum);
		    	cell=row.getCell(colnum);
		    	
		    	style=workbook.createCellStyle();
		    	
		    	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    	
		    	cell.setCellStyle(style);
		    	
		    	//fo=new FileOutputStream(path);
		    	workbook.write(fo);
		    	
		    	workbook.close();
		    	fi.close();
		    	fo.close();
		    	
		    }
		    
		    
		 // User-defined Method for Set or Fill "RedColour" to a perticular Cell in Excel. 
		    
		    public void fillRedColour(String sheetName, int rownum, int colnum) throws IOException
		    {
		    	fi=new FileInputStream(path);
		    	workbook=new XSSFWorkbook(fi);
		    	sheet=workbook.getSheet(sheetName);
		    	row=sheet.getRow(rownum);
		    	cell=row.getCell(colnum);
		    	
		    	style=workbook.createCellStyle();
		    	
		    	style.setFillForegroundColor(IndexedColors.RED.getIndex());
		    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    	
		    	cell.setCellStyle(style);
		    	
		    	//fo=new FileOutputStream(path);
		    	workbook.write(fo);
		    	
		    	workbook.close();
		    	fi.close();
		    	fo.close();
		    	
		    }
	
	
	
	

}
