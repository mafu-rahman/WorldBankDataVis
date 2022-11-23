package gui;

import dataFetchers.DATAPARSER;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.google.gson.JsonArray;

public class Visualization {
	
	private static String visualType;
	private int analysisType; 
	private ArrayList<JsonArray> retrievedJsonArray;
	private ArrayList<ArrayList<Integer>> years;
	private ArrayList<ArrayList<Double>> values;
	private ArrayList<String> topic;
	
	/**
	 * Constructor method for visualization class
	 * @param visualType visualization type i.e bar chart, line chart, etc.
	 * @param retrievedJsonArray the contents of the analysis.json file 
	 */
	public Visualization(String visualType, ArrayList<JsonArray> retrievedJsonArray) {
		this.visualType = visualType;
		
		this.retrievedJsonArray = retrievedJsonArray;
		topic = DATAPARSER.getTopic(retrievedJsonArray);
		this.years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
		this.values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
	}
	
//	public Visualization(String visualType, int analysisType, ArrayList<JsonArray> retrievedJsonArray) {
//		this.visualType = visualType;
//		this.analysisType = analysisType;
//		this.retrievedJsonArray = retrievedJsonArray;
//		
//		topic = DATAPARSER.getTopic(retrievedJsonArray);
//		years = DATAPARSER.parseRetrievedJSONDataYears(retrievedJsonArray);
//		values = DATAPARSER.parseRetrievedJSONDataValues(retrievedJsonArray);
//	}
	
	/**
	 * Method used to draw charts based on given analysisIndex
	 * @param analysisIndex index of analysis mode to be used
	 */
	public void drawChart(JPanel west) {
		if(visualType.equals("Line Chart")) {
			createLine(west);
		}
		else if(visualType.equals("Bar Chart")) {
			createBar(west);
		}
		else if(visualType.equals("Scatter Chart")) {
			createScatter(west);
		}
		
		else if(visualType.equals("Pie Chart")) {
			createPie(west);
		}
		
		else if(visualType.equals("Report")) {
			createReport(west);
		}
	}
	/**
	 * Method used to draw a line chart
	 */
	private void createLine(JPanel west) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();;
		
		for(int i=0; i<years.size(); i++) {
			ArrayList<Integer> y = years.get(i);
			ArrayList<Double>  v = values.get(i);
			String topic = this.topic.get(i);
			XYSeries series = new XYSeries(topic);
			for(int j=0; j<y.size(); j++) {
				series.add(y.get(j), v.get(j));
			}
			dataset.addSeries(series);
		}
		
		JFreeChart chart = ChartFactory.createXYLineChart(visualType, "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle(visualType, new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}

	private void createScatter(JPanel west) {
		
		XYPlot plot = new XYPlot();
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		for(int i=0; i<years.size(); i++) {
			
			ArrayList<Integer> y = years.get(i);
			ArrayList<Double>  v = values.get(i);
			String topic = this.topic.get(i);
			TimeSeries series = new TimeSeries(topic);
			XYSplineRenderer itemrenderer = new XYSplineRenderer();

			for(int j=0; j<y.size(); j++) {
				series.add(new Year(y.get(j)), v.get(j));
			}
			
			dataset.addSeries(series);
			plot.setDataset(i, dataset);
			plot.setRenderer(i, itemrenderer);
			
			plot.mapDatasetToRangeAxis(i, i);// 1st dataset to 1st y-axis
		}
			
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		plot.setRangeAxis(new NumberAxis("US$"));
		

		JFreeChart scatterChart = new JFreeChart("Time Series",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}
	
	/**
	 * Method used to draw a bar chart
	 */
	private void createBar(JPanel west) {
		CategoryPlot plot = new CategoryPlot();
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);

		JFreeChart barChart = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int i=0; i<years.size(); i++) {
			
			ArrayList<Integer> y = years.get(i);
			ArrayList<Double>  v = values.get(i);
			String topic = this.topic.get(i);
			
			BarRenderer barrenderer = new BarRenderer();
			//dataset = new DefaultCategoryDataset();
			
			for(int j=0; j<y.size(); j++) {
				dataset.addValue(v.get(j), topic , y.get(j));
			}
			
			plot.setDataset(i, dataset);
			plot.setRenderer(i, barrenderer);
			//plot.setRangeAxis(new NumberAxis(""));
			plot.setRangeAxis(i, new NumberAxis("value"));

			plot.mapDatasetToRangeAxis(i, i);

		}

		//barChart =
		//		  ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
		//		  "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		
		barChart = new JFreeChart(visualType,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		


		ChartPanel chartPanel = new ChartPanel(barChart);
		
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

		
	}
	/**
	 * Method used to draw a pie chart
	 */
	private void createPie(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String topic1 = this.topic.get(0);
		String topic2 = this.topic.get(1);
		
		ArrayList<Integer> y1 = years.get(0);
		ArrayList<Integer> y2 = years.get(1);
		
		ArrayList<Double> v1 = values.get(0);
		ArrayList<Double> v2 = values.get(1);
		
		for(int j=0; j<y1.size(); j++) {
			dataset.addValue(v1.get(j), topic1, y1.get(j));
			dataset.addValue(v2.get(j), topic2, y1.get(j));
		}
		
		
		JFreeChart pieChart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,
				TableOrder.BY_COLUMN, true, true, false);
		 
		

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createTimeSeries(JPanel west) {
		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		series1.add(new Year(2018), 5.6);
		series1.add(new Year(2017), 5.7);
		series1.add(new Year(2016), 5.8);
		series1.add(new Year(2015), 5.8);
		series1.add(new Year(2014), 5.9);
		series1.add(new Year(2013), 6.0);
		series1.add(new Year(2012), 6.1);
		series1.add(new Year(2011), 6.2);
		series1.add(new Year(2010), 6.4);

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		series2.add(new Year(2018), 10624);
		series2.add(new Year(2017), 10209);
		series2.add(new Year(2016), 9877);
		series2.add(new Year(2015), 9491);
		series2.add(new Year(2014), 9023);
		series2.add(new Year(2013), 8599);
		series2.add(new Year(2012), 8399);
		series2.add(new Year(2011), 8130);
		series2.add(new Year(2010), 7930);
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		series3.add(new Year(2018), 2.92);
		series3.add(new Year(2017), 2.87);
		series3.add(new Year(2016), 2.77);
		series3.add(new Year(2015), 2.8);
		series3.add(new Year(2014), 2.83);
		series3.add(new Year(2013), 2.89);
		series3.add(new Year(2012), 2.93);
		series3.add(new Year(2011), 2.97);
		series3.add(new Year(2010), 3.05);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, splinerenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart chart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}
	/**
	 * Method used to Draw a Report 
	 * @param analysisIndex index of analysis mode
	 */
	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage = "";
		
		reportMessage = visualType + "\n" + "==========\n";
		
		
		for(int i=0; i<values.size(); i++) {
			ArrayList<Integer> y = years.get(i);
			ArrayList<Double>  v = values.get(i);
			
			String topic = this.topic.get(i);
			XYSeries series = new XYSeries(topic);
			reportMessage += topic + ":\n";

			for(int j=0; j<y.size(); j++) {
				reportMessage += "\tYear: " + y.get(j) + ": " + v.get(j) + "\n";
			}
		}
		
		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}
}
