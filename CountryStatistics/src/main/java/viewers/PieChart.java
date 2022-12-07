package viewers;

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
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class PieChart implements IViewer{
	private ChartPanel chartPanel;
	private JPanel viewPanel;
	private DefaultCategoryDataset dataset;
	private JFreeChart pieChart;
	private Result result;


	public void initialize(JPanel viewPanel) {
		this.viewPanel = viewPanel;
		System.out.println("Initializing using PieChart Viewer");
		
		dataset = new DefaultCategoryDataset();
		
		pieChart = ChartFactory.createMultiplePieChart("Pie Chart", dataset,
				TableOrder.BY_COLUMN, true, true, false);
		 
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.viewPanel.add(chartPanel);
	}
	
	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);		
	}
	
	public String toString() {
		return "Pie Chart";
	}

	public void draw(Result result) {
		dataset.clear();
		
		System.out.println("Drawing using Pie Chart Viewer");
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
		pieChart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
		HashMap<String, Double> data1 = result.getData1();

		String topic1 = result.getTopic1();
		
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			dataset.addValue(set.getValue(), topic1, set.getKey());
		}
		
	}

	private void drawTwoSeries() {
		TwoSeriesResult result = (TwoSeriesResult) this.result;
		
		String title = result.getTitle();
		pieChart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
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
		
		String title = result.getTitle();
		pieChart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
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
	

}