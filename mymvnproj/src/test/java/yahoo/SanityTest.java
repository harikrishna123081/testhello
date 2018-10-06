package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SanityTest extends MainClass
{
	@Test
	@Parameters({"browser"})
	public void sanityTesting(String br) throws Exception
	{
		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(br.matches("chrome"))
		{
			driver=new ChromeDriver();		
		}
		FileInputStream fin=new FileInputStream("d:\\sdet_aug2018\\testdata.xlsx");  //get the file
		  XSSFWorkbook wb=new XSSFWorkbook(fin);  //in the file get workbook
		  XSSFSheet ws=wb.getSheet("sanitytest");   //in the workbook get the sheet1
		  Row row;
		  String classname,methodname;
		  
		  for(int r=1;r<=ws.getLastRowNum();r++)   //for all the rows in the sheet
		  {
		    row=ws.getRow(r);
		    if(row.getCell(5).getStringCellValue().matches("yes"))
		    {
		    	classname=row.getCell(3).getStringCellValue();
		    	methodname=row.getCell(4).getStringCellValue();
		    	Class c=Class.forName(classname);  //load the class into memory
				Method m=c.getMethod(methodname, null);  //get method in the class
				Object obj=c.newInstance();  //create instance for the class
				m.invoke(obj, null); 	
		    }
		  }
		  fin.close();	 
			 
	}
}
