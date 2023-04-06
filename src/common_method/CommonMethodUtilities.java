package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonMethodUtilities {
	public static void evidenceFileCreator(String filename, String request, String response) throws IOException
	{
		File newtextfile = new File("C:\\Users\\pc1\\Desktop\\restAssuredEvidence\\" + filename + ".txt");
		if(newtextfile.createNewFile())
		{
			FileWriter datawriter = new FileWriter(newtextfile);
			datawriter.write("Requestbody is: \n" +request+ "\n\n");
			datawriter.write("Responsebody is: \n" +response);
			datawriter.close();
			System.out.println("Request and Responsebody data saved in:" +newtextfile.getName());
		}
		else
		{
			System.out.println(newtextfile.getName() + "already exists take a backup of it");
		}
	}


}
