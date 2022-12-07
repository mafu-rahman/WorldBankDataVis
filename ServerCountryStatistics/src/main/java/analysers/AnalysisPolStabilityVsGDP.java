package analysers;

import java.util.HashMap;
import adapters.IAdapter;
import results.Result;
import results.TwoSeriesResult;
import server.BusinessDataObject;

public class AnalysisPolStabilityVsGDP implements IAnalyser{ 
	
	private String polStabilityCode = "PV.EST";
	private String gdpCode = "ENY.GDP.MKTP.KD.ZG";
	
	private final String title = "Political Stability vs GDP Growth";
	private final String polStabilityTopic = "Heat Index";
	private final String gdpTopic = "CO2 Emissions";
	
	private HashMap<String, Double> polStabilityData;
	private HashMap<String, Double> gdpData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;		
	
	private TwoSeriesResult result;
	
	/**
	 * Constructor method
	 * @param adapter
	 */
	public AnalysisPolStabilityVsGDP(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	private void fetchPolStability() {
		polStabilityData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, polStabilityCode);
	}
	
	private void fetchGDPGrowth() {
		gdpData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, gdpCode);
	}

	/**
	 * Calculate Political Stability vs GDP Growth
	 * @return result calculated result
	 */
	@Override
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculated using Political Stability vs GDP Growth");
		
		this.theData = data;
		
		this.fetchPolStability();
		this.fetchGDPGrowth();
		this.processData();	
		
		return result;
	}

	/**
	 * Process the data
	 */
	@Override
	public void processData() {
		result.addType("Two Series");
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