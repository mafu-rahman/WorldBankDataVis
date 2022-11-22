package analysis;

import com.google.gson.JsonArray;

import dataFetchers.Fetcher;

public class AnalysisCoalvsRenewable {
	
	private String topic;
	private String coalCode;
	private String renewableCode;
	private JsonArray coalEnergyData;
	private JsonArray renewableEnergyData;
	
	private Fetcher fetcher;
	
	private int fromYear;
	private int toYear;
	
	public AnalysisCoalvsRenewable(int fromYear, int toYear) {
		
	}
	
	private void fetchDataCoal() {
		fetcher.fetchData(coalCode);
	}
	
	private void fetchDataRenewable() {
		fetcher.fetchData(renewableCode);
	}
}