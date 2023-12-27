package com.commonmethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonMethods {
	
	public static JsonPath rawtoJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
