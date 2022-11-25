package adapters;


import com.google.gson.JsonArray;
import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.unitedNationsFetcher;

public class UnitedNationsAdapter implements TargetAdapter{
	
	private Fetcher fetcher;
	
	public UnitedNationsAdapter() {
		fetcher = new unitedNationsFetcher();
	}

	/**
	 * Implement fetching and converting XML tgo JSON format
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode) {
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		return j;
		
		
	}

}
