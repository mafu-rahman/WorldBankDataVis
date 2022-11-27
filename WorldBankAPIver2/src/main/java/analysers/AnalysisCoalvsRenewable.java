package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisCoalvsRenewable implements IAnalyser{
	
	private String coalCode = "EG.ELC.COAL.ZS";
	private String renewableCode = "EG.ELC.RNEW.ZS";
	
	private final String title = "Coal Energy vs Renewable Energy";
	private final String electricityToipic = "Electricity production from coal sources (% of total)";
	private final String renewableTopic = "Renewable electricity output (% of total electricity output)";
	
	private JsonArray coalEnergyDataJSON;
	private JsonArray renewableEnergyDataJSON;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisCoalvsRenewable(TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataCoal() {
		coalEnergyDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, coalCode);
	}
	
	private void fetchDataRenewable() {
		renewableEnergyDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, renewableCode);
	}

	/**
	 * Calculate Coal vs Renewable Analyser
	 * @return result calculated result
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
	 * Process the data
	 */
	@SuppressWarnings("unchecked")
	@Override
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
	 * toString() method
	 */
	public String toString() {
		return "Coal Energy vs Renewable Energy";
	}
}