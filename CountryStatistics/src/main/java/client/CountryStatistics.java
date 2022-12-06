package client;

import adapters.OpenCovidAdapter;
import adapters.WorldBankAdapter;
import analysers.*;
import gui.LoginUI;
import viewers.BarChart;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Report;
import viewers.ScatterChart;
import viewers.Viewer;

/**
 * This is the main client class.
 * ALl the viewers are setup here
 * All the analysets with their specific adapters are initialised here
 * @author mafu
 *
 */
public class CountryStatistics {
	
	/**
	 * Main method for client access
	 * @param args
	 */
	public static void main(String args[]) {
		
		
		
//		Analyser analyses = new Analyser();
//		AnalysisFactory analysisFactory = new AnalysisFactory();
//		
//		analyses.addAnalyser(analysisFactory.createAnalyser("Agriculture vs Forest", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Coal vs Renewable", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Forest vs Heat Index vs CO2 Emissions", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Fossil Fuel vs Renewable Consum", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Heat Index vs CO2 Emissions", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Renewable Output vs Renewable Consumption", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Total Population vs GDP Growth", new WorldBankAdapter()));
//		analyses.addAnalyser(analysisFactory.createAnalyser("Display Covid Cases", new OpenCovidAdapter()));
//		// analysisFactory.makeAnalyser("Political Stability vs GDP Growth", new WorldBankAdapter());
		
		new LoginUI();
	}

}
