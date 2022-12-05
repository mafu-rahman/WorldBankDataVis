package client;

<<<<<<< Updated upstream
import adapters.OpenCovidAdapter;
=======
import adapters.UnitedNationsAdapter;
>>>>>>> Stashed changes
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
		
		Viewer v = new Viewer();
		v.addViewer(new BarChart());
		v.addViewer(new PieChart());
		v.addViewer(new LineChart());
		v.addViewer(new ScatterChart());
		v.addViewer(new Report());
		
		Analyser analyses = new Analyser();
		AnalysisFactory analysisFactory = new AnalysisFactory();
		analyses.addAnalyser(analysisFactory.makeAnalyser("Agriculture vs Forest", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Coal vs Renewable", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Forest vs Heat Index vs CO2 Emissions", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Fossil Fuel vs Renewable Consum", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Heat Index vs CO2 Emissions", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Renewable Output vs Renewable Consumption", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Total Population vs GDP Growth", new WorldBankAdapter()));
		analyses.addAnalyser(analysisFactory.makeAnalyser("Display Covid Cases", new OpenCovidAdapter()));
		// analysisFactory.makeAnalyser("Political Stability vs GDP Growth", new WorldBankAdapter());
		
		new LoginUI(v, analyses);
	}

}
