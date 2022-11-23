package adapters;

import com.google.gson.JsonArray;

import analysers.IAnalyser;
import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.unitedNationsFetcher;
import dataFetchers.worldBankFetcher;

public class UnitedNationsAdapter implements TargetAdapter{
	
	private Fetcher fetcher;
	
	public UnitedNationsAdapter() {
		fetcher = new unitedNationsFetcher();
	}

	public Object fetchData(UserSelection selection, String analysisTypeCode) {
		return null;
		
		
	}

}
