package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.OneSeriesResult;
import results.Result;

public class DisplayCovidCases implements IAnalyser {
	
	private final String title = "Display Covid Cases";
	
	private JsonArray covidDataJSON;
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	private JsonParser jsonParser;
	
	private OneSeriesResult result;
	
	public DisplayCovidCases(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new OneSeriesResult();
	}
	
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Displaying Covid Cases via OpenCovid API");
		
		this.userSelection = selection;
		
		this.fetchDataCovid();
		
		return result;
	}

	/**
	 * Process the retrieved data from OpenCovid and parse
	 * the JSON data
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void processData() {
		this.jsonParser.setParser(new JsonParseRetrivedData(covidDataJSON));
		HashMap<String, Double> covidData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(title);
		this.result.addData1(covidData);
	}
	
	/**
	 * Fetch data via the appropriate adapter
	 */
	private void fetchDataCovid() {
		this.covidDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, title);
	}
	
	/**
	 * @return String value
	 */
	public String toString() {
		return "Covid Cases";
	}

}
