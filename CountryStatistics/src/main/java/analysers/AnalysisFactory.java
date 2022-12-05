package analysers;

import adapters.IAdapter;

public class AnalysisFactory {
	
	public IAnalyser makeAnalyser(String newAnalysis, IAdapter adapter) {
		
		
		if(newAnalysis.equals("Agriculture vs Forest")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisAgriVsForest(adapter);
		}
		
		else if(newAnalysis.equals("Coal vs Renewable")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisCoalEnergyvsRenewableOutput(adapter);
		}
		
		else if(newAnalysis.equals("Forest vs Heat Index vs CO2 Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisForestvsHeatIndexvsCO2Emissions(adapter);
		}
		
		else if(newAnalysis.equals("Fossil Fuel vs Renewable Consum")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisFossilFuelvsRenewableConsumption(adapter);
		}
		
		else if(newAnalysis.equals("Heat Index vs CO2 Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisHeatIndexvsCO2Emission(adapter);
		}
		
		else if(newAnalysis.equals("Political Stability vs GDP Growth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisPolStabilityvsGDPGrowth(adapter);
		}
		
		else if(newAnalysis.equals("Renewable Output vs Renewable Consumption")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisRenewableOutputvsRenewableConsumption(adapter);
		}
		
		else if(newAnalysis.equals("Total Population vs GDP Growth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisTotalPopvsGDPGrowth(adapter);
		}
		
		else if(newAnalysis.equals("Display Covid Cases")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new DisplayCovidCases(adapter);
		}
		
		return null;
	}

}
