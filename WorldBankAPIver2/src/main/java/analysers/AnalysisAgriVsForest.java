package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;
import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.*;

public class AnalysisAgriVsForest implements IAnalyser{
	
	private final String agriCode = "AG.LND.AGRI.ZS";
	private final String forestCode = "AG.LND.FRST.ZS";
	
	private final String title = "Agricultral land vs Forest Land";
	private final String agriToipic = "Agricultural land (% of land area)";
	private final String forestTopic = "Forest land (% of land area)";

	private JsonArray agricultureDataJson;
	private JsonArray forestDataJson;
	
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	
	/**
	 * Constructor Method
	 * @param adapter
	 */
	public AnalysisAgriVsForest(TargetAdapter adapter) {
		this.fetcherAdapter = adapter;

		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	private void fetchDataAgri() {
		this.agricultureDataJson = (JsonArray) fetcherAdapter.fetchData(userSelection, agriCode);
	}
	
	private void fetchDataForest() {
		this.forestDataJson = (JsonArray) fetcherAdapter.fetchData(userSelection, forestCode);
	}

	/**
	 * Calculates Agriculture vs Forest Analyser
	 * @return result calculated data
	 */
	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculating using Agriculture vs Forest Analyser");
		
		this.userSelection = selection;
		
		this.fetchDataAgri();
		this.fetchDataForest();
		this.processData();	
		
		return result;
	}
	
	/**
	 * Process the data via parsing
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		jsonParser.setParser(new JsonParseRetrivedData(agricultureDataJson));
		HashMap<String, Double> agricultureData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(forestDataJson));
		HashMap<String, Double> forestData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(agriToipic);
		this.result.addTopic2(forestTopic);
		this.result.addData1(agricultureData);
		this.result.addData2(forestData);	
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Agriculture vs Forest";
	}
}