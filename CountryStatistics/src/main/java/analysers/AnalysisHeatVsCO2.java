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

public class AnalysisHeatVsCO2 implements IAnalyser{ 
	
	private String heatIndexCode = "EN.CLC.HEAT.XD";
	private String co2EmissionCode = "EN.ATM.CO2E.PC";
	
	private final String title = "Heat Index vs CO2 Emissions";
	private final String heatIndexTopic = "Heat Index";
	private final String co2EmissionTopic = "CO2 Emissions";
	
	private HashMap<String, Double> heatIndexData;
	private HashMap<String, Double> co2EmissionData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
			
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisHeatVsCO2(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataHeatIndex() {
		heatIndexData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, heatIndexCode);
	}
	
	private void fetchDataCO2Emissions() {
		co2EmissionData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, co2EmissionCode);
	}

	/**
	 * Calculate Heat Index vs CO2 Emissions
	 * @return result calculated result
	 */
	@Override
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculated using Heat Index vs CO2 Emissions");
		
		this.theData = data;
		
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
		result.addType("Two Series");
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