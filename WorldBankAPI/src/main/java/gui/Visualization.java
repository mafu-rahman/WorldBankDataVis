package gui;

import gui.DATAPARSER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		this.years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
		this.values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
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
		else if(visualType.equals("Report")) {
			drawReport();
		}
		else if(visualType.equals("Pie Chart")) {
			drawPieChart();
		}
	}
	
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
	
	private void drawPieChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int size = this.years.size() - 1;
		while(size >= 0) {
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
	
	private void drawReport() {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.red);
		
		String reportMessage = "Test Message" + "\n";
		
		report.setText(reportMessage);
		JScrollPane outputPane = new JScrollPane(report);	
	}
	
	private static void drawBarChart() {
		
	}
	
	private static void drawScatterChart() {
		
	}
	
	

}
