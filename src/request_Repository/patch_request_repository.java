package request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.getData;

public class patch_request_repository {
	public static String baseuri()
	{
		String baseuri ="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource="api/users/2";
		return resource;
	}
	public static String requestbody() throws IOException
	{
		ArrayList<String> data = getData.getDataExcel("Patch_data", "tc1");
		String name = data.get(2);
		String job = data.get(3);
		String requestbody = "{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		return requestbody;
	}

}
