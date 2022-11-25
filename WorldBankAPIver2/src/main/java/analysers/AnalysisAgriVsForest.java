package analysers;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;

import adapters.TargetAdapter;
import client.UserSelection;
import jsonDataParser.JsonParseRetrivedData;
import jsonDataParser.JsonParser;
import results.Result;
import results.*;

public class AnalysisAgriVsForest implements IAnalyser{
	
	private final String agriCode = "AG.LND.AGRI.ZS";
	private final String forestCode = "AG.LND.FRST.ZS";
	
	private JsonArray agricultureDataJson;
	private JsonArray forestDataJson;
	
	private JsonParser jsonParser;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	
	public AnalysisAgriVsForest(TargetAdapter adapter) {
		jsonParser = new JsonParser();
		this.fetcherAdapter = adapter;
	}
	
	private void fetchDataAgri() {
		this.agricultureDataJson = (JsonArray) fetcherAdapter.fetchData(userSelection, agriCode);
	}
	
	private void fetchDataForest() {
		this.forestDataJson = (JsonArray) fetcherAdapter.fetchData(userSelection, forestCode);
	}

	@Override
	public Result calculate(UserSelection selection) {
		System.out.println("Calculating using Agriculture vs Forest Analyser");
		
		this.userSelection = selection;
		
		this.fetchDataAgri();
		this.fetchDataForest();
		
		
		Result result = this.processData();	
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Result processData() {
		jsonParser.setParser(new JsonParseRetrivedData(agricultureDataJson));
		HashMap<String, Double> agricultureData = (HashMap<String, Double>) jsonParser.parse();
		
		jsonParser.setParser(new JsonParseRetrivedData(forestDataJson));
		HashMap<String, Double> forestData = (HashMap<String, Double>) jsonParser.parse();
		
		TwoSeriesResult result = new TwoSeriesResult();
		result.addData1(agricultureData);
		result.addData2(forestData);
		
		return result;
	}
	
	public String toString() {
		return "Agriculture vs Forest";
	}
}