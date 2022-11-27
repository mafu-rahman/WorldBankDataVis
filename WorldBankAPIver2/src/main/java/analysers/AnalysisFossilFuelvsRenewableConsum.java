package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisFossilFuelvsRenewableConsum implements IAnalyser{
	
	private String fossilFuelCode = "EG.USE.COMM.FO.ZS";
	private String renewableCode = "EG.FEC.RNEW.ZS";
	
	private final String title = "Fossil Fuel Consumption vs Renewable Energy Consumption";
	private final String fossilFuelTopic = "Fossil Fuel Consumption (% of fossil fuel consumed)";
	private final String renewableTopic = "Renewable Energy Consumption output (% of total energy consumed)";
	
	private JsonArray fossilFuelDataJSON;
	private JsonArray renewableEnergyDataJSON;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisFossilFuelvsRenewableConsum (TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataCoal() {
		fossilFuelDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, fossilFuelCode);
	}
	
	private void fetchDataRenewable() {
		renewableEnergyDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, renewableCode);
	}

	/**
	 * Calculate Fossil Fuel Consumption vs Renewable Energy Consumption
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Fossil Fuel Consumption vs Renewable Energy Consumption");
		
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
		jsonParser.setParser(new JsonParseRetrivedData(fossilFuelDataJSON));
		HashMap<String, Double> fossilFuelData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(renewableEnergyDataJSON));
		HashMap<String, Double> renewableEnergyData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(fossilFuelTopic);
		this.result.addTopic2(renewableTopic);
		this.result.addData1(fossilFuelData);
		this.result.addData2(renewableEnergyData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Fossil Fuel Consumption vs Renewable Energy Consumption";
	}
}