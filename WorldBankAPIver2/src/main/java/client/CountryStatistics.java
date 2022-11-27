package client;

import adapters.WorldBankAdapter;
import analysers.*;
import gui.loginUI;
import viewers.BarChart;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Report;
import viewers.ScatterChart;
import viewers.Viewer;

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
		
		Analyser a = new Analyser();
		a.addAnalyser(new AnalysisAgriVsForest(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisCoalvsRenewable(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisRenewableOutputvsRenewableConsumption(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisFossilFuelvsRenewableConsum(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisTotalPopvsGDPGrowth(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisHeatIndexvsCO2Emission(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisForestvsHeatIndexvsCO2Emissions(new WorldBankAdapter()));
		
		new loginUI();
	}

}
