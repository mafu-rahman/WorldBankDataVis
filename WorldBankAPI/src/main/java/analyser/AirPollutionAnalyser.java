package analyser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class AirPollutionAnalyser extends Analyser {
	
	private double emuYear;
	private double cumEmu;
	private JsonArray jsonArray;
	private int sizeOfResults;
	private String country;
	private double mean;
	
	public AirPollutionAnalyser(JsonArray jsonArray, int sizeOfResults, String country) {
		this.cumEmu = 0;
		this.emuYear = 0;
		this.jsonArray = jsonArray;
		this.country = country;
		this.sizeOfResults = sizeOfResults;
	}
	
	public double computeAvg(JsonArray jsonArray, int sizeOfResults) {
		int year;
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				emuYear = 0.0;
			else
				emuYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
						.getAsDouble();

			System.out.println("PM2.5 air pollution, mean annual exposure for " + this.country + " in " + year + " is " + emuYear);
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
