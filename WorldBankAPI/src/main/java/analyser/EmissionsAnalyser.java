package analyser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class EmissionsAnalyser extends Analyser {
	
	private int emuYear;
	private int cumEmu;
	private JsonArray jsonArray;
	private int sizeOfResults;
	private String country;
	private int mean;
	
	public EmissionsAnalyser(JsonArray jsonArray, int sizeOfResults, String country) {
		this.cumEmu = 0;
		this.emuYear = 0;
		this.jsonArray = jsonArray;
		this.country = country;
		this.sizeOfResults = sizeOfResults;
	}
	
	public int computeAvg(JsonArray jsonArray, int sizeOfResults) {
		int year;
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				emuYear = 0;
			else
				emuYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsInt();

			System.out.println("Emissions for " + this.country + " in " + year + " is " + emuYear);
			this.cumEmu = this.cumEmu + emuYear;
		}
		
		this.mean = calcMean(this.cumEmu, this.sizeOfResults);
		printMean();
		
		return this.mean;
	}
	
	@Override
	public void printMean() {
		System.out.println(
				"The average emissions as micrograms per cubic metre over the selected years is " + this.mean);
	}

}
