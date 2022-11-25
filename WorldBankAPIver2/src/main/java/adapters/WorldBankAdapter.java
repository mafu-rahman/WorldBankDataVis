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

	public JsonArray fetchData(UserSelection selection, String analysisTypeCode) {
		
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		
		return j;
		
		
	}

}
