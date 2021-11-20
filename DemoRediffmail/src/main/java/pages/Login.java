package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Login {

	WebDriver driver;
	private By userbox = By.xpath("//input[@id='login1']");
	private By passbox = By.xpath("//input[@id='password']");
	private By loginbtn = By.xpath("//input[@name='proceed']");
	private String url = "https://mail.rediff.com/cgi-bin/login.cgi";
	private String expectedTitle = "Rediffmail";

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void doLoginAs(String user, String pass) {
		try {
			driver.findElement(userbox).sendKeys(user);
			driver.findElement(passbox).sendKeys(pass);
			driver.findElement(loginbtn).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void assertTitle() {
		Assert.assertEquals(expectedTitle, driver.getTitle());
	}

	public void verifyTitle() {
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(expectedTitle, driver.getTitle());
		sa.assertAll();
	}

	public void openUrl() {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void closePage() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void skiplogin() {
		if(driver.findElement(By.linkText("Skip,Go to Inbox")).isDisplayed()) {
			driver.findElement(By.linkText("Skip,Go to Inbox")).click();
		}
	}
}
