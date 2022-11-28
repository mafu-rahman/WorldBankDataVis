package test;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import adapters.WorldBankAdapter;
import analysers.AnalysisAgriVsForest;
import analysers.IAnalyser;
import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.WorldBankFetcher;
import results.Result;
import viewers.BarChart;
import viewers.IViewer;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Report;

public class TestVisualizer {

	/**
	 * Test Line Chart
	 */
	@Test
	public void test_visualizer_01() {
		
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CAN");
		selection.setFromYear(2000);
		selection.setToYear(2001);
		
		IAdapter adapter = new WorldBankAdapter();
		IAnalyser analyser = new AnalysisAgriVsForest(adapter);
		Result result = analyser.calculate(selection);
		 
		IViewer linechart = new LineChart();
		linechart.draw(result);
	}
	
	/**
	 * Test Bar Chart
	 */
	@Test
	public void test_visualizer_02() {
		
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CAN");
		selection.setFromYear(2000);
		selection.setToYear(2001);
		
		IAdapter adapter = new WorldBankAdapter();
		IAnalyser analyser = new AnalysisAgriVsForest(adapter);
		Result result = analyser.calculate(selection);
		 
		IViewer barchart = new BarChart();
		barchart.draw(result);
	}
	
	/**
	 * Test Report
	 */
	@Test
	public void test_visualizer_03() {
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CAN");
		selection.setFromYear(2000);
		selection.setToYear(2001);
		
		IAdapter adapter = new WorldBankAdapter();
		IAnalyser analyser = new AnalysisAgriVsForest(adapter);
		Result result = analyser.calculate(selection);
		 
		IViewer report = new Report();
		report.draw(result);
		
		
	}
	
	/**
	 * Test Pie Chart
	 */
	@Test
	public void test_visualizer_04() {
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CAN");
		selection.setFromYear(2000);
		selection.setToYear(2001);
		
		IAdapter adapter = new WorldBankAdapter();
		IAnalyser analyser = new AnalysisAgriVsForest(adapter);
		Result result = analyser.calculate(selection);
		 
		IViewer piechart = new PieChart();
		piechart.draw(result);
	}
	
}
