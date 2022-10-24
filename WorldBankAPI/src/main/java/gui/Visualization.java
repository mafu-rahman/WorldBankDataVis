package gui;

import gui.DATAPARSER;
import dataFetchers.Fetcher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import com.google.gson.JsonArray;

public class Visualization {
	
	private static String visualType;
	private int analysisType; 
	private ArrayList<JsonArray> retrievedJsonArray;
	private ArrayList<ArrayList<Integer>> years;
	private ArrayList<ArrayList<Double>> values;
	private String topic;
	
	/**
	 * Constructor method for visualization class
	 * @param visualType visualization type i.e bar chart, line chart, etc.
	 * @param retrievedJsonArray the contents of the analysis.json file 
	 */
	public Visualization(String visualType, ArrayList<JsonArray> retrievedJsonArray) {
		this.visualType = visualType;
		this.retrievedJsonArray = retrievedJsonArray;
		
		this.years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
		this.values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
	}
	
	public Visualization(String visualType, int analysisType, ArrayList<JsonArray> retrievedJsonArray) {
		this.visualType = visualType;
		this.analysisType = analysisType;
		this.retrievedJsonArray = retrievedJsonArray;
		this.years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
		this.values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
	}
	
	/**
	 * Method used to draw charts based on given analysisIndex
	 * @param analysisIndex index of analysis mode to be used
	 */
	public void drawChart(int analysisIndex) {
		if(visualType.equals("Line Chart")) {
			drawLineChart();
		}
		else if(visualType.equals("Bar Chart")) {
			drawBarChart();
		}
		else if(visualType.equals("Scatter Chart")) {
			drawScatterChart();
		}
		else if(visualType.equals("Report")) {
			drawReport(analysisIndex);
		}
		else if(visualType.equals("Pie Chart")) {
			drawPieChart();
		}
	}
	
	/**
	 * Method used to draw a line chart
	 */
	private void drawLineChart() {
		for(int i=0; i<years.size(); i++) {
			System.out.println(visualType);

			ArrayList<Integer> y = years.get(i);
			ArrayList<Double> v = values.get(i);
			for(int j=0; j<y.size(); j++) {
				System.out.println("Year: " + y.get(j) + " Value: " + v.get(j));

			}
		}
	}
	
	/**
	 * Method used to draw a pie chart
	 */
	private void drawPieChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int size = this.years.size() - 1;
		while(size >= 0) {
			// TODO: Make dataset read in value from this.years 
			// Integer x = this.years.get(size);
			// Double x = this.values.get(size);
			dataset.addValue(1, "A", "B");
			size--;
		}
		
		JFreeChart pieChart = ChartFactory.createMultiplePieChart3D("TOTAL POP VS TOTAL REP"
				, dataset, TableOrder.BY_COLUMN, true, true, false);
		
		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
	}
	
	/**
	 * Method used to Draw a Report 
	 * @param analysisIndex index of analysis mode
	 */
	private void drawReport(int analysisIndex) {
		String reportMessage = "";
		JTextArea report = new JTextArea();
		
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		ArrayList<String> codes = DATAPARSER.getAnalysisCodes(analysisIndex);
		Vector<String> topics = DATAPARSER.getAnalysisList();
		
		String topicReport = topics.get(analysisIndex);
		String[] tokens = topicReport.split(" vs ");
		
		int sizeOfResults = codes.size();
		
		int numYears = this.years.size() - 1;
		
		reportMessage += topicReport + "\n---------------------------------"
				+ "-----------------\n";
		
		while(numYears != 0) {
			reportMessage += "Year " + this.years.get(numYears) + "\n";
			for(int i = 0; i < tokens.length; i++) {
				reportMessage += tokens[i] + "\n";
			}
			reportMessage += "\n\n";
			numYears--;
		}
		
		
		report.setText(reportMessage);
		JScrollPane outputPane = new JScrollPane(report);	
		// panel.add(outputPane);
	}
	
	/**
	 * Method used to draw a bar chart
	 */
	private static void drawBarChart() {
		
	}
	
	/**
	 * Method used to draw a scatter chart
	 */
	private static void drawScatterChart() {
		TimeSeries s1;
	}
	
	public static void main(String[] args) {
		
	}

}
