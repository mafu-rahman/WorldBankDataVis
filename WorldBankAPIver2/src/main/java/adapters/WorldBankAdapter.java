package adapters;

import com.google.gson.JsonArray;

import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.worldBankFetcher;

public class WorldBankAdapter implements TargetAdapter{
	
	private Fetcher fetcher;
	
	public WorldBankAdapter() {
		fetcher = new worldBankFetcher();
	}

	/**
	 * Fetch data based on user provided data selection and analysis type
	 * and Implement fetching and converting XML to JSON format
	 * @param selection data composed of country code, start year and end year 
	 * @param analysisTypeCode type of analysis
	 * @return Object data object returned using the fetchData method
	 */
	public JsonArray fetchData(UserSelection selection, String analysisTypeCode) {
		
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		
		return j;
		
		
	}

}
