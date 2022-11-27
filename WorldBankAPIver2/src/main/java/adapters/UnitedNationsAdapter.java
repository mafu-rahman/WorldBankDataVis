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
	 * Fetch data based on user provided data selection and analysis type
	 * and Implement fetching and converting XML to JSON format
	 * @param selection data composed of country code, start year and end year 
	 * @param analysisTypeCode type of analysis
	 * @return Object data object returned using the fetchData method
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode) {
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		return j;
	}

}
