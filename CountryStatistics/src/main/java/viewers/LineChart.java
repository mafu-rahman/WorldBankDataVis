package viewers;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class LineChart implements IViewer{
	
	private JPanel viewPanel;
	
	private ChartPanel chartPanel;
	
	private JFreeChart chart;
	
	private XYSeriesCollection dataset;
		
	private XYLineAndShapeRenderer renderer;
	private XYPlot plot;

	private Result result;
	
	
	public LineChart() {
	}

	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using LineChart Viewer");
		
		this.viewPanel = viewPanel;
		
		dataset = new XYSeriesCollection();
		
		chart = ChartFactory.createXYLineChart("Title", "Year", "Values", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		chart.getLegend().setFrame(BlockBorder.NONE);

		renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot = chart.getXYPlot();
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		this.viewPanel.add(chartPanel);
	}
	
	public void draw(Result result) {
		dataset.removeAllSeries();
		
		System.out.println("Drawing using Line Chart Viewer");
		this.result = result;
		
		if(result instanceof OneSeriesResult) {
			this.drawOneSeries();
		}
		
		else if(result instanceof TwoSeriesResult) {
			this.drawTwoSeries();
		}
		
		else if(result instanceof ThreeSeriesResult) {
			this.drawThreeSeries();
		}
		
		else {
			System.out.println("View not supported");
		}
	}
	
	private void drawOneSeries() {
		OneSeriesResult result = (OneSeriesResult) this.result;
		
		String title = result.getTitle();
		chart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
		HashMap<String, Double> data1 = result.getData1();
		
		String topic1 = result.getTopic1();
		
		XYSeries series1 = new XYSeries(topic1);
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(key, set.getValue());
		}
		dataset.addSeries(series1);
	}
	
	private void drawTwoSeries() {
		TwoSeriesResult result = (TwoSeriesResult) this.result;
		
		String title = result.getTitle();
		chart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		
		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		
		XYSeries series1 = new XYSeries(topic1);
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(key, set.getValue());
		}
		dataset.addSeries(series1);
		
		XYSeries series2 = new XYSeries(topic2);
		
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series2.add(key, set.getValue());
		}
		dataset.addSeries(series2);
	}
	
	private void drawThreeSeries() {
		ThreeSeriesResult result = (ThreeSeriesResult) this.result;
		
		String title = result.getTitle();
		chart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		HashMap<String, Double> data3 = result.getData3();

		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		String topic3 = result.getTopic3();

		XYSeries series1 = new XYSeries(topic1);
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(set.getValue(), key);
		}
		dataset.addSeries(series1);
		
		XYSeries series2 = new XYSeries(topic2);
		
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series2.add(set.getValue(), key);
		}
		dataset.addSeries(series2);
		
		XYSeries series3 = new XYSeries(topic3);
		
		for(Map.Entry<String, Double> set: data3.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series3.add(set.getValue(), key);
		}
		dataset.addSeries(series3);	
	}
	
	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);
	}
	
	public String toString() {
		return "Line Chart";
	}

}