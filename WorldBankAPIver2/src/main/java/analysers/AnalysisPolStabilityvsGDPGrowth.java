package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.TwoSeriesResult;

public class AnalysisPolStabilityvsGDPGrowth implements IAnalyser{ 
	
	private String polStabilityCode = "PV.EST";
	private String gdpCode = "ENY.GDP.MKTP.KD.ZG";
	
	private final String title = "Political Stability vs GDP Growth";
	private final String polStabilityTopic = "Heat Index";
	private final String gdpTopic = "CO2 Emissions";
	
	private JsonArray polStabilityDataJSON;
	private JsonArray gdpDataJSON;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisPolStabilityvsGDPGrowth(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		
		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchPolStability() {
		polStabilityDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, polStabilityCode);
	}
	
	private void fetchGDPGrowth() {
		gdpDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, gdpCode);
	}

	/**
	 * Calculate Political Stability vs GDP Growth
	 * @return result calculated result
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculated using Political Stability vs GDP Growth");
		
		this.userSelection = selection;
		
		this.fetchPolStability();
		this.fetchGDPGrowth();
		this.processData();	
		
		return result;
	}

	/**
	 * Process the data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processData() {
		jsonParser.setParser(new JsonParseRetrivedData(polStabilityDataJSON));
		HashMap<String, Double> polStabilityData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(gdpDataJSON));
		HashMap<String, Double> gdpData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(polStabilityTopic);
		this.result.addTopic2(gdpTopic);
		this.result.addData1(polStabilityData);
		this.result.addData2(gdpData);
		
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Political Stability vs GDP Growth";
	}
}