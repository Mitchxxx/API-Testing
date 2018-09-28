package DemoClass;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.ReUsableMethods;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Basics4 {
	
	
		
	
	@Test
	public void postData () throws IOException {
		
		 String postdata = GenerateStringFromResource("C:\\Users\\mitchxxx1\\eclipse-RestAssuredPractice\\Demo\\src\\files\\AddAplace.xml");
		
		 
		 
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response res =given().
		queryParam("key", "AIzaSyC9zFXeU3OTxDIXj6NvSWuo4SldMUwfduE").
		body(postdata).
		when().
		//XML format
		
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
		
		XmlPath x = ReUsableMethods.rawToXml(res);
		
		
		
		String place = x.get("PlaceAddResponse.place_id");
		
		System.out.println(place);
		
		
		
		
		//Create a place = response (place_id) 
		//and delete Place = (Request - Place_id)
	}
	
	//Method to Convert XML data to String
			public static String GenerateStringFromResource(String path) throws IOException {
				
				return new String(Files.readAllBytes(Paths.get(path)));
			}

}
