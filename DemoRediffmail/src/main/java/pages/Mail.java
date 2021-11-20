package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mail {
	WebDriver driver;
	private By writemail = By.xpath("//b[contains(text(),'Write mail')]");
	private By to = By.xpath("//input[@id='TO_IDcmp2']");
	private By subj = By.xpath("//body/div[4]/div[2]/div[2]/div[2]/div[2]/ul[2]/li[2]/div[1]/div[1]/ul[1]/li[4]/input[1]");
	private By frame= By.xpath("//body/div[4]/div[2]/div[2]/div[2]/div[2]/ul[2]/li[2]/div[1]/div[1]/ul[1]/li[6]/div[1]/div[1]/div[1]/iframe[1]");
	private By body= By.xpath("//body");
	private By send=By.xpath("//body/div[4]/div[2]/div[2]/div[2]/div[2]/ul[2]/li[2]/div[1]/div[1]/div[1]/a[1]");

	public Mail(WebDriver driver) {
		this.driver = driver;
	}

	public void doComposeAs(String mailto, String subject,String message) {
		try {
			isElement(writemail).click();
			isElement(to).sendKeys(mailto);
			isElement(subj).sendKeys(subject);
			driver.switchTo().frame(driver.findElement(frame));
			Thread.sleep(10000);
			isElement(body).sendKeys(message);
			driver.switchTo().defaultContent();
			isElement(send).click();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public  WebElement isElement(By by) {
		WebDriverWait wt=new WebDriverWait(driver,30);
		WebElement ele=null;
		boolean flag=wt.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
		for(int i=0;i<10;i++) {
			if(flag) {
				ele=driver.findElement(by);
				break;
			}
			else {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}
		return ele;
		
	}
}
