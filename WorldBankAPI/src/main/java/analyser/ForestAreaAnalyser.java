package analyser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ForestAreaAnalyser extends Analyser {
	
	private double currYear;
	private double cumYear;
	private JsonArray jsonArray;
	private int sizeOfResults;
	private String country;
	private double mean;
	
	public ForestAreaAnalyser(JsonArray jsonArray, int sizeOfResults, String country) {
		this.cumYear = 0;
		this.currYear = 0;
		this.jsonArray = jsonArray;
		this.country = country;
		this.sizeOfResults = sizeOfResults;
	}
	
	public double computeAvg(JsonArray jsonArray, int sizeOfResults) {
		int year;
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				currYear = 0.0;
			else
				currYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

			System.out.println("% of Forest land Area " + this.country + " in " + year + " is " + currYear);
			this.cumYear = this.cumYear + currYear;
		}
		
		this.mean = calcMean(this.cumYear, this.sizeOfResults);
		printMean();
		
		return this.mean;
	}
	
	@Override
	public void printMean() {
		System.out.println(
				"The average emissions as micrograms per cubic metre over the selected years is " + this.mean);
	}

}
