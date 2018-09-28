package DemoClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.PayLoad;
import files.Resources;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics3 {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws Exception{
		
		FileInputStream fis= new FileInputStream("C:\\Users\\mitchxxx1\\eclipse-RestAssuredPractice\\Demo\\src\\files\\Env.properties");
		prop.load(fis);
		
		prop.get("HOST");
	}
	
	@Test
	public void AddandDelete () {
		//Body of Data to be posted as a string variable
		
		//Task 1- Grab the response
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().
		queryParam("key", prop.getProperty("KEY")).
		body(PayLoad.getPostData()).
		when().
		post(Resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).  
		extract().response();
		
		//Convert Extracted response to String and print it out
		String responseString = res.asString();
		System.out.println(responseString);
		
		
		
		//Task 2- Grab the place-id from the response
		//Convert to JSON format
				JsonPath js = new JsonPath(responseString);
				String placeId =js.get("place_id");
				
				System.out.println(placeId);
		
		//Task 3- place this place_id in the Delete request
				
				given().
				queryParam("key",prop.getProperty("KEY")).
				
				body("{\r\n" + 
						"  \"place_id\": \""+placeId+"\"\r\n" + 
						"}").
				when().
				post(Resources.placePostData()).
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("status", equalTo("OK"));  
				
	}

}
