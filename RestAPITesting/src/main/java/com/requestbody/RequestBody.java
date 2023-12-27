package com.requestbody;

public class RequestBody {
	
	public static String UnderDevAPI() {
		return "{\r\n"
				+ "    \"dashboard\":{\r\n"
				+ "        \"purcahseAmount\":910,\r\n"
				+ "        \"website\":\"abcdefg.com\"\r\n"
				+ "\r\n"
				+ "    },\r\n"
				+ "    \"courses\":[\r\n"
				+ "        {\r\n"
				+ "            \"title\":\"Selenium Python\",\r\n"
				+ "            \"price\":50,\r\n"
				+ "            \"copies\":6\r\n"
				+ "        },\r\n"
				+ "        { \r\n"
				+ "            \"title\":\"Java\",\r\n"
				+ "            \"price\":80,\r\n"
				+ "            \"copies\":2\r\n"
				+ "        },\r\n"
				+ "        { \r\n"
				+ "            \"title\":\"SDLC\",\r\n"
				+ "            \"price\":120,\r\n"
				+ "            \"copies\":7\r\n"
				+ "        },\r\n"
				+ "        { \r\n"
				+ "            \"title\":\"RPA\",\r\n"
				+ "            \"price\":100,\r\n"
				+ "            \"copies\":9\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
	
	}
	
	public static String AddBook(String isbn , String aisle) {
		
		String payload= "{\r\n"
				+ "    \"name\":\"Learn RwstAPI Automation\",\r\n"
				+ "    \"isbn\":\""+isbn+"\",\r\n"
				+ "    \"aisle\":\""+aisle+"\",\r\n"
				+ "    \"author\":\"John Foe\"\r\n"
				+ "}";
		return payload;
	}
	

}
