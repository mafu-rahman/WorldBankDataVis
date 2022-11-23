package analysers;

import com.google.gson.JsonArray;

import adapters.TargetAdapter;
import client.UserSelection;
import results.Result;

public class AnalysisCoalvsRenewable implements IAnalyser{
	
	private String coalCode;
	private String renewableCode;
	
	private JsonArray coalEnergyData;
	private JsonArray renewableEnergyData;
	
	private TargetAdapter fetcherAdapter;
	private UserSelection userSelection;
		
	private int fromYear;
	private int toYear;
	
	public AnalysisCoalvsRenewable(UserSelection selection, TargetAdapter adapter) {
		this.fetcherAdapter = adapter;
		this.userSelection = selection;
	}
	
	private void fetchDataCoal() {
		coalEnergyData = (JsonArray) fetcherAdapter.fetchData(userSelection, coalCode);
	}
	
	private void fetchDataRenewable() {
		renewableEnergyData = (JsonArray) fetcherAdapter.fetchData(userSelection, renewableCode);
	}

	@Override
	public Result calculate() {
		System.out.println("Calculated using Coal vs Renewable Analyser");
		return null;
	}
	
	public String toString() {
		return "Coal Energy vs Renewable Energy";
	}
}