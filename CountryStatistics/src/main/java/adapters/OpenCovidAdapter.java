package adapters;

import com.google.gson.JsonArray;

import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.OpenCovidFetcher;;

public class OpenCovidAdapter implements IAdapter {
	
	private Fetcher fetcher;
	
	/**
	 * Constructor method
	 */
	public OpenCovidAdapter() {
		fetcher = new OpenCovidFetcher();
	}

	/**
	 * Fetch data based on user provided data selection and analysis type
	 * and Implement fetching and converting XML to JSON format
	 * @param selection : data composed of country code, start year and end year 
	 * @param analysisTypeCode : type of analysis
	 * @return returns Object data object using the fetchData method
	 */
	@Override
	public Object fetchData(UserSelection selection, String analysisTypeCode) {
		JsonArray j = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		return j;
	}

}
