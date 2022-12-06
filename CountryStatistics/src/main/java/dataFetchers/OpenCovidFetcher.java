package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import client.UserSelection;

public class OpenCovidFetcher implements Fetcher {
	
	// TODO: Create new UserSelection for OpenCovid OR Add String date attribute to current UserSelection?
	public static void main(String[] args) {
		UserSelection selection = new UserSelection();
		selection.setFromYear(2021);
		selection.setToYear(2022);
		OpenCovidFetcher fetcher = new OpenCovidFetcher();
		fetcher.fetchData(selection);
	}

	/**
	 * Used to fetch data from the OpenCovid API
	 * @return retrievedJsonArray return the JsonArray containing data from OpenCovid API
	 */
	@Override
	public Object fetchData(UserSelection selection) {
		String country = "can";
		long fromYear = selection.getFromYear();
		long toYear = selection.getToYear();
		
		String urlString = String.format(""
				+ "\nhttps://api.opencovid.ca/timeseries?stat=all&geo=%s&after=%d&before=%d&fill=false&version=true&pt_names=short&hr_names=short&legacy=false&fmt=json"
				, country, fromYear, toYear);
		
		return null;
	}
	
	public Object fetchData(UserSelection selection, String analysisTypeCode) {
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
