package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import client.UserSelection;
import server.BusinessDataObject;

/**
 * The fetcher class for connecting to world bank API
 * @author mafu
 *
 */
public class WorldBankFetcher implements IFetcher{
	
	/**
	 * Used to fetch data from World Bank API
	 * @return retrievedJsonArray return the JsonArray containing data from World Bank API 
	 */
	public JsonArray fetchData(BusinessDataObject selection, String analysisTypeCode) {
		String country = selection.getCountryCode();
		String fromYear = selection.getFromYear();
		String toYear = selection.getToYear();
		
		String urlString = String.format(""
				+ "\nhttp://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json"
				, country, analysisTypeCode, fromYear, toYear);
		
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
				
				retrievedJsonArray = new JsonParser().parse(inline).getAsJsonArray();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedJsonArray;	
	}
}
