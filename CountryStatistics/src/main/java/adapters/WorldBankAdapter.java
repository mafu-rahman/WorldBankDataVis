package adapters;

import java.util.HashMap;

import com.google.gson.JsonArray;

import client.UserSelection;
import dataFetchers.IFetcher;
import dataFetchers.WorldBankFetcher;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;

public class WorldBankAdapter implements IAdapter{
	
	private IFetcher fetcher;
	private JsonParser jsonParser;
	
	/**
	 * Constructor Method
	 */
	public WorldBankAdapter() {
		this.fetcher = new WorldBankFetcher();
		this.jsonParser = new JsonParser();
	}

	/**
	 * Fetch data based on user provided data selection and analysis type
	 * and Implement fetching and converting XML to JSON format
	 * @param selection : data composed of country code, start year and end year 
	 * @param analysisTypeCode : type of analysis
	 * @return returns Object data object returned using the fetchData method
	 */
	public HashMap<String, Double> fetchData(UserSelection selection, String analysisTypeCode) {
		
		JsonArray rawData = (JsonArray) fetcher.fetchData(selection, analysisTypeCode);
		this.jsonParser.setParser(new JsonParseRetrivedData(rawData));
		HashMap<String, Double> data = (HashMap<String, Double>) jsonParser.parse();
		
		return data;
	}
}