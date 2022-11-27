package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisHeatIndexvsCO2Emission implements IAnalyser{ 
	
	private String heatIndexCode = "EN.CLC.HEAT.XD";
	private String co2EmissionCode = "EN.ATM.CO2E.PC";
	
	private final String title = "Heat Index vs CO2 Emissions";
	private final String heatIndexTopic = "Heat Index";
	private final String co2EmissionTopic = "CO2 Emissions";
	
	private JsonArray heatIndexDataJSON;
	private JsonArray co2EmissionDataJSON;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisHeatIndexvsCO2Emission(TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataHeatIndex() {
		heatIndexDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, heatIndexCode);
	}
	
	private void fetchDataCO2Emissions() {
		co2EmissionDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, co2EmissionCode);
	}

	/**
	 * Calculate Heat Index vs CO2 Emissions
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Heat Index vs CO2 Emissions");
		
		this.userSelection = selection;
		
		this.fetchDataHeatIndex();
		this.fetchDataCO2Emissions();
		this.processData();	
		
		return result;
	}

	/**
	 * Process the data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processData() {
		jsonParser.setParser(new JsonParseRetrivedData(heatIndexDataJSON));
		HashMap<String, Double> heatIndexData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(co2EmissionDataJSON));
		HashMap<String, Double> co2EmissionData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(heatIndexTopic);
		this.result.addTopic2(co2EmissionTopic);
		this.result.addData1(heatIndexData);
		this.result.addData2(co2EmissionData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Heat Index vs CO2 Emissions";
	}
}