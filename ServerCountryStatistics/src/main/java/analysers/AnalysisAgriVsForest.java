package analysers;

import java.util.HashMap;

import adapters.IAdapter;
import results.*;
import server.BusinessDataObject;

public class AnalysisAgriVsForest implements IAnalyser{
	
	private final String agriCode = "AG.LND.AGRI.ZS";
	private final String forestCode = "AG.LND.FRST.ZS";
	
	private final String title = "Agricultral land vs Forest Land";
	private final String agriToipic = "Agricultural land (% of land area)";
	private final String forestTopic = "Forest land (% of land area)";

	private HashMap<String, Double> agricultureData;
	private HashMap<String, Double> forestData;
	
	private IAdapter fetcherAdapter;
	private BusinessDataObject theData;
		
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
	public Result calculate(BusinessDataObject data) {
		System.out.println("Calculating using Agriculture vs Forest Analyser");
		
		this.theData = data;
		
		this.fetchDataForest();
		this.fetchDataAgri();
		this.processData();	
		
		return result;
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	@SuppressWarnings("unchecked")
	private void fetchDataAgri() {
		this.agricultureData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, agriCode);
	}
	
	/**
	 * Fetching data, calling the appropriate adapter
	 */
	@SuppressWarnings("unchecked")
	private void fetchDataForest() {
		this.forestData = (HashMap<String, Double>) fetcherAdapter.fetchData(theData, forestCode);
	}
	
	/**
	 * This method processes the retrieved data if required for it to be viewed to the users.
	 * It parses the JSON data and converts into a HashMap object of key value pairs. 
	 */
	@SuppressWarnings("unchecked")
	public void processData() {
		/*
		 * Process the data here
		 * then add to array list
		 */
		
		result.addType("Two Series");
		result.addTitle(title);
		result.addTopic1(agriToipic);
		result.addTopic2(forestTopic);
		result.addData1(agricultureData);
		result.addData2(forestData);
	}
	
	/**
	 * toString() method
	 */
	public String toString() {
		return "Agriculture vs Forest";
	}
}