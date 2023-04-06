package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class get_common_method {
	public static int responsestatuscode(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
        int responsestatuscode = given().when().get(resource).then().extract().statusCode();
		return responsestatuscode;
	}
	public static String responsebody_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		String responsebody= given().when().get(resource).then().extract().response().asString();
		return responsebody;
	}

}
