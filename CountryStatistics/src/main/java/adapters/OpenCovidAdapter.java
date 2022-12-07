package adapters;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import client.UserSelection;
import dataFetchers.IFetcher;
import dataFetchers.OpenCovidFetcher;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonDataParser;
import jsonDataParser.JsonParseOpenCovid;
import server.BusinessDataObject;;

public class OpenCovidAdapter implements IAdapter {
	
	private IFetcher fetcher;
	private JsonDataParser jsonParser;
	
	/**
	 * Constructor method
	 */
	public OpenCovidAdapter() {
		fetcher = new OpenCovidFetcher();
		this.jsonParser = new JsonDataParser();
	}

	/**
	 * Fetch data based on user provided data selection and analysis type
	 * and Implement fetching and converting XML to JSON format
	 * @param selection : data composed of country code, start year and end year 
	 * @param analysisTypeCode : type of analysis
	 * @return returns Object data object using the fetchData method
	 */
	public HashMap<String, Double> fetchData(BusinessDataObject selection, String analysisTypeCode) {
		String dataS = (String) fetcher.fetchData(selection, analysisTypeCode);
		
		JsonArray rawData = new JsonArray();
		
		rawData = new JsonParser().parse(dataS).getAsJsonObject().get("data").getAsJsonObject().get("cases").getAsJsonArray();

		this.jsonParser.setParser(new JsonParseOpenCovid(rawData));
		HashMap<String, Double> data = (HashMap<String, Double>) jsonParser.parse();
	
		return data;
	}
}