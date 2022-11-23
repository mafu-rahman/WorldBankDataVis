package analysers;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import results.Result;

public class AnalysisAgriVsForest implements IAnalyser{
	
	private String agriCode;
	private String forestCode;
	
	private JsonArray agricultureData;
	private JsonArray forestData;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	private int fromYear;
	private int toYear;
	
	public AnalysisAgriVsForest(UserSelection selection, TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.userSelection = selection;
	}
	
	private void fetchDataAgri() {
		agricultureData = (JsonArray) fetcherAdapter.fetchData(userSelection, agriCode);
	}
	
	private void fetchDataForest() {
		forestData = (JsonArray) fetcherAdapter.fetchData(userSelection, forestCode);
	}

	@Override
	public Result calculate() {
		System.out.println("Calculated using Agriculture vs Forest Analyser");
		return null;
	}
	
	public String toString() {
		return "Agriculture vs Forest";
	}
}