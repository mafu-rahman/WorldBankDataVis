package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import com.google.gson.JsonArray;

public class Visualization {
	
	private static String visualType;
	private ArrayList<JsonArray> retrievedJsonArray;
	private ArrayList<ArrayList<Integer>> years;
	private ArrayList<ArrayList<Double>> values;
	private String topic;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Visualization(String visualType, ArrayList<JsonArray> retrievedJsonArray) {
		this.visualType = visualType;
		this.retrievedJsonArray = retrievedJsonArray;
		
		years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
		values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
	}
	
	public void drawChart() {
		if(visualType.equals("Line Chart")) {
			drawLineChart();
		}
		else if(visualType.equals("Bar Chart")) {
			drawBarChart();
		}
		else if(visualType.equals("Scatter Chart")) {
			drawScatterChart();
		}
	}
	
	private void drawLineChart() {
		for(int i=0; i<years.size(); i++) {
			ArrayList<Integer> y = years.get(i);
			ArrayList<Double> v = values.get(i);
			for(int j=0; j<y.size(); j++) {
				System.out.println("Year: " + y.get(j));
				System.out.println("Value: " + v.get(j));

			}
		}
	}
	
	private static void drawBarChart() {
		
	}
	
	private static void drawScatterChart() {
		
	}
	
	

}
