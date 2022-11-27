package adapters;

import com.google.gson.JsonArray;

import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.WorldBankFetcher;

public class WorldBankAdapter implements TargetAdapter{
	
	private Fetcher fetcher;
	
	public WorldBankAdapter() {
		fetcher = new WorldBankFetcher();
	}

	/**
	 * Calls the appropriate fetcher and converts/processes the data if required.
	 */
	public JsonArray fetchData(UserSelection selection, String analysisTypeCode) {
		
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		
		return j;
		
		
	}

}
