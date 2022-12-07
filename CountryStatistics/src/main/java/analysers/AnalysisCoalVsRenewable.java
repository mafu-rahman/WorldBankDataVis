package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonDataParser;
import results.Result;
import results.TwoSeriesResult;
import server.BusinessDataObject;

public class AnalysisCoalVsRenewable implements IAnalyser{
	
	private String coalCode = "EG.ELC.COAL.ZS";
	private String renewableCode = "EG.ELC.RNEW.ZS";
	
	private final String title = "Coal Energy vs Renewable Energy";
	private final String electricityToipic = "Electricity production from coal sources (% of total)";
	private final String renewableTopic = "Renewable electricity output (% of total electricity output)";
	
	private HashMap<String, Double> coalEnergyData;
	private HashMap<String, Double> renewableEnergyData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
			
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisCoalVsRenewable(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	/**
	 * This method gets the userselection as a parameter and calls the appropriate method
	 * to fetch the data for this analyser
	 * @return result : it stores the processed data in a result object
	 */
	@Override
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculated using Coal vs Renewable Analyser");
		
		this.theData = data;
		
		this.fetchDataCoal();
		this.fetchDataRenewable();
		this.processData();	
		
		return result;
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataCoal() {
		coalEnergyData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, coalCode);
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataRenewable() {
		renewableEnergyData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, renewableCode);
	}

	/**
	 * This method processes the retrived data if required for it to be viewed to the users.
	 * It parses the JSON data and converts into a HashMap object of key value pairs. 
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		this.result.addType("Two Series");
		this.result.addTitle(title);
		this.result.addTopic1(electricityToipic);
		this.result.addTopic2(renewableTopic);
		this.result.addData1(coalEnergyData);
		this.result.addData2(renewableEnergyData);
		
	}
	
	public String toString() {
		return "Coal Energy vs Renewable Energy";
	}
}