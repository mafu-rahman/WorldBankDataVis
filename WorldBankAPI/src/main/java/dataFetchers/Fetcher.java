package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Fetcher {
	
	
	private String countryCode;
	private int startYear;
	private int endYear;
	private Vector<String> bannedVisual;
	private String visualType;
	private int analysisType;
	
	// Country Codes
	private static String[] CODES = {"CAN", "GBR", "USA", "CN", "BRA"};
		
	/**
	 * Constructor method for fetcher
	 * @param countryCode code for country 
	 * @param sYear starting year for the desired period
	 * @param eYear ending year for the desired period
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
	
	/**
	 * Constructor method for fetcher
	 * @param countryCode code for country 
	 * @param analysisType index of type of analysis desired 
	 * @param sYear starting year for the desired period
	 * @param eYear ending year for the desired period
	 */
	public Fetcher(String countryCode, int analysisType, int sYear, int eYear) {
		if(countryChecker(countryCode)) {
			this.countryCode = countryCode;
		}
		if(dateChecker(sYear, eYear)) {
			this.startYear = sYear;
			this.endYear = eYear;
		}
		if(analysisType >= 0 && analysisType <= 8) {
			this.analysisType = analysisType;
		}
	}
	
	/**
	 * Method used to fetch data from World Bank API 
	 * @param analysisTypeCode code from which data is to be fetched, for example, "SP.POP.TOTL" 
	 * @return retrievedJsonArray JSONArray of data that has been fetched from the API 
	 */
	public JsonArray fetchData(String analysisTypeCode) {
		String country = this.countryCode;
		String urlString = String.format(""
				+ "http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json"
				, country, analysisTypeCode, this.startYear, this.endYear);
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
	
	
	/**
	 * Check if country is valid
	 * @param code String value of country code
	 * @return boolean value true if country is valid, false otherwise
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
	
	/**
	 * Check if the period over which the analysis is done is valid
	 * @param sYear starting year
	 * @param eYear ending year
	 * @return boolean value true if country is valid, false otherwise
	 */
	private boolean dateChecker(int sYear, int eYear) {
		if(sYear <= eYear) {
			System.out.println(String.format("Valid Dates found: %d, %d", sYear, eYear));
			return true;
		}
		return false;
	}
	
	/**
	 * Getter Method
	 * @return country code 
	 */
	public String getCountry() {
		return this.countryCode;
	}
	
	/**
	 * Getter Method
	 * @return starting year
	 */
	public int getStartYear() {
		return this.startYear;
	}
	
	/**
	 * Getter Method
	 * @return ending year
	 */
	public int getEndYear() {
		return this.endYear;
	}
	
	/**
	 * Getter Method
	 * @return analyis type
	 */
	public int getAnalysisType() {
		return this.analysisType;
	}
	
	/**
	 * Getter Method
	 * @return visual type
	 */
	public String getVisualType() {
		return this.visualType;
	}
	
	/**
	 * Setter Method
	 * @param analysis
	 */
	public void setAnalysisType(int analysis) {
		if(!(analysis >= 0 && analysis <= 9)) {
			throw new IllegalArgumentException("Error: Invalid Analysis Type!");
		}
		this.analysisType = analysis;
	}
	
	/**
	 * Setter Method
	 * @param country
	 */
	public void setCountry(String country) {
		if(!(countryChecker(country))) {
			throw new IllegalArgumentException("Error: Invalid Country Code!");
		}
		this.countryCode = country;
	}
	
	/**
	 * Setter Method
	 * @param sYear
	 */
	public void setStartYear(int sYear) {
		this.startYear = sYear;
	}
	
	/**
	 * Setter Method
	 * @param eYear
	 */
	public void setEndYear(int eYear) {
		this.endYear = eYear;
	}

	/**
	 * Setter Method
	 * @param visualType
	 */
	public void setVisualType(String visualType) {
		if(bannedVisual.contains(visualType)) {
			throw new IllegalArgumentException("Error: Attempting to use invalid visualization!");
		}
		this.visualType = visualType;
	}
	
	
	public static void main(String[] args) { 
	}
	
}
