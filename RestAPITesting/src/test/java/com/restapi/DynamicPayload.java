package com.restapi;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonmethods.commonMethods;
import com.requestbody.RequestBody;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DynamicPayload {
	
	@Test(dataProvider = "BookData")
	public void addbookMetod(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().log().all().header("Content-Type","application/json").
				body(RequestBody.AddBook(isbn,aisle)).
		when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().
		response().asString();
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
	
	}
	
	@DataProvider(name="BookData")
	public Object[][] addbookData() {
		return new Object [][] {{"klmn","908"},{"dvh","453"},{"af","614"}};
	}

}
