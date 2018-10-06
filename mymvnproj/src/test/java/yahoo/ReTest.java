package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ReTest extends MainClass
{
  //DesiredCapabilities ds;
  @Test
  @Parameters({"browser"})
  public void retesting(String br) throws Exception
  {
	  if(br.matches("firefox"))
	  {		
		   driver=new FirefoxDriver();	
		 // ds=DesiredCapabilities.firefox();
		 // ds.setPlatform(Platform.WINDOWS);
	  }
	  if(br.matches("chrome"))
	  {
		driver=new ChromeDriver();	
		 // ds=DesiredCapabilities.chrome();
		  //ds.setPlatform(Platform.WINDOWS);
	  }	
	 // driver=new RemoteWebDriver(new URL("http://192.168.1.120:1234/wd/hub"), ds);
	  FileInputStream fin=new FileInputStream("d:\\sdet_aug2018\\testdata.xlsx");  //get the file
	  XSSFWorkbook wb=new XSSFWorkbook(fin);  //in the file get workbook
	  XSSFSheet ws=wb.getSheet("retest");   //in the workbook get the sheet1
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


	 











