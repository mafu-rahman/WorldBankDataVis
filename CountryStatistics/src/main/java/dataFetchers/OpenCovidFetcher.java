package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import client.UserSelection;

public class OpenCovidFetcher implements IFetcher {
	
	public JsonArray fetchData(UserSelection selection, String analysisTypeCode) {
		String country = selection.getCountryCode();
		long fromYear = selection.getFromYear();
		long toYear = selection.getToYear();
		
		String urlString = "http://130.63.209.45:8000/WBAnalysis/?p1=Analysis1&p2=10&p3=20";
		System.out.println("Connecting to URL: " + urlString);
		JsonArray retrievedJsonArray = new JsonArray();
		
		try {
			
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				
				Gson gson = new Gson();
				
				retrievedJsonArray = new JsonParser().parse(inline).getAsJsonArray();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedJsonArray;	
	}
}