package analysers;

import java.util.HashMap;
import adapters.IAdapter;
import jsonDataParser.JsonDataParser;
import results.Result;
import results.TwoSeriesResult;
import server.BusinessDataObject;

public class AnalysisFossilVsRenew implements IAnalyser{
	
	private String fossilFuelCode = "EG.USE.COMM.FO.ZS";
	private String renewableCode = "EG.FEC.RNEW.ZS";
	
	private final String title = "Fossil Fuel Consumption vs Renewable Energy Consumption";
	private final String fossilFuelTopic = "Fossil Fuel Consumption (% of fossil fuel consumed)";
	private final String renewableTopic = "Renewable Energy Consumption output (% of total energy consumed)";
	
	private HashMap<String, Double> fossilFuelData;
	private HashMap<String, Double> renewableEnergyData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
		
	private JsonDataParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisFossilVsRenew (IAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonDataParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataCoal() {
		fossilFuelData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, fossilFuelCode);
	}
	
	private void fetchDataRenewable() {
		renewableEnergyData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, renewableCode);
	}

	/**
	 * Calculate Fossil Fuel Consumption vs Renewable Energy Consumption
	 * @return result calculated result
	 */
	@Override
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculated using Fossil Fuel Consumption vs Renewable Energy Consumption");
		
		this.theData = data;
		
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
		result.addType("Two Series");
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