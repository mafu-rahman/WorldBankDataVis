package adapters;


import com.google.gson.JsonArray;
import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.UnitedNationsFetcher;

public class UnitedNationsAdapter implements IAdapter{
	
	private Fetcher fetcher;
	
	public UnitedNationsAdapter() {
		fetcher = new UnitedNationsFetcher();
	}

	/**
	 * Calls the appropriate fetcher and converts/processes the data if required.
	 * Implement fetching and converting XML to JSON format
	 */
	
	public Object fetchData(UserSelection selection, String analysisTypeCode) {
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		return j;
		
		
	}

}
