package test_Case;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.http.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.CommonMethodUtilities;
import common_method.common_method_api;
import io.restassured.path.json.JsonPath;
import request_Repository.post_request_repository;

public class post_tc1 {
	@Test
	public static void orchestrator() throws IOException {
		String responseBody = "";
		int res_StatusCode = 0;
		String baseURI = post_request_repository.baseuri();
		String resource = post_request_repository.resource();
		String requestBody = post_request_repository.requestbody();
		for (int i = 0; i < 5; i++) {
			res_StatusCode = common_method_api.responsestatuscode_extractor(baseURI, resource, requestBody);
			if (res_StatusCode == 201) {
				responseBody = common_method_api.responsebody_extractor(baseURI, resource, requestBody);
				response_Body_Validator(responseBody);

				break;
			} else {
				System.out.println("correct status code is not found in itenration" + i);

			}
		}
		CommonMethodUtilities.evidenceFileCreator("post_tc1", requestBody, responseBody);
		Assert.assertEquals(res_StatusCode, 201);

	}

	public static void response_Body_Validator(String responseBody) {
		// create jsonPath object to extract responseBody parameters

		JsonPath jsp = new JsonPath(responseBody);
		// extract response_body parameters;
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
		// System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " +
		// res_id + "\ncreatedAt : " + res_createdAt);

		// validate response body parameter
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		// Assert.assertNotNull(res_id,"assertion error,id parameter is not null");

		// extract data from createdAt parameter
		String actual_date = res_createdAt.substring(0, 10);
		String current_date = LocalDate.now().toString(); // create a date object
		// Assert.assertEquals(actual_date,current_date);
		// System.out.println("Actual DATE :" + actual_date + "\nCURRENT DATE : " +
		// current_date);
	}
}
