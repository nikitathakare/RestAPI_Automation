package com.restapi;

import com.commonmethods.commonMethods;
import com.requestbody.RequestBody;

import io.restassured.path.json.JsonPath;

public class complexAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(RequestBody.UnderDevAPI());
		System.out.println(js.get("dashboard.purcahseAmount"));
		
		int count =js.get("courses.size()");
		System.out.println(count);
		
		String firstcoures = js.get("courses[0].title");
		System.out.println(firstcoures);
		
		for(int i=0;i<count;i++) {
			String str = js.get("courses["+i+"].title");
			System.out.println(str);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		

	}

}
