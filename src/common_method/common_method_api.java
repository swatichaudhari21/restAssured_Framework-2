package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class common_method_api {
	public static int responsestatuscode_extractor(String baseuri, String resource , String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int responsestatuscode_extractor = given().header("Content-Type","application/json").body(requestBody).when().post(resource)
				.then().extract().statusCode();
		return responsestatuscode_extractor;
		
	}
	public static String responsebody_extractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI=baseuri;
		String responsebody_extractor = given().header("Content-Type","application/json").body(requestBody).when().post(resource)
				.then().extract().response().asString();
		return responsebody_extractor;
		
	}

}
