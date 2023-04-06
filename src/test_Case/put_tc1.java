package test_Case;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.CommonMethodUtilities;
import common_method.put_common_method;
import io.restassured.path.json.JsonPath;
import request_Repository.put_request_repository;

public class put_tc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responsebody = " ";
		int responsestatuscode = 0;
		String baseuri = put_request_repository.baseuri();
		String resource = put_request_repository.resource();
		String requestbody = put_request_repository.requestbody();
		
		for (int i=0; i<5; i++)
		{
			responsestatuscode = put_common_method.responsestatuscode(baseuri, resource, requestbody);
			if(responsestatuscode == 200)
			{
				responsebody = put_common_method.responsebody_extractor(baseuri, resource, requestbody);
				responsebodyValidator(responsebody);
				break;
			}
			else
			{
				System.out.println("correct status code is not found in the iteration" +i);
			}
			
		}
		CommonMethodUtilities.evidenceFileCreator("put_tc1", requestbody, responsebody);
		Assert.assertEquals(responsestatuscode, 200);
	}
	public static void responsebodyValidator(String responsebody)
	{
		//create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responsebody);
		
		//extract responsebody parameters
		String res_name = jsp.getString("name");
		//System.out.println(res_name);
		String res_job = jsp.getString("job");
		//System.out.println(res_job);
		String res_updatedAt = jsp.getString("updatedAt");
		//System.out.println(res_updatedAt);
		
		//validate responsebody parameters
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
	
		//extract date from updatedAt parameter
	    String actual_date=res_updatedAt.substring(0,10);
	    String current_date=LocalDate.now().toString();        //create date object
	   // Assert.assertEquals(actual_date, current_date);
	}


}
