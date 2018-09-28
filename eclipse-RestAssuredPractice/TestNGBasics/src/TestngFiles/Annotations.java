package TestngFiles;

import org.testng.annotations.*;

public class Annotations {
	
	@BeforeMethod
	public void UserIdGeneration () {
		
		System.out.println("This Block executes before each method");
		
	}
	
	@BeforeTest 
	public void Cookies ()
	{
		System.out.println("This block executes before all TestCases");
	}
	
	@AfterTest
	public void CookiesClose()
	{
		System.out.println("This block executes after all TestCases");
	}
	
	@AfterMethod 
	public void ReportAdding()
	{
		System.out.println("This block executes after each method");
	}
	
	@Test
	public void OpeningBrowser()
	{
		System.out.println("Opening the Browser");
	}
	
	@Test
	public void FlightBooking()
	{
		System.out.println("Flight Booking");
	}
	

}
