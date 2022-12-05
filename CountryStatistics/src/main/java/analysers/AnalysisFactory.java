package analysers;

import adapters.IAdapter;

public class AnalysisFactory {
	
	public IAnalyser makeAnalyser(String newAnalysis, IAdapter adapter) {
		
		if(newAnalysis.equals("Agriculture vs Forest")) {
			return new AnalysisAgriVsForest(adapter);
		}
		
		else if(newAnalysis.equals("Coal vs Renewable")) {
			return new AnalysisCoalvsRenewable(adapter);
		}
		
		else if(newAnalysis.equals("Forest vs Heat Index vs CO2 Emissions")) {
			return new AnalysisForestvsHeatIndexvsCO2Emissions(adapter);
		}
		
		else if(newAnalysis.equals("Fossil Fuel vs Renewable Consum")) {
			return new AnalysisFossilFuelvsRenewableConsum(adapter);
		}
		
		else if(newAnalysis.equals("Heat Index vs CO2 Emissions")) {
			return new AnalysisHeatIndexvsCO2Emission(adapter);
		}
		
		else if(newAnalysis.equals("Political Stability vs GDP Growth")) {
			return new AnalysisPolStabilityvsGDPGrowth(adapter);
		}
		
		else if(newAnalysis.equals("Renewable Output vs Renewable Consumption")) {
			return new AnalysisRenewableOutputvsRenewableConsumption(adapter);
		}
		
		else if(newAnalysis.equals("Total Population vs GDP Growth")) {
			return new AnalysisTotalPopvsGDPGrowth(adapter);
		}
		
		else if(newAnalysis.equals("Display Covid Cases")) {
			return new DisplayCovidCases(adapter);
		}
		
		return null;
	}

}
