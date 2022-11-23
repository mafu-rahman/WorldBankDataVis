package adapters;

import com.google.gson.JsonArray;

import analysers.IAnalyser;
import client.UserSelection;
import dataFetchers.Fetcher;

public interface TargetAdapter {
	
	public Object fetchData(UserSelection selection, String analysisTypeCode);
	

}
