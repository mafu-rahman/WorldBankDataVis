package httpTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Fetcher {
	
	private static String[] CODES = {"CAN", "UK", "USA", "CN", "BRA"};
	
	private static String[] ANALYSIS_TYPES = {"SP.POP.TOTL", 				// Total population
											  "EN.ATM.CO2E.PC",				// CO2 Emissions 
											  "EN.ATM.PM25.MC.M3", 			// PM2.5 air pollution, mean annual exposure
											  "AG.LND.FRST.ZS",				// Forest Area (% of land area)
											  "EG.USE.PCAP.KG.OE",			// Energy Use (kg of oil equivalent per capita)
											  "NY.GDP.PCAP.CD",				// GDP Per Capita 
											  "SH.MED.BEDS.ZS",				// Hospital Beds (per 1000 people)
											  "SE.XPD.TOTL.GD.ZS"};			// Govt expenditure on education, total (% GDP)
		
	private String countryCode;
	private int startYear;
	private int endYear;
	
	public Fetcher() {}
	
	public Fetcher(String countryCode, int sYear, int eYear) {
		if(countryChecker(countryCode)) {
			this.countryCode = countryCode;
		}
		if(dateChecker(sYear, eYear)) {
			this.startYear = sYear;
			this.endYear = eYear;
		}
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
		if(sYear < eYear) {
			System.out.println(String.format("Valid Dates found: %d, %d", sYear, eYear));
			return true;
		}
		return false;
	}
	
	
	public void fetchData() {
		String country = this.countryCode;
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/SP.POP.TOTL?date=%d:%d&format=json", country, this.startYear, this.endYear);
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
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				int year;
				for (int i = 0; i < sizeOfResults; i++) {
					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						popYear = 0;
					else
						popYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsInt();

					System.out.println("Population for " + country + " in " + year + " is " + popYear);
					cumPop = cumPop + popYear;
				}
				System.out.println(
						"The average population over the selected years is " + cumPop / sizeOfResults);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
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
		Fetcher fetcher = new Fetcher("BRA", 2000, 2001);
		fetcher.fetchData();
	}
	
}
