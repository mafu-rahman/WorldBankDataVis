package analysers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonDataParser;
import results.OneSeriesResult;
import results.Result;
import server.BusinessDataObject;

public class AnalysisCovidCases implements IAnalyser {
	
	private final String title = "Covid Cases Each Year";
	
	private HashMap<String, Double> tempData;
	private HashMap<String, Double> finalCovidData;

	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
		
	private OneSeriesResult result;
	
	public AnalysisCovidCases(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		finalCovidData = new HashMap<>();
		this.result = new OneSeriesResult();
	}
	
	/**
	 * This method gets the user selection as a parameter and calls the appropriate method
	 * to fetch the data for this analyser
	 * @return result : it stores the processed data in a result object
	 */
	public Result calculate(BusinessDataObject data) {
		System.out.println("Displaying Covid Cases via OpenCovid API");
		
		this.theData = data;
		
		this.fetchDataCovid();
		this.processData();
		
		return result;
	}
	
	/**
	 * Fetch data via the appropriate adapter
	 */
	private void fetchDataCovid() {
		int fromYear = Integer.parseInt(theData.getFromYear());
		int toYear = Integer.parseInt(theData.getToYear());
		
		for(int i=fromYear; i<=toYear; i++) {
			String year = i + "";
			theData.setFromYear(year);
			theData.setToYear(year);
			
			this.tempData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, title);
			
			Double value = 0.0;
			for (Map.Entry<String, Double> entry : tempData.entrySet()) {
				   value += entry.getValue();
				}
			this.finalCovidData.put(year, value);
		}
		
	}

	/**
	 * Process the retrieved data from OpenCovid and parse
	 * the JSON data
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		/*
		 * Process the data here
		 * then add to array list
		 */
		
		this.result.addType("One Series");
		this.result.addTitle(title);
		this.result.addTopic1(title);
		this.result.addData1(finalCovidData);
	}
	
	
	
	/**
	 * @return String value
	 */
	public String toString() {
		return "Covid Cases";
	}

}
