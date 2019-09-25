package Day3;



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.FileUtil;

import org.openqa.selenium.OutputType;
//import org.apache.commons.io.input.*;
import org.apache.commons.io.FileUtils;

import Testme.driverutility;

public class Onlineshoppingtesting {
	
	
	WebDriver driver=Drivers.getDriver("chrome");
	ExtentHtmlReporter reporter= new ExtentHtmlReporter("C:\\Users\\training_b6B.01.16\\Desktop\\testme.html");
	   ExtentReports extent= new ExtentReports();
	   ExtentTest logger=extent.createTest("TestMeApp");
	@Test
    public void startReportbeforeTest() {
	
	  String url="http://10.232.237.143:443/TestMeApp/fetchcat.htm";
	  driver.get(url);
	  driver.manage().window().maximize();
	}
  
	@Test(priority=1,enabled=false)
	public void testRegistration() {
		driver.findElement(By.linkText("SignUp")).click();
		
		driver.findElement(By.id("userName")).sendKeys("chaitra12");
		driver.findElement(By.id("firstName")).sendKeys("Rakshitha");
		driver.findElement(By.id("lastName")).sendKeys("M");
		driver.findElement(By.id("password")).sendKeys("rakshitha");
		driver.findElement(By.name("confirmPassword")).sendKeys("rakshitha");
		WebElement radio=driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[6]/div/div/label"));
		radio.click();
		driver.findElement(By.id("emailAddress")).sendKeys("rakshitharaj25@gmail.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("7829333841");
		driver.findElement(By.id("dob")).sendKeys("25-09-1990");
		driver.findElement(By.id("address")).sendKeys("Bangalore");
		Select pc=new Select(driver.findElement(By.name("securityQuestion")));
		pc.selectByIndex(0);
		driver.findElement(By.name("answer")).sendKeys("Bangalore");
		driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[13]/div/input[1]")).click();;
	}
	
	@Test(priority=2)
	public void testLogin() {
		  driver.findElement(By.linkText("SignIn")).click();
		  driver.findElement(By.id("userName")).sendKeys("Lalitha");
		  driver.findElement(By.id("password")).sendKeys("Password123");
		  driver.findElement(By.name("Login")).click();	
	}
	
	@Test(priority=3)
	public void carttest() {
		driver.findElement(By.linkText("All Categories")).click();
		driver.findElement(By.linkText("Electronics")).click();
		driver.findElement(By.linkText("Head Phone")).click();
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
		
	}
	@Test(priority=4)
	public void payment() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();
		Thread.sleep(5000);
		WebElement radio=driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div[2]/div[2]/div"));
		radio.click();
		driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
		driver.findElement(By.name("username")).sendKeys("123457");
		driver.findElement(By.name("password")).sendKeys("Pass@457");
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[3]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[2]/input")).click();
		driver.findElement(By.name("transpwd")).sendKeys("Trans@457");
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div/form/div/div[2]/input")).click();
		driver.findElement(By.xpath("/html/body/header/div/div/ul/b/a[2]")).click();
		
	}
	/*@AfterMethod
	public void getResultsAfterMethod() {
		
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("C:\\Users\\training_b6B.01.16\\Desktop\\testme.html");
		  ExtentReports extent=new ExtentReports();
		  extent.attachReporter(reporter);
		  ExtentTest logger=extent.createTest("TESTME");
		  logger.log(Status.INFO, "TestMe app is used");
		  logger.log(Status.PASS, "Excel data reading is done successfully");
		  //logger.log(Status.FAIL, MarkupHelper.createLabel("this test case fails", ExtentColor.CYAN));
		  extent.flush();
	}*/
	
	 @AfterMethod
	  public void getResultAfterMethod(ITestResult result) throws Exception
	  {
	   
	   extent.attachReporter(reporter);
	 
	   
	   if(result.getStatus()==ITestResult.SUCCESS)
	   {
   	   logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Test passed",ExtentColor.GREEN));
	   TakesScreenshot capture1=(TakesScreenshot)driver;
	   File source1=capture1.getScreenshotAs(OutputType.FILE);
	   String imgpath1=System.getProperty("user.dir")+"/extent-reports/snapshots/"+result.getName()+".png";
	   System.out.println(result.getName());
	   FileUtils.copyFile(source1,new File(imgpath1));
	   logger.addScreenCaptureFromPath(imgpath1,result.getName());
	   }
	   else if(result.getStatus()==ITestResult.FAILURE)
	   {
	   logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"Test fail",ExtentColor.RED));
	   TakesScreenshot capture=(TakesScreenshot)driver;
	   File source=capture.getScreenshotAs(OutputType.FILE);
	   String imgpath=System.getProperty("user.dir")+"/extent-reports/snapshots/"+result.getName()+".png";
	   System.out.println(result.getName());
	   FileUtils.copyFile(source,new File(imgpath));
	   logger.addScreenCaptureFromPath(imgpath,result.getName());
	         }
	   extent.flush();
	  }
	 
	}
