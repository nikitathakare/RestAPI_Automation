package JiraAutomation;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;

import com.commonmethods.commonMethods;

public class AddComments {
	
	public static void main(String[] args) {
		RestAssured.baseURI="http://localhost:8080/";
		
	SessionFilter session = new SessionFilter();	
//login to get the login id 
		String response = given().header("Content-Type","application/json").
				body("{ \"username\": \"nikitathakare\",\r\n"
				+ " \"password\": \"Nikita@1234\" }").log().all().
				filter(session).when().post("/rest/auth/1/session").then().extract().response().asString();
		
//ADD COMMENTS		
		given().log().all().pathParam("key","10102").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \"This is Second Comments\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).
		when().post("rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201);
		
//Add Attachments
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\JiraAttachment.txt";
		given().header("X-Atlassian-Token", "no-check").header("Content-Type","multipart/form-data")
		.multiPart("file",new File(path))
		
		.filter(session).when().post("rest/api/2/issue/10102/attachments")
		.then().log().all().assertThat().statusCode(200);
		
//Get Issue Details 
		String res = given().header("Content-Type","application/json").pathParam("key", "10101").log().all().
				filter(session).when().get("rest/api/2/issue/{key}").then().log().all().extract().response().asPrettyString();
		
		JsonPath js = commonMethods.rawtoJson(res);
		String a = js.getString("fields.issuetype.name");
		System.out.println(a);
		
		
		
		
	}

}
