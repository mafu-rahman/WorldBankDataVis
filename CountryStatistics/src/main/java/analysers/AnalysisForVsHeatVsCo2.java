package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.ThreeSeriesResult;

public class AnalysisForVsHeatVsCo2 implements IAnalyser{

	private String forestLandCode = "AG.LND.FRST.ZS";
	private String heatIndexCode = "EN.CLC.HEAT.XD";
	private String co2EmissionCode = "EN.ATM.CO2E.PC";
	
	private final String title = "Forest Land vs Heat Index vs CO2 Emissions";
	private final String forestLandTopic = "Forest Land";
	private final String heatIndexTopic = "Heat Index";
	private final String co2EmissionTopic = "CO2 Emissions";
	
	private HashMap<String, Double> forestLandData;
	private HashMap<String, Double> heatIndexData;
	private HashMap<String, Double> co2EmissionData;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
			
	private ThreeSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisForVsHeatVsCo2(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new ThreeSeriesResult();
	}
	
	/**
	 * Calculate Forest Land vs Heat Index vs CO2 Emissions
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Forest Land vs Heat Index vs CO2 Emissions");
		
		this.userSelection = selection;
		
		this.fetchDataForestLand();
		this.fetchDataHeatIndex();
		this.fetchDataCO2Emissions();
		this.processData();	
		
		return result;
	}
	
	private void fetchDataForestLand() {
		forestLandData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, forestLandCode);
	}
	
	private void fetchDataHeatIndex() {
		heatIndexData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, heatIndexCode);
	}
	
	private void fetchDataCO2Emissions() {
		co2EmissionData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, co2EmissionCode);
	}
	
	/**
	 * Process the data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processData() {
		
		this.result.addTitle(title);
		this.result.addTopic1(forestLandTopic);
		this.result.addTopic2(heatIndexTopic);
		this.result.addTopic3(co2EmissionTopic);
		this.result.addData1(forestLandData);
		this.result.addData2(heatIndexData);
		this.result.addData3(co2EmissionData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Forest Land vs Heat Index vs CO2 Emissions";
	}
}