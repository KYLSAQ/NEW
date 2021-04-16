import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Get_carousel {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:\\Users\\tutul\\Desktop\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.noon.com/uae-en/");
		driver.manage().window().maximize();
		Get_carousel carousel = new Get_carousel();
		
		 driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		 //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("return document.readyState").toString().equals("complete");
		
		List<WebElement> element = driver.findElements(By.xpath("//div[@class ='sc-1pz9vus-1 iGMThe']//h3[@class='sc-dlfnbm eFXxew']"));
		js.executeScript("scroll(107, 600);");
		carousel.getCarouselItemsSorted("Recommended For You",driver);
		driver.quit();
	}
	
	
	public ArrayList<String> getCarouselItemsSorted(String section_name,WebDriver driver ){
		ArrayList<String> sortedList = new ArrayList(); 
		
		try{
		
		WebElement next_click= driver.findElement(By.xpath("//*[h3 ='"+section_name+"']/parent::div[@class='sc-fvhGYg kHEClt']/parent::div//div[starts-with(@class, 'swiper-button-next custom-navigation swiper-nav-16')]"));
		List<WebElement> element1 = null;
		ArrayList<String> obtainedList = new ArrayList(); 
		while(next_click.isDisplayed()){
			
			//JavascriptExecutor executor = (JavascriptExecutor)driver;
			//executor.executeScript("arguments[0].click();", next_click);
			
			Actions actions = new Actions(driver);
			actions.moveToElement(next_click).click().perform();
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			element1 = driver.findElements(By.xpath("//*[h3 ='"+section_name+"']/parent::div[@class='sc-fvhGYg kHEClt']/parent::div//div[@class='swiper-wrapper']//div[@class='swiper-slide']//div[@class='e3js0d-11 liZdac']"));
			 
			for(WebElement we : element1){
				   obtainedList.add(we.getText());
					}
			}
			for(String s:obtainedList){
				if(s.isEmpty())
					continue;
				else{
					sortedList.add(s);
					}
			}
		Collections.sort(sortedList,String.CASE_INSENSITIVE_ORDER);
		for(int j =0 ; j<sortedList.size();j++){
			System.out.println(sortedList.get(j));
		}
		System.out.println(sortedList.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return sortedList;
	}
}



