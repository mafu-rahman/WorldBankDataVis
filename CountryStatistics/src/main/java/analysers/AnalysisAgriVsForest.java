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

	private HashMap<String, Double> agricultureData;
	private HashMap<String, Double> forestData;
	
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private TwoSeriesResult result;
	
	/**
	 * Constructor Method
	 * @param adapter
	 */
	public AnalysisAgriVsForest(IAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.result = new TwoSeriesResult();
	}
	
	/**
	 * This method gets the user selection as a parameter and calls the appropriate method
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
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataAgri() {
		this.agricultureData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, agriCode);
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	private void fetchDataForest() {
		this.forestData = (HashMap<String, Double>) fetcherAdapter.fetchData(userSelection, forestCode);
	}
	
	/**
	 * This method processes the retrieved data if required for it to be viewed to the users.
	 * It parses the JSON data and converts into a HashMap object of key value pairs. 
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		
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