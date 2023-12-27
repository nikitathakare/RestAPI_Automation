package Oauth_automation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojos.Api;
import pojos.WebAutomation;
import pojos.coursedetails;

public class Oauth_TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String [] webcourses = {"Selenium Webdriver Java","Cypress"};
		
		String code = "4%2F0AfJohXmArIyPMw2Lgd7QO1gymLyJZM-fkBQx4JZZuaBe15XT1MLSiaHyTqH3HKpoBQj9rg";
		
		String response = given().urlEncodingEnabled(false).queryParams("code",code)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code").when()
				.log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(response);
		String accesscode= js.getString("access_token");
		System.out.println(accesscode);
		
		coursedetails gc =given().queryParams("access_token",accesscode).expect().defaultParser(Parser.JSON)
		.log().all().when().get("https://rahulshettyacademy.com/getCourse.php")
		.as(coursedetails.class);
		
		System.out.println(gc.getUrl());
		System.out.println(gc.getCourses().toString());
	
	/// Retrive api coursetitle	
	  List<Api>	ap =gc.getCourses().getApi();
	  for(int i=0;i<ap.size();i++) {
		  System.out.println(ap.get(i).getCourseTitle()+ ""+""+ap.get(i).getPrice() );
	  }
	  
	  ArrayList<String> a = new ArrayList<String>();
	  // compare the cousetitle of webautomation courses
	  List<WebAutomation> wa = gc.getCourses().getWebAutomation();
	  for(int i =0;i<wa.size();i++) {
		  a.add(wa.get(i).getCourseTitle());
	  }
	  
	  List<String>  b= Arrays.asList(webcourses);
	  Assert.assertTrue(a.equals(b));
	  
	  
	  
		

	}

}
