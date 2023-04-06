package test_Case;

import java.io.IOException;

import org.testng.Assert;

import common_method.CommonMethodUtilities;
import common_method.get_common_method;
import io.restassured.path.json.JsonPath;
import request_Repository.get_request_repository;

public class get_tc1 {
	public static void orchestrator() throws IOException
	{
	String responsebody = " ";
	int responsestatuscode = 0;
	String baseuri = get_request_repository.baseuri();
	String resource = get_request_repository.resource();
	
	for (int i=0; i<5; i++)
	{
		responsestatuscode = get_common_method.responsestatuscode(baseuri, resource);
		if(responsestatuscode == 200)
		{
			responsebody = get_common_method.responsebody_extractor(baseuri, resource);
		    responsebodyValidator(responsebody);
			break;
		}
		else
		{
			System.out.println("correct status code is not found in the iteration" +i);
		}
	}
	   //evidence file
	   CommonMethodUtilities.evidenceFileCreator("get_tc1",null, responsebody);
       Assert.assertEquals(responsestatuscode, 200);
	}
    private static void responsebodyValidator(String responsebody) 
    {
    	JsonPath jsp = new JsonPath(responsebody);
      
      int count = jsp.getInt("data.size()");
		System.out.println(count);
  
		int id[] = { 7, 8, 9, 10, 11, 12 };
		String email[] = { "michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
				"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in" };
		String first_name[] = { "Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel" };
		String last_name[] = { "Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell" };
		String avatar[] = { "https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg",
				"https://reqres.in/img/faces/9-image.jpg", "https://reqres.in/img/faces/10-image.jpg",
				"https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg" };

		for (int i = 0; i < count; i++) {

			int exp_id = id[i];
			String exp_email = email[i];
			String exp_fname = first_name[i];
			String exp_lname = last_name[i];
			String exp_avatar = avatar[i];

			//extract responsebody parameter
			int res_id = jsp.getInt("data["+i+"].id");
		    System.out.println(res_id);
			String res_email = jsp.getString("data["+i+"].email");
			System.out.println(res_email);
			String res_fname = jsp.getString("data["+i+"].first_name");
			System.out.println(res_fname);
			String res_lname = jsp.getString("data["+i+"].last_name");
			System.out.println(res_lname);
			String res_avatar = jsp.getString("data["+i+"].avatar");
			System.out.println(res_avatar);

			// validate responsebody

			Assert.assertEquals(res_id, exp_id);
			Assert.assertEquals(res_email, exp_email);
			Assert.assertEquals(res_fname, exp_fname);
			Assert.assertEquals(res_lname, exp_lname);
			Assert.assertEquals(res_avatar, exp_avatar);
		}
	}


}
