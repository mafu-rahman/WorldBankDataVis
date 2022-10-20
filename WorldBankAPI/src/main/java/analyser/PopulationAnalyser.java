package analyser;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class PopulationAnalyser extends Analyser {
	
	private int popYear;
	private int cumPop;
	private JsonArray jsonArray;
	private int sizeOfResults;
	private String country;
	private int mean;
	
	public PopulationAnalyser(JsonArray jsonArray, int sizeOfResults, String country) {
		this.cumPop = 0;
		this.popYear = 0;
		this.jsonArray = jsonArray;
		this.country = country;
		this.sizeOfResults = sizeOfResults;
	}
	
	public int computeAvg(JsonArray jsonArray, int sizeOfResults) {
		int year;
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				popYear = 0;
			else
				popYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsInt();

			System.out.println("Population for " + this.country + " in " + year + " is " + popYear);
			this.cumPop = this.cumPop + popYear;
		}
		
		this.mean = calcMean(this.cumPop, this.sizeOfResults);
		printMean();
		
		return this.mean;
	}
	
	@Override
	public void printMean() {
		System.out.println(
				"The average population over the selected years is " + this.mean);
	}
	

}
