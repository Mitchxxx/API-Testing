package DemoClass;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Basics9 {
	
	String ConsumerKey = "VM3pj57jy3bMpRIhT26dOm7JR";
	String ConsumerSecret = "ZotEDjZuO0g2wgdGFP7YNCbAgQnsdNXqhj5dJxBGm5tVtRXmyQ";
	String Token = "978707352382853120-96Jw9icKw7MBjayuVO0nvMBeluUe5mY";
	String TokenSecret = "lTCv5mnz4JhSHLUQHgNqbLWeXgnhyA2dosmT8JoIdRk35";
	String id;
	//Import the scribe jar file for oauth 1 authentication from the maven repository
	
	@Test 
	public void getLatestTweet()
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.queryParam("count", "1").
		
		when().get("/home_timeline.json").
		
		then().extract().response();
	
	String response = res.asString();
	
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
	
	String Text2 =js.get("text");
	System.out.println(Text2);
	
	String Id = js.getString("id");
	
	System.out.println(Id);
	
		
	}
	
	@Test
	public void CreateTweet () {
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res2=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
			.queryParam("status", "Hi Guys I am tweeting from Eclipse with Automation").
			
			when().post("/update.json").
			
			then().extract().response();
		
		String response = res2.asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		
		//String Text2 =js.get("text");
		//System.out.println(Text2);
		
		 id = js.get("id").toString();
		
	
		
		
	}
	
	@Test 
	public void DeleteTweet () {
		 
		//String id = "978731953506668544";
		CreateTweet();
			
			RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
			Response res2=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.
				
				when().post("destroy/"+id+".json").
				
				then().extract().response();
			
			String response = res2.asString();
			
			System.out.println(response);
			
			JsonPath js = new JsonPath(response);
			
			System.out.println("Tweet Which got deleted with automation is below");
		Boolean trun = js.get("truncated");
		System.out.println(trun);
	}

}
