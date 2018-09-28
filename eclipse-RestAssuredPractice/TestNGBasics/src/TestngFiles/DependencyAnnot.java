package TestngFiles;

import org.testng.annotations.Test;

public class DependencyAnnot {

	@Test
	public void OpeningBrowser()
	{
		System.out.println("Opening the Browser");
	}
	
	// Use the dependsOnMethod to prioritize the Test
	//Use the alwaysRun = true to make the test is executed even if the test it depends on fails
	
	@Test(dependsOnMethods = {"OpeningBrowser"},alwaysRun=true)
	public void FlightBooking()
	{
		System.out.println("Flight Booking");
	}
	
	// Use Time out to select the maximum time for test execution
	@Test(timeOut=2000)
	public void Timerelated()
	{
		System.out.println("New test case");
	}
	
	//Use the enabled = false to enable or disable scripts
	@Test(enabled=false)
	public void Payment()
	{
		System.out.println("Making Payment");
	}
}
