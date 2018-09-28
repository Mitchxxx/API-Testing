package TestngFiles;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Annot2 {
	
	@BeforeSuite
	public void InstallSoftware ()
	{
		System.out.println("I am the first");
		
	}
	
	@Test
	public void software()
	{
		System.out.println("I am the Test");
	}
	
	@AfterSuite 
	public void uninstallSoftware()
	{
		System.out.println("I am last");
	}

}
