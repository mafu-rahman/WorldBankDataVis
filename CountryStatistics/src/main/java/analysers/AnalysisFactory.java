package analysers;

import adapters.IAdapter;

public class AnalysisFactory {
	
	public IAnalyser createAnalyser(String newAnalysis, IAdapter adapter) {
		
		
		if(newAnalysis.equals("Agriculture vs Forest")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisAgriVsForest(adapter);
		}
		
		else if(newAnalysis.equals("Coal vs Renewable")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisCoalVsRenewable(adapter);
		}
		
		else if(newAnalysis.equals("Forest vs Heat Index vs CO2 Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisForVsHeatVsCo2(adapter);
		}
		
		else if(newAnalysis.equals("Fossil Fuel vs Renewable Consum")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisFossilVsRenew(adapter);
		}
		
		else if(newAnalysis.equals("Heat Index vs CO2 Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisHeatVsCO2(adapter);
		}
		
		else if(newAnalysis.equals("Political Stability vs GDP Growth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisPolStabilityVsGDP(adapter);
		}
		
		else if(newAnalysis.equals("Renewable Output vs Renewable Consumption")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisRenewOutputVsConsump(adapter);
		}
		
		else if(newAnalysis.equals("Total Population vs GDP Growth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisTotalPopVsGDP(adapter);
		}
		
		else if(newAnalysis.equals("Display Covid Cases")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisCovidCases(adapter);
		}
		
		return null;
	}

}
