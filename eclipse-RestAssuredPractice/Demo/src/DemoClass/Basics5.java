package DemoClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import files.ReUsableMethods;

import static io.restassured.RestAssured.given;

public class Basics5 {
	
	
	@Test
	public void BasicsRestAssured(){
		
		RestAssured.baseURI= "https://maps.googleapis.com";
		
		Response res = given().
		        param("location","-33.870775,151.199025").
		        param("radius", "5000").
		        param("key", "AIzaSyC9zFXeU3OTxDIXj6NvSWuo4SldMUwfduE").log().all().
		        when().
		        get("/maps/api/place/nearbysearch/json").
		        
		         
		        
		        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		        //.body("results[1].geometry.location.lat", equalTo("-33.871042")).and().
		       .body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().log().body().
		      extract().response();
		//Convert to JsonPath
		    JsonPath js = ReUsableMethods.rawToJson(res);
		    
		 //Get size of Array   
		    int count = js.get("results.size()");
		    for (int i=0; i<count; i++) {
		    	
		    	System.out.println(js.get("results["+i+"].name").toString());
		    }
		    
		  
		    
		    
		    
	}

}
