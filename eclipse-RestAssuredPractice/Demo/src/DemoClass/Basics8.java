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

public class Basics8 {
	
	Properties prop = new Properties();
	@BeforeTest 
	public void getData () throws Exception {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\mitchxxx1\\eclipse-RestAssuredPractice\\Demo\\src\\files\\Env.properties");
		prop.load(fis);
	
	}
	
	
	
	@Test
	public void JiraAPI() {
		
		//Creating Issue/Defect
		
		RestAssured.baseURI = "http://localhost:1900";
		Response res =given().
				header("Cookie", "JSESSIONID="+ReUsableMethods.getSessionKey()).
				header("Content-Type","application/json").
				pathParams("commentid","10035").
				
				body("{\r\n" + 
						"      \"body\": \"Updating comment from the automation code\",\r\n" + 
						"      \"visibility\": {\r\n" + 
						"        \"type\": \"role\",\r\n" + 
						"        \"value\": \"Administrators\"\r\n" + 
						"      }\r\n" + 
						"    }").
				when().put("/rest/api/2/issue/10044/comment/{commentid}").
				
				then().statusCode(200).extract().response();
		
		JsonPath js = ReUsableMethods.rawToJson(res);
		
		String id = js.get("id");
		System.out.println(id);
		

}
}