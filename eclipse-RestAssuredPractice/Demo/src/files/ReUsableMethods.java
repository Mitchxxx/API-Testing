package files;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReUsableMethods {
	
/*	Properties prop = new Properties();
*/
	
	public static XmlPath rawToXml(Response res) {
		String respon = res.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	
	public static JsonPath rawToJson(Response res) {
		
		String respon = res.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}
	
	public static String getSessionKey() {
		
		RestAssured.baseURI = "http://localhost:1900";
		Response res =given().header("cookie", "JSESSIONID=6E3487971234567896704A9EB4AE501F").header("Content-Type","application/json"). 
		body("{ \"username\": \"megboko\", \"password\": \"Macaustin1990!\" }").
	when().
	post("/rest/auth/1/session").
	
	then().statusCode(200).extract().response();
		
		JsonPath js = ReUsableMethods.rawToJson(res);
		
		String session = js.get("session.value");
		
		//System.out.println(session);
		return session;
		
		
	}
	
	public static String commentID() {
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
		return commentID();
	}
}
