package client;

import adapters.WorldBankAdapter;
import analysers.Analyser;
import analysers.AnalysisAgriVsForest;
import analysers.AnalysisCoalvsRenewable;
import gui.loginUI;
import viewers.BarChart;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Report;
import viewers.ScatterChart;
import viewers.Viewer;

public class CountryStatistics {
	
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
		
		new loginUI();
	}

}
