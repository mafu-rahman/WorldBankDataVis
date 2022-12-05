package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisCoalEnergyvsRenewableOutput implements IAnalyser{
	
	private String coalCode = "EG.ELC.COAL.ZS";
	private String renewableCode = "EG.ELC.RNEW.ZS";
	
	private final String title = "Coal Energy vs Renewable Energy";
	private final String electricityToipic = "Electricity production from coal sources (% of total)";
	private final String renewableTopic = "Renewable electricity output (% of total electricity output)";
	
	private JsonArray coalEnergyDataJSON;
	private JsonArray renewableEnergyDataJSON;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisCoalEnergyvsRenewableOutput(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	/**
	 * This method gets the userselection as a parameter and calls the appropriate method
	 * to fetch the data for this analyser
	 * @return result : it stores the processed data in a result object
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Coal vs Renewable Analyser");
		
		this.userSelection = selection;
		
		this.fetchDataCoal();
		this.fetchDataRenewable();
		this.processData();	
		
		return result;
	}

	/**
	 * This method processes the retrived data if required for it to be viewed to the users.
	 * It parses the JSON data and converts into a HashMap object of key value pairs. 
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		jsonParser.setParser(new JsonParseRetrivedData(coalEnergyDataJSON));
		HashMap<String, Double> coalEnergyData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(renewableEnergyDataJSON));
		HashMap<String, Double> renewableEnergyData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(electricityToipic);
		this.result.addTopic2(renewableTopic);
		this.result.addData1(coalEnergyData);
		this.result.addData2(renewableEnergyData);
		
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataCoal() {
		coalEnergyDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, coalCode);
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataRenewable() {
		renewableEnergyDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, renewableCode);
	}
	
	public String toString() {
		return "Coal Energy vs Renewable Energy";
	}
}