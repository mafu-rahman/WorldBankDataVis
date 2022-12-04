package analysers;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import client.UserSelection;
import jsonDataParser.JsonParser;
import results.Result;

public class DisplayCovidCases implements IAnalyser {
	
	private final String title = "Covid Cases";
	
	private JsonArray covidDataJSON;
	private IAdapter fetcherAdapter;
	private UserSelection userSelection;
	
	private JsonParser jsonParser;
	
	@Override
	public Result calculate(UserSelection selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processData() {
		// TODO Auto-generated method stub

	}
	
	private void fetchDataCovid() {
		this.covidDataJSON = (JsonArray) fetcherAdapter.fetchData(userSelection, title);
	}

}
