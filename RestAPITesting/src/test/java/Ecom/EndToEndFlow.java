package Ecom;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;

import EcomPojojs.EcomLogin;
import EcomPojojs.EcomLogonResponse;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class EndToEndFlow {

	public static void main(String[] args) {
		//RestAssured
		// TODO Auto-generated method stub
		
		RequestSpecification requestSpec =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").
				setContentType(ContentType.JSON).build();
		
		EcomLogin login = new EcomLogin();
		login.setUserEmail("pikachu@gmail.com");
		login.setUserPassword("Nikita@123");
		
	RequestSpecification loginspec=	given().log().all().spec(requestSpec).body(login);
	 EcomLogonResponse loginRes= loginspec.when().post("api/ecom/auth/login").then().log().all().
		extract().response().as(EcomLogonResponse.class);
	 
	 System.out.println("Response is generated");
	 loginRes.getUserId();
	 loginRes.getUserId();
	 String userId = loginRes.getUserId();
	String Token= loginRes.getToken();
	System.out.println(Token);
				
	
	
	//AddProduct 
	
	RequestSpecification spec1 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.MULTIPART).
			addHeader("Authorization", Token).build();
	
	RequestSpecification addproductreq= given().log().all().spec(spec1).body("api/ecom/product/add-product").
			param("productName", "Shirt").param("productAddedBy",userId).param("productCategory", "fashion")
			.param("productSubCategory", "shirts").param("productPrice", "20000").param("productDescription", "clothing").
			param("productFor","men").multiPart("productImage", new File("C:\\Users\\hp\\Downloads\\mens_shirt.jpg"));
	
	String addProductres = addproductreq.expect().defaultParser(Parser.JSON).when().post("api/ecom/product/add-product").then().log().all().extract().
			response().asString();
	JsonPath js1 = new JsonPath(addProductres);
	String productId = js1.get("productId");
	String message = js1.get("message");
	System.out.println(productId+" "+message);
	
	//deleteProduct 
	
	RequestSpecification spec2 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").build();
	
	RequestSpecification deletereq = given().spec(spec2).log().all().header("Authorization",Token).
			pathParam("productId",productId);
	
	String deleteRes= deletereq.when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	
	JsonPath js2 = new JsonPath(deleteRes);
	Assert.assertEquals("Product Deleted Successfully",js2.get("message"));
	
	//System.out.println(js2);
	

	}

}
