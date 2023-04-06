package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class patch_common_method {
	public static int responsestatuscode_extractor(String baseuri, String resource, String requestbody)
	{
		RestAssured.baseURI=baseuri;
		int responsestatuscode = given().header("Content-Type","application/json").body(requestbody).when().patch(resource)
				.then().extract().statusCode();
		return responsestatuscode;
	}
	public static String responsebody_extractor(String baseuri, String resource, String requestbody)
	{
		RestAssured.baseURI=baseuri;
		String responsebody = given().header("Content-Type","application/json").body(requestbody).when().patch(resource)
				.then().extract().response().asString();
				return responsebody;
	}

}
