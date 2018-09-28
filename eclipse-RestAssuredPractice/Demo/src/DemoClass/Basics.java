package DemoClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Basics {
	
	
	@Test
	public void BasicsRestAssured(){
		//First Step -- Base URL or Host
		RestAssured.baseURI= "https://maps.googleapis.com";
		//use the given() block and add your parameters, headers e.t.c
		given().
		        param("location","-33.870775,151.199025").
		        param("radius", "5000").
		        param("key", "AIzaSyC9zFXeU3OTxDIXj6NvSWuo4SldMUwfduE").log().all().
		        
		        //Use the when() block for resources (get, put, post e.t.c)
		        
		        when().
		        get("/maps/api/place/nearbysearch/json").
		        
		        //Use the then() block for response 
		        
		        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		        //.body("results[1].geometry.location.lat", equalTo("-33.871042")).and().
		       .body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		      header("Server", "scaffolding on HTTPServer2");
	}

}
