package com.restapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.Assert;

import com.commonmethods.commonMethods;

public class RestAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//given
		//when
		//then
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//post the data
		String response = given().log().all().queryParams("key","qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
				extract().response().asString();
		System.out.println(response);
		
		JsonPath js = commonMethods.rawtoJson(response);
		String placeid =js.getString("place_id");
		System.out.println(placeid);
	// update the data	
		String NewAddress= "29, side layout, cohen 100";
		String response1 =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+NewAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).
		body("msg",equalTo("Address successfully updated")).extract().response().asPrettyString();
		
		System.out.println(response1);
		JsonPath j = commonMethods.rawtoJson(response1);
		String address = j.getString("address");
		
		//get the google map 
		String response2 =given().log().all().queryParam("place_id", placeid).queryParam("key", "qaclick123").
		header("Content-Type", "application/json").when().get("maps/api/place/get/json").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response2);
		
		JsonPath j1 = commonMethods.rawtoJson(response2);
		String address1 = j1.getString("address");
		
		if(address == address1) {
			System.out.println("address matched");
		}
		
		Assert.assertEquals(address, "KL");
		
		

	}

}
