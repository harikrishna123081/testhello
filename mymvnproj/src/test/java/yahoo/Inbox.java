package yahoo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Inbox extends MainClass
{
 public void deletemail() throws Exception
 {
	 List<WebElement> lst=driver.findElements(By.xpath("//input[@name='mid']"));
	 lst.get(3).click();
	 Thread.sleep(5000);
	 driver.findElement(By.id("top_delete")).click();
 }
 
}
