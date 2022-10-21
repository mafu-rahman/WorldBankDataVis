package dataFetchers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.xml.crypto.dsig.keyinfo.PGPData;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import analyser.*;

public class Fetcher {
	
	
	private String countryCode;
	private int startYear;
	private int endYear;
	private String analysisType;
	// Country Codes
	private static String[] CODES = {"CAN", "GBR", "USA", "CN", "BRA"};
	// Data Sets
	private static String[] ANALYSIS_TYPES = {"SP.POP.TOTL", 				// Total population
											  "EN.ATM.CO2E.PC",				// CO2 Emissions 
											  "EN.ATM.PM25.MC.M3", 			// PM2.5 air pollution, mean annual exposure
											  "AG.LND.FRST.ZS",				// Forest Area (% of land area)
											  "EG.USE.PCAP.KG.OE",			// Energy Use (kg of oil equivalent per capita)
											  "NY.GDP.PCAP.CD",				// GDP Per Capita 
											  "SH.MED.BEDS.ZS",				// Hospital Beds (per 1000 people)
											  "SH.XPD.CHEX.PC.CD"};			// Current health expenditure per capita (current US$)
		
	/*
	 * Constructor
	 */
	public Fetcher(String countryCode, String analyseType, int sYear, int eYear) {
		if(countryChecker(countryCode)) {
			this.countryCode = countryCode;
		}
		if(dateChecker(sYear, eYear)) {
			this.startYear = sYear;
			this.endYear = eYear;
		}
		if(analysisChecker(analyseType)) {
			this.analysisType = analyseType;
		}
	}
	
	/*
	 * check if analysisType, start year, end year, and countryCode are valid
	 */
	private boolean analysisChecker(String analyse) {
		for(String s : ANALYSIS_TYPES) {
			if(s.equals(analyse)) {
				System.out.println(String.format("Valid analysis code found: %s", analyse));
				return true;
			}
		}
		return false;
	}
	
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
	 * fetch data from world bank API and then analyze according to dataset type
	 */
	public void fetchData() {
		String country = this.countryCode;
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", country, this.analysisType, this.startYear, this.endYear);
		System.out.println("Connecting to URL: " + urlString);
		int popYear = 0;
		int cumPop = 0;
		
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
				
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				
				
				//"SP.POP.TOTL",					//Total population
				if(this.analysisType.equals("SP.POP.TOTL")) {
					PopulationAnalyser analyser = new PopulationAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"EN.ATM.CO2E.PC",				// CO2 Emissions
				else if(this.analysisType.equals("EN.ATM.CO2E.PC")) {
					EmissionsAnalyser analyser = new EmissionsAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"EN.ATM.PM25.MC.M3", 			// PM2.5 air pollution, mean annual exposure
				else if(this.analysisType.equals("EN.ATM.PM25.MC.M3")) {
					AirPollutionAnalyser analyser = new AirPollutionAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"AG.LND.FRST.ZS",				// Forest Area (% of land area)
				else if(this.analysisType.equals("AG.LND.FRST.ZS")) {
					ForestAreaAnalyser analyser = new ForestAreaAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"EG.USE.PCAP.KG.OE",			// Energy Use (kg of oil equivalent per capita)
				else if(this.analysisType.equals("EG.USE.PCAP.KG.OE")) {
					EnergyUseAnalyser analyser = new EnergyUseAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"NY.GDP.PCAP.CD",				// GDP Per Capita 
				else if(this.analysisType.equals("NY.GDP.PCAP.CD")) {
					GDPperCapitaAnalyser analyser = new GDPperCapitaAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"SH.MED.BEDS.ZS",				// Hospital Beds (per 1000 people)
				else if(this.analysisType.equals("SH.MED.BEDS.ZS")) {
					HospitalBedsAnalyser analyser = new HospitalBedsAnalyser(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}
				
				//"SE.XPD.TOTL.GD.ZS"};			// Govt expenditure on education, total (% GDP)
				else if(this.analysisType.equals("SH.XPD.CHEX.PC.CD")) {
					HealthExpensePerCapita analyser = new HealthExpensePerCapita(jsonArray, sizeOfResults, country);
					analyser.computeAvg(jsonArray, sizeOfResults);
				}				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public String getAnalysisType() {
		return this.analysisType;
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
	
	public void setAnalysisType(String analyse) {
		this.analysisType = analyse;
	}
	
	public static void main(String[] args) { 
			
			Fetcher fetcher = new Fetcher("USA", ANALYSIS_TYPES[7], 2000, 2006);
			fetcher.fetchData();
		
	}
	
}
