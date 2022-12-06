package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisRenewOutputVsConsump implements IAnalyser{
	
	private String renewableConsum = "EG.FEC.RNEW.ZS";
	private String renewableOutput = "EG.ELC.RNEW.ZS";
	
	private final String title = "Renewable Energy Consumption vs Renewable Energy Output";
	private final String renewableOutputTopic = "Renewable energy output (% of total energy output)";
	private final String renewableConsumTopic = "Renewable energy consumption (% of total energy consumed)";
	
	private HashMap<String, Double> renewableConsumData;
	private HashMap<String, Double> renewableEnergyData;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisRenewOutputVsConsump(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataConsumption() {
		renewableConsumData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, renewableConsum);
	}
	
	private void fetchDataOutput() {
		renewableEnergyData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, renewableOutput);
	}

	/**
	 * Calculate Renewable Consumption vs Renewable Output Analyser
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Renewable Energy Output vs Renewable Energy Consumption Analyser");
		
		this.userSelection = selection;
		
		this.fetchDataConsumption();
		this.fetchDataOutput();
		this.processData();	
		
		return result;
	}

	/**
	 * Process the data
	 */
	@Override
	public void processData() {
		this.result.addTitle(title);
		this.result.addTopic1(renewableConsumTopic);
		this.result.addTopic2(renewableOutputTopic);
		this.result.addData1(renewableConsumData);
		this.result.addData2(renewableEnergyData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Renewable Energy Consumption vs Renewable Energy Output";
	}
}
