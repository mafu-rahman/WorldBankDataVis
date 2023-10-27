package analysers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonDataParser;
import results.Result;
import results.TwoSeriesResult;
import server.BusinessDataObject;

public class AnalysisTotalPopVsGDP implements IAnalyser{
	
	private final String totalPopCode = "SP.POP.TOTL";
	private final String gdpCode = "NY.GDP.MKTP.KD.ZG";
	
	private final String title = "Total Population vs GDP Growth";
	private final String totalPopTopic = "Total Population";
	private final String gdpTopic = "GDP Growth";
	
	private HashMap<String, Double> totalPopData;
	private HashMap<String, Double> gdpData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
		
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisTotalPopVsGDP (IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	/**
	 * Calculate Total Population vs GDP Growth
	 * @return result calculated result
	 */
	@Override
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculated using Total Population vs GDP Growth");
		
		this.theData = data;		
		this.fetchDataTotalPop();
		this.fetchDataGDP();
		this.processData();	
		
		return result;
	}
	
	private void fetchDataTotalPop() {
		totalPopData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, totalPopCode);
	}
	
	private void fetchDataGDP() {
		gdpData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, gdpCode);
	}

	/**
	 * Process the data
	 */
	@Override
	public void processData() {
		
		//processing the data
		for (Map.Entry<String, Double> entry : totalPopData.entrySet()) {
		   String key = entry.getKey();
		   Double value = entry.getValue();
		   
		   value = value / Math.pow(10, 8);
		   totalPopData.replace(key, value);		   
		}
		
		result.addType("Two Series");
		this.result.addTitle(title);
		this.result.addTopic1(totalPopTopic);
		this.result.addTopic2(gdpTopic);
		this.result.addData1(totalPopData);
		this.result.addData2(gdpData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Total Population vs GDP Growth";
	}
}