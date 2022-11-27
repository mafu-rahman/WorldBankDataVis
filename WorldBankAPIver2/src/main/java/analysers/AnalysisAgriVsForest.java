package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;
import adapters.IAdapter;
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

	private JsonArray agricultureDataJSON;
	private JsonArray forestDataJSON;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	private JsonParser jsonParser;
	
	private TwoSeriesResult result;
	
	public AnalysisAgriVsForest(IAdapter adapter) {
		this.fetcherAdapter = adapter;

		this.jsonParser = new JsonParser();
		this.result = new TwoSeriesResult();
	}
	
	/**
	 * This method gets the userselection as a parameter and calls the appropriate method
	 * to fetch the data for this analyser
	 * @return result : it stores the processed data in a result object
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
	 * This method processes the retrived data if required for it to be viewed to the users.
	 * It parses the JSON data and converts into a HashMap object of key value pairs. 
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		this.jsonParser.setParser(new JsonParseRetrivedData(agricultureDataJSON));
		HashMap<String, Double> agricultureData = (HashMap<String, Double>) jsonParser.parse();
		
		this.jsonParser.setParser(new JsonParseRetrivedData(forestDataJSON));
		HashMap<String, Double> forestData = (HashMap<String, Double>) jsonParser.parse();
		
		this.result.addTitle(title);
		this.result.addTopic1(agriToipic);
		this.result.addTopic2(forestTopic);
		this.result.addData1(agricultureData);
		this.result.addData2(forestData);	
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataAgri() {
		this.agricultureDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, agriCode);
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataForest() {
		this.forestDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, forestCode);
	}
	
	public String toString() {
		return "Agriculture vs Forest";
	}
}