package DemoClass;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics7 {
	
	
Properties prop = new Properties();
	
	@BeforeTest 
	public void getData () throws Exception {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\mitchxxx1\\eclipse-RestAssuredPractice\\Demo\\src\\files\\Env.properties");
		prop.load(fis);
	
	}
	
	
	
	@Test
	public void JiraAPI() {
		
		//Updating Comment in Jira
		
		RestAssured.baseURI = "http://localhost:1900";
		Response res =given().
				header("Cookie", "JSESSIONID="+ReUsableMethods.getSessionKey()).
				header("Content-Type","application/json").
				
				body("{\r\n" + 
						"      \"body\": \"Commenting in Jira with eclipse Hip hip Hurrayy!!!!!!\",\r\n" + 
						"      \"visibility\": {\r\n" + 
						"        \"type\": \"role\",\r\n" + 
						"        \"value\": \"Administrators\"\r\n" + 
						"      }\r\n" + 
						"    }").
				when().post("/rest/api/2/issue/10044/comment/").
				
				then().statusCode(201).extract().response();
		
		JsonPath js = ReUsableMethods.rawToJson(res);
		
		String id = js.get("id");
		System.out.println(id);
		
		
	}


}
