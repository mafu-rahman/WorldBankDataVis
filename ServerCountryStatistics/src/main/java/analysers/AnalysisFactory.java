package analysers;

import adapters.IAdapter;

public class AnalysisFactory {
	
	public IAnalyser createAnalyser(String newAnalysis, IAdapter adapter) {
		
		
		if(newAnalysis.equals("AgriculturevsForest")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisAgriVsForest(adapter);
		}
		
		else if(newAnalysis.equals("CoalvsRenewable")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisCoalVsRenewable(adapter);
		}
		
		else if(newAnalysis.equals("ForestvsHeatIndexvsCO2Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisForVsHeatVsCo2(adapter);
		}
		
		else if(newAnalysis.equals("FossilFuelvsRenewableConsum")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisFossilVsRenew(adapter);
		}
		
		else if(newAnalysis.equals("HeatIndexvsCO2Emissions")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisHeatVsCO2(adapter);
		}
		
		else if(newAnalysis.equals("PoliticalStabilityvsGDPGrowth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisPolStabilityVsGDP(adapter);
		}
		
		else if(newAnalysis.equals("RenewableOutputvsRenewableConsumption")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisRenewOutputVsConsump(adapter);
		}
		
		else if(newAnalysis.equals("TotalPopulationvsGDPGrowth")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisTotalPopVsGDP(adapter);
		}
		
		else if(newAnalysis.equals("DisplayCovidCases")) {
			System.out.println("Analyis Factory: Active! | Type: " + newAnalysis);
			return new AnalysisCovidCases(adapter);
		}
		
		return null;
	}

}
