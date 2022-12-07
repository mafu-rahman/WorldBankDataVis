package dataFetchers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonArray;
import server.BusinessDataObject;

public class OpenCovidFetcher implements IFetcher {
	
	public static void main(String args[]) {
//		BusinessDataObject obj = new BusinessDataObject();
//		obj.setFromYear("2020");
//		obj.setToYear("2021");
//		
//		String s = fetchData1(obj, "");
	}
	
	public String fetchData(BusinessDataObject selection, String analysisTypeCode) {
		String fromYear = selection.getFromYear();
		String toYear = selection.getToYear();
		
		String urlString = String.format("https://api.opencovid.ca/timeseries?stat=all&geo=can"
				+ "&after=%s-01-01&before=%s-12-31&"
				+ "fill=false&version=true&pt_names=short&hr_names=short&legacy=false&fmt=json", fromYear, toYear);
		
		System.out.println("Connecting to URL: " + urlString);
		JsonArray retrievedJsonArray = new JsonArray();
		
		String inline = "";
		StringBuilder content = new StringBuilder();  
		
		try  
	    {  
	      URL url = new URL(urlString); // creating a url object  
	      URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
	  
	      // wrapping the urlconnection in a bufferedreader  
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
	      String line;  
	      // reading from the urlconnection using the bufferedreader  
	      while ((line = bufferedReader.readLine()) != null)  
	      {  
	        content.append(line + "\n");  
	      }  
	      bufferedReader.close();  
	    }  
	    catch(Exception e)  
	    {  
	      e.printStackTrace();  
	    }  
		
		return content.toString();	
	}
	
}