package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisTotalPopvsGDPGrowth implements IAnalyser{
	
	private String totalPopCode = "SP.POP.TOTL";
	private String gdpCode = "NY.GDP.MKTP.KD.ZG";
	
	private final String title = "Total Population vs GDP Growth";
	private final String totalPopTopic = "Total Population";
	private final String gdpTopic = "GDP Growth";
	
	private JsonArray totalPopDataJSON;
	private JsonArray gdpDataJSON;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisTotalPopvsGDPGrowth (TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataTotalPop() {
		totalPopDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, totalPopCode);
	}
	
	private void fetchDataGDP() {
		gdpDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, gdpCode);
	}

	/**
	 * Calculate Total Population vs GDP Growth
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Total Population vs GDP Growth");
		
		this.userSelection = selection;
		
		this.fetchDataTotalPop();
		this.fetchDataGDP();
		this.processData();	
		
		return result;
	}

	/**
	 * Process the data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processData() {
		jsonParser.setParser(new JsonParseRetrivedData(totalPopDataJSON));
		HashMap<String, Double> totalPopData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(gdpDataJSON));
		HashMap<String, Double> gdpData = (HashMap<String, Double>) jsonParser.parse();
		
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