package Oauth_automation;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serializationPojo.AddPlace;
import serializationPojo.location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serialization {
	
	public static void main(String[] args) {
		
		AddPlace ap = new AddPlace();
		ap.setLanguage("Marathi");
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(35.383494);
		ap.setLocation(l);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		List<String> list1 = new ArrayList<String>();
		list1.add("shoe park");
		list1.add("shop");
		list1.add("klm");
		ap.setTypes(list1);
		ap.setWebsite("http://google.com");
		
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
RequestSpecification res1 =given().spec(req).body(ap);

Response response = res1.when().post("/maps/api/place/add/json").then().log().all().spec(res).
			extract().response();
		
String ResponseString = response.asString();
System.out.println(ResponseString);
	}

}
