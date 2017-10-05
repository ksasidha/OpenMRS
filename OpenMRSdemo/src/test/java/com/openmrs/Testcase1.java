package com.openmrs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Testcase1 {
	WebDriver driver;
  String id;

	@Parameters({"browser","url"})
	@BeforeClass
	public void setup(String browser,String url)
	{
		//Set the bowser type
		//System.setProperty("webdriver.IE.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
		selectBrowser(browser);
		//Go to the site
		geturl(url);
	}

	public void selectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.IE.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();		  
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.Chrome.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver=new ChromeDriver();		  
		}

	}

	public void geturl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}


	@AfterClass
	public void closeBrowser()
	{

		driver.quit();
		System.out.println("browser quit");
	}

	
	public boolean login(String username,String password) throws IOException
	{

		//Enter username , password,select Inpatient ward and click on Login
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		captureScreenshot(driver);
		
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		captureScreenshot(driver);
		
		driver.findElement(By.id("Inpatient Ward")).click();
		captureScreenshot(driver);
		
        driver.findElement(By.id("loginButton")).click();
        captureScreenshot(driver);
        
        WebElement verifyText=driver.findElement(By.xpath(".//*[@id='home-container']/h4[contains(text(),'Logged in as Super User () at Inpatient Ward.')]"));
        if(verifyText.isDisplayed())
   {
 	  
 	  System.out.println("Successfully logged in as Admin");
 	  return true;
   }
   else
   {
 	  System.out.println("Invalid Login");
 	  return false;
 	  
   }
         		


	}
 
	
	
	public void setPatientName(String givenname,String middlename,String familyName)
	{
		driver.findElement(By.linkText("Register a patient")).click();
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.name("givenName")).clear();
		driver.findElement(By.name("givenName")).sendKeys(givenname);
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.name("middleName")).clear();
		driver.findElement(By.name("middleName")).sendKeys(middlename); 
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.name("familyName")).clear();
		driver.findElement(By.name("familyName")).sendKeys(familyName); 
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setPatientGender(String gen) throws IOException
	{
		driver.findElement(By.xpath(".//*[@id='genderLabel']")).click();
		captureScreenshot(driver);

		Select gender = new Select(driver.findElement(By.id("gender-field")));
		gender.selectByVisibleText(gen);
		//gender.deselectByIndex(index);
		captureScreenshot(driver);
	}
	public void setPatientBirthDate(String dob)
	{

		driver.findElement(By.id("birthdateLabel")).click();
		try {
			captureScreenshot(driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat formatter=new SimpleDateFormat("MMMM/dd/yyyy");
		Date dt=new Date(dob);
		String date=formatter.format(dt);
		String[] date1=date.split("/");
		String day=date1[1];
		String mon=date1[0];
		String year=date1[2]; 
		
		driver.findElement(By.id("birthdateDay-field")).sendKeys(day);
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Select month = new Select(driver.findElement(By.id("birthdateMonth-field")));
		month.selectByVisibleText(mon);
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.id("birthdateYear-field")).sendKeys(year);
		driver.findElement(By.xpath("//span[text()='Address']")).click();
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setPatientAddress(String address1,String address2,String city,String state,String country,
			String zip,String number) throws IOException
	{

		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("address1")).sendKeys(address1);
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("address2")).clear();
		driver.findElement(By.id("address2")).sendKeys(address2);
		captureScreenshot(driver);
		
		driver.findElement(By.id("cityVillage")).clear();
		driver.findElement(By.id("cityVillage")).sendKeys(city);
		captureScreenshot(driver);
		
		driver.findElement(By.id("stateProvince")).clear();
		driver.findElement(By.id("stateProvince")).sendKeys(state);
		captureScreenshot(driver);
		
		driver.findElement(By.id("country")).clear();
		driver.findElement(By.id("country")).sendKeys(country);
		captureScreenshot(driver);
		
		driver.findElement(By.id("postalCode")).clear();
		driver.findElement(By.id("postalCode")).sendKeys(zip);
		captureScreenshot(driver);
		
		//driver.findElement(By.xpath("//span[text()='Phone Number']")).clear();
		driver.findElement(By.xpath("//span[text()='Phone Number']")).click();
		captureScreenshot(driver);
		
		driver.findElement(By.name("phoneNumber")).clear();
		driver.findElement(By.name("phoneNumber")).sendKeys(number);
		captureScreenshot(driver);
	}
	public void setPatientRelatives(String relation,String relative) throws IOException
	{
		
		driver.findElement(By.xpath("//span[text()='Relatives']")).click();
		captureScreenshot(driver);

		Select relationship = new Select(driver.findElement(By.id("relationship_type")));
		relationship.selectByVisibleText(relation);
		captureScreenshot(driver);
		
		driver.findElement(By.xpath("//section[@id='relationships-info']//div//input[1]")).clear();
		driver.findElement(By.xpath("//section[@id='relationships-info']//div//input[1]")).sendKeys(relative);	
		captureScreenshot(driver);
	}
		
	public void setConfirmRegistration()
	{
		driver.findElement(By.id("confirmation_label")).click();
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		WebElement submitButton=driver.findElement(By.id("submit"));
		
	
		waitforelementtobeclickable(submitButton,60);
		Actions act=new Actions(driver);
		act.moveToElement(submitButton).click().perform();
		//driver.findElement(By.id("submit")).click();
		try {
			captureScreenshot(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean verifySuccessfulRegistration()
	{
		
		WebElement verifyregtext=driver.findElement(By.xpath("//h3[contains(text(),'DIAGNOSES')]"));
		waitforelementtobevisible(verifyregtext,60);
		
		waitforelementtobevisible(driver.findElement(By.xpath(".//*[@id='content']/div[6]/div[2]/span")),60);
		 id=driver.findElement(By.xpath(".//*[@id='content']/div[6]/div[2]/span")).getText();
		
		//wait for the text to appear
		
		if (verifyregtext.isDisplayed())
		{
		System.out.println("Registered Successfully");	
		return true;
		}
		else 
			System.out.println("Unsuccessfull Registration");
		return false;
	}
	
	public Boolean view(String id,String givenname,String middlename,String familyName, String gen,String dob) throws IOException, InterruptedException
	{
		SimpleDateFormat formatter=new SimpleDateFormat("MMM/dd/yyyy");
		Date dt=new Date(dob);
		String date=formatter.format(dt);
		String[] date1=date.split("/");
		String day=date1[1];
		String mon=date1[0];
		String year=date1[2]; 
		
		String dateofbirth=day+" "+mon+" "+year;
		String name=givenname+" "+middlename+" "+familyName;
		
		
		
		driver.findElement(By.xpath("//img[@src='/openmrs/ms/uiframework/resource/uicommons/images/logo/openmrs-with-title-small.png']")).click();
		captureScreenshot(driver);
		
		driver.findElement(By.linkText("Find Patient Record")).click();
		captureScreenshot(driver);
		
		driver.findElement(By.id("patient-search")).sendKeys(givenname);
		captureScreenshot(driver);
		
		
		try {
		waitforelementstobevisible(driver.findElements(By.xpath(".//*[@id='patient-search-results-table']/tbody/tr")),120);
		List<WebElement> rowPAtients=driver.findElements(By.xpath(".//*[@id='patient-search-results-table']/tbody/tr"));
		
		int row=rowPAtients.size();
		if (rowPAtients.get(0).getText().contains("No matching records found"))
		{
			
			System.out.println("Norecords found");
			
		}
		else
		{
		List<WebElement> pages=driver.findElements(By.xpath(".//*[@id='patient-search-results-table_paginate']/span/a"));
		  for (int i=0;i<pages.size();i++)	
		  {  
			  
			  if(i>0)
			  pages.get(i).click();
		
		  for(int j=1;j<=row;j++)
		    {
			List<WebElement> tdPAtients=driver.findElements(By.xpath(".//*[@id='patient-search-results-table']/tbody/tr["+j+"]/td"));
			  
			String a=tdPAtients.get(0).getText();
			String b=tdPAtients.get(1).getText();
			String gender=String.valueOf(gen.charAt(0)).toUpperCase();
			String de=tdPAtients.get(4).getText();
			//&& tdPAtients.get(1).getText().contains(name) && tdPAtients.get(2).getText().contains(gen) && tdPAtients.get(4).getText().trim().contains(d)
			  if (tdPAtients.get(0).getText().contains(id) && tdPAtients.get(1).getText().contains(givenname) && tdPAtients.get(2).getText().contains(gender) )//&& tdPAtients.get(4).getText().contains(dateofbirth) )
			   {   
				  tdPAtients.get(0).click();
			       captureScreenshot(driver);
			       break;  	      
			    	  
			    }	 
			  		   		     		
		     }
		  
		  
		
	      }
		
		  if(verifyText())
		  {
			  System.out.println("Successfully viewed");
			  return true;
			  
		  }
		 
		  else 
			  
			  System.out.println("Patient info not found ");
		  return false;
		}
		
		}
		catch(Exception e) {
			System.out.println("Unscusessful view");
		}
		  
		
		return true;
		
	 }

	public boolean edit(String editgivenname,String edityear) throws IOException
	{    
		
		WebElement editText=driver.findElement(By.xpath("//a[contains(text(),'Edit')]"));
		waitforelementtobeclickable(editText,60);
		editText.click();
		captureScreenshot(driver);
		
		driver.findElement(By.name("givenName")).clear();
		driver.findElement(By.name("givenName")).sendKeys(editgivenname);
		captureScreenshot(driver);
		
		driver.findElement(By.id("birthdateLabel")).click();
		captureScreenshot(driver);
		
		driver.findElement(By.id("birthdateYear-field")).clear();
		driver.findElement(By.id("birthdateYear-field")).sendKeys(edityear);
		captureScreenshot(driver);
		
		driver.findElement(By.xpath("//span[contains(text(),'Confirm')]")).click();
		driver.findElement(By.id("registration-submit")).click();
		captureScreenshot(driver);
		
		WebElement verifyedittext=driver.findElement(By.xpath("//h3[contains(text(),'DIAGNOSES')]"));
		waitforelementtobevisible(verifyedittext,60);
		
		//wait for the text to appear
		
		if (verifyedittext.isDisplayed())
		{
			
		return true;
		}
		else 
			
		return false;
		

	}
	
	public boolean verifyText()
	{
		
		WebElement verifyedittext=driver.findElement(By.xpath("//h3[contains(text(),'DIAGNOSES')]"));
		waitforelementtobevisible(verifyedittext,60);
		
		//wait for the text to appear
		
		if (verifyedittext.isDisplayed())
		{
					return true;
		}
		else 
			
		return false;
	}

	public boolean register(String givenname,String middlename,String familyName,String gen,String dob,String address1,String address2,String city,String state,String country,
			String zip,String number,String relation,String relative) throws IOException
	{
		setPatientName(givenname,middlename,familyName);
		setPatientGender(gen);
		setPatientBirthDate(dob);
		setPatientAddress(address1,address2,city,state,country,zip,number);
		setPatientRelatives(relation,relative);
		setConfirmRegistration();
		boolean success=verifySuccessfulRegistration();
		return success;
		
		
	}
	
	@Parameters({"username","password"})
	@Test(priority=1,description="Login to OpenMRS")
	public void Logintoapp(String username,String password) throws IOException
	{

		boolean result = login(username,password);
		Assert.assertTrue(result);
	}

	@Parameters({"givenname", "middlename","familyName", "gen","dob","address1","address2","city","state","country","zip","number","relation","relative"})
	@Test(priority=2,description="Register a Patient" ,dependsOnMethods={"Logintoapp"},enabled=true)
	public void RegisterPatient(String givenname,String middlename,String familyName,String gen,String dob,String address1,String address2,String city,String state,String country,
			String zip,String number,String relation,String relative) throws IOException
	{
		boolean result = register(givenname, middlename, familyName, gen, dob, address1, address2, city, state, country, zip, number, relation, relative);
		Assert.assertTrue(result);


	}
	@Parameters({"editgivenname","edityear"})
	@Test(priority=4,description="Edit the patient details",dependsOnMethods={"Logintoapp","viewPatientDetails"},enabled=false)
	public void editPatient(String editgivenname,String edityear) throws IOException
	{
		boolean result =edit(editgivenname, edityear);
		Assert.assertTrue(result);

	}
	
	@Parameters({"givenname","middlename","familyName","gen","dob"})
	@Test(priority=3,description="View the patient details",dependsOnMethods={"Logintoapp"})
	public void viewPatientDetails(String givenname,String middlename,String familyName, String gen,String dob) throws IOException, InterruptedException
	{
		
		
		view(id, givenname,middlename, familyName,gen, dob);
		
	}

	public void waitforelementtobeclickable(WebElement element,long time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeClickable(element));


	}
	
	
	public void waitforelementtobevisible(WebElement element,long time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(element));


	}
	
	public void waitforelementstobevisible(List<WebElement> element,long time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));


	}
	public void captureScreenshot(WebDriver driver) throws IOException
	{
		TakesScreenshot tsh=(TakesScreenshot)driver;
		
		//Strore in temp location
		File fs=tsh.getScreenshotAs(OutputType.FILE);
		
		//Copy the file from one location to another 
		
		Calendar cal= Calendar.getInstance();
		
		File destFile=new File("C:\\ScreenShot\\opemmrs"+cal.getTimeInMillis()+".jpg");
		FileUtils.copyFile(fs, destFile);
		
		
	}
}
