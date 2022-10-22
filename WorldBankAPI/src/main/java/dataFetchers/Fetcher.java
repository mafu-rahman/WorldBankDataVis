package dataFetchers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.crypto.dsig.keyinfo.PGPData;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import analyser.*;

public class Fetcher {
	
	
	private String countryCode;
	private int startYear;
	private int endYear;
	private Vector<String> bannedVisual;
	private String visualType;
	
	// Country Codes
	private static String[] CODES = {"CAN", "GBR", "USA", "CN", "BRA"};

		
	/*
	 * Constructor
	 */
	public Fetcher(String countryCode, int sYear, int eYear) {
		if(countryChecker(countryCode)) {
			this.countryCode = countryCode;
		}
		if(dateChecker(sYear, eYear)) {
			this.startYear = sYear;
			this.endYear = eYear;
		}
	}
	
	public JsonArray fetchData(String analysisTypeCode) {
		String country = this.countryCode;
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", country, analysisTypeCode, this.startYear, this.endYear);
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
	
	
	/*
	 * check if analysisType, start year, end year, and countryCode are valid
	 */
	
	private boolean countryChecker(String code) {
		for(String s : CODES) {
			if(s.equals(code)) {
				System.out.println(String.format("Valid country code found: %s", code));
				return true;
			}
		}
		return false;
	}
	
	private boolean dateChecker(int sYear, int eYear) {
		if(sYear <= eYear) {
			System.out.println(String.format("Valid Dates found: %d, %d", sYear, eYear));
			return true;
		}
		return false;
	}
	
	
	/*
	 * getters and setters
	 */
	public String getCountry() {
		return this.countryCode;
	}
	
	public int getStartYear() {
		return this.startYear;
	}
	
	public int getEndYear() {
		return this.endYear;
	}
	
	public void setCountry(String country) {
		this.countryCode = country;
	}
	
	public void setStartYear(int sYear) {
		this.startYear = sYear;
	}
	
	public void setEndYear(int eYear) {
		this.endYear = eYear;
	}
	
	public static void main(String[] args) { 
			
			//Fetcher fetcher = new Fetcher("USA", 0, 2000, 2006);
			//fetcher.fetchData();
		
	}

	public void setAnalyseType(String visualType) {
		this.visualType = visualType;
	}
	
}
