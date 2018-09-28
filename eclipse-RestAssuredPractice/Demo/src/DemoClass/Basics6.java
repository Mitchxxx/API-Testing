package DemoClass;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.*;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Basics6 {
	
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
				
				body("{\r\n" + 
						"	\"fields\":{\r\n" + 
						"		\"project\":\r\n" + 
						"		{\r\n" + 
						"			\"key\": \"RES\"\r\n" + 
						"		},\r\n" + 
						"		\"summary\": \"Issue 5 for automating comment\",\r\n" + 
						"		\"description\": \"Adding commen\",\r\n" + 
						"		\"issuetype\":{\r\n" + 
						"			\"name\": \"Bug\"\r\n" + 
						"		}\r\n" + 
						"	}\r\n" + 
						"}").
				when().post("/rest/api/2/issue").
				
				then().statusCode(201).extract().response();
		
		JsonPath js = ReUsableMethods.rawToJson(res);
		
		String id = js.get("id");
		System.out.println(id);
		
		
	}

	
	

}
