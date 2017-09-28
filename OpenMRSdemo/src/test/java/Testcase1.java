
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testcase1 {
WebDriver driver;
	

String username="Admin";
String password="Admin123";
String givenname="John";
String middlename="K";
String familyName="Della";
String gen="Male";
String day="10";
String mon="September";
String year="1978";
String address1="1201 Woodlands dr";
String address2="Suite 567";
String city="Spring";
String state="TX";
String country="USA";
String zip="77896";
String number="7458961236";
String relation="Parent";
String relative="Karen";
String editgivenname="Johan";
String edityear="1989";




    @BeforeClass
    public void setup()
    {
    	//Set the bowser type
		  //System.setProperty("webdriver.IE.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
		  driver=new ChromeDriver();	
		  
		  //Go to the site
		  driver.get("https://demo.openmrs.org/openmrs/login.htm");
    	
    }
    @AfterClass
    public void closeBrowser()
    {
    	
    	driver.quit();
    }
	
	
	public boolean Login(String username,String password)
	{
		  		  
		  //Enter username , password,select Inpatient ward and click on Login
		  driver.findElement(By.id("username")).sendKeys(username);
		  driver.findElement(By.id("password")).sendKeys(password);
		  driver.findElement(By.id("Inpatient Ward")).click();
		  
		  driver.findElement(By.id("loginButton")).click();
		return true;
		   
		 	
	}
	
	
	public boolean Register(String givenname,String middlename,String familyName,String gen,String day,String mon,String year,String address1,String address2,String city,String state,String country,
			String zip,String number,String relation,String relative)
	{
		
		 driver.findElement(By.linkText("Register a patient")).click();
		 driver.findElement(By.name("givenName")).sendKeys(givenname);
		 driver.findElement(By.name("middleName")).sendKeys(middlename); 
		 driver.findElement(By.name("familyName")).sendKeys(familyName); 
		 
		 driver.findElement(By.xpath(".//*[@id='genderLabel']")).click();
		 
		 Select gender = new Select(driver.findElement(By.id("gender-field")));
		 gender.selectByVisibleText(gen);
		 //gender.deselectByIndex(index);
		 
		 driver.findElement(By.id("birthdateLabel")).click();
		 driver.findElement(By.id("birthdateDay-field")).sendKeys(day);
		 
		 Select month = new Select(driver.findElement(By.id("birthdateMonth-field")));
		 month.selectByVisibleText(mon);
		 		 
		 driver.findElement(By.id("birthdateYear-field")).sendKeys(year);
		 driver.findElement(By.xpath("//span[text()='Address']")).click();

		 driver.findElement(By.id("address1")).sendKeys(address1);
		 driver.findElement(By.id("address2")).sendKeys(address2);
		 driver.findElement(By.id("cityVillage")).sendKeys(city);
		 driver.findElement(By.id("stateProvince")).sendKeys(state);
		 driver.findElement(By.id("country")).sendKeys(country);
		 driver.findElement(By.id("postalCode")).sendKeys(zip);
		 driver.findElement(By.xpath("//span[text()='Phone Number']")).click();
		 driver.findElement(By.name("phoneNumber")).sendKeys(number);
		 driver.findElement(By.xpath("//span[text()='Relatives']")).click();
		 
		 Select relationship = new Select(driver.findElement(By.id("relationship_type")));
		 relationship.selectByVisibleText(relation);
		 driver.findElement(By.xpath("//section[@id='relationships-info']//div//input[1]")).sendKeys(relative);		 
		 driver.findElement(By.id("confirmation_label")).click();
		 driver.findElement(By.id("submit")).click();
		return true;
		 
	}
	
	public boolean Edit(String editgivenname,String edityear)
	{
		 driver.findElement(By.linkText("   Edit ")).click();
		 driver.findElement(By.name("givenName")).sendKeys(editgivenname);
		 driver.findElement(By.id("birthdateLabel")).click();
		 driver.findElement(By.id("birthdateYear-field")).sendKeys(edityear);
		 driver.findElement(By.id("confirmation_label")).click();
		 driver.findElement(By.id("registration-submit")).click();
		return true;
		    
	}
	
	
	@Test(priority=1)
	public void Logintoapp()
	{
		
		   boolean result = Login("Admin","Admin123");
		   Assert.assertTrue(result);
	}
	
	@Test(priority=2)
	public void RegisterPatient()
	{
		boolean result = Register(givenname, middlename, familyName, gen, day, mon, year, address1, address2, city, state, country, zip, number, relation, relative);
		 Assert.assertTrue(result);
		
		
	}
	@Test(priority=3)
	public void editPatient()
	{
		boolean result =Edit(editgivenname, edityear);
		Assert.assertTrue(result);
		
	}
}
