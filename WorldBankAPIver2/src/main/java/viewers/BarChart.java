package viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class BarChart implements IViewer{
	private JPanel viewPanel;
	private ChartPanel chartPanel;
	private JFreeChart barChart;
	private DefaultCategoryDataset dataset;
	private CategoryPlot plot;
	private BarRenderer barrenderer;
	private Result result;
	
	/**
	 * Initializes the panel for the bar chart
	 */
	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using BarChart Viewer");
		
		this.viewPanel = viewPanel;
		this.dataset = new DefaultCategoryDataset();

		plot = new CategoryPlot();
		plot.setDomainAxis(new CategoryAxis("Year"));
		plot.setRangeAxis(new NumberAxis("Value"));
		
		barrenderer = new BarRenderer();
		plot.setDataset(dataset);
		plot.setRenderer(barrenderer);
			
		barChart = new JFreeChart("Bar Chart Title", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);		
		
		chartPanel = new ChartPanel(barChart);	
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.viewPanel.add(chartPanel);

	}
	
	/**
	 * Draws the bar chart
	 */
	public void draw(Result result) {
		this.dataset.clear();
		
		System.out.println("Drawing using BarChart Viewer");
		this.result = result;
		String title = result.getTitle();
		barChart.setTitle(title);
		
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
		
		HashMap<String, Double> data1 = result.getData1();
		
		String topic1 = result.getTopic1();
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			dataset.addValue(set.getValue(), topic1, set.getKey());
		}	
	}
	
	private void drawTwoSeries() {
		TwoSeriesResult result = (TwoSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		
		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			dataset.addValue(set.getValue(), topic1, set.getKey());
		}
		
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			dataset.addValue(set.getValue(), topic2, set.getKey());
		}		
	}
	
	private void drawThreeSeries() {
		ThreeSeriesResult result = (ThreeSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		HashMap<String, Double> data3 = result.getData3();

		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		String topic3 = result.getTopic3();
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			dataset.addValue(set.getValue(), topic1, set.getKey());
		}
		
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			dataset.addValue(set.getValue(), topic2, set.getKey());
		}
		
		for(Map.Entry<String, Double> set: data3.entrySet()) {
			dataset.addValue(set.getValue(), topic3, set.getKey());
		}	
	}
	
	/**
	 * Remove panel from frame
	 */
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);		
	}
	
	/**
	 * toString method
	 */
	public String toString() {
		return "Bar Chart";
	}
}