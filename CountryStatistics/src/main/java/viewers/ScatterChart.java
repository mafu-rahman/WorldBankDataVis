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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class ScatterChart implements IViewer{

	private JPanel viewPanel;
	
	private XYPlot plot;
	private TimeSeriesCollection dataset;
	private ChartPanel chartPanel;
	private JFreeChart scatterChart;

	private Result result;
	

	
	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using Scatter Chart Viewer");
		
		this.viewPanel = viewPanel;
		
		plot = new XYPlot();
		dataset = new TimeSeriesCollection();
					
		XYSplineRenderer itemrenderer = new XYSplineRenderer();

		plot.setDataset(dataset);
		plot.setRenderer(itemrenderer);
			
		//plot.mapDatasetToRangeAxis(i, i);// 1st dataset to 1st y-axis
			
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		plot.setRangeAxis(new NumberAxis("Values"));

		scatterChart = new JFreeChart("Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.viewPanel.add(chartPanel);
	}

	@Override
	public void draw(Result result) {
		//resetting the data so that it does not show up in a new analyser
		this.dataset.removeAllSeries();
		
		System.out.println("Drawing using Line Chart Viewer");
		this.result = result;
		
		String title = result.getTitle();
		scatterChart.setTitle(
				new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
		
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
		TimeSeries series1 = new TimeSeries(topic1);
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series1);

	}

	private void drawTwoSeries() {
		TwoSeriesResult result = (TwoSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		
		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();		
		
		TimeSeries series1 = new TimeSeries(topic1);
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series1);
		
		TimeSeries series2 = new TimeSeries(topic2);
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series2.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series2);
	}

	private void drawThreeSeries() {
		ThreeSeriesResult result = (ThreeSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		HashMap<String, Double> data3 = result.getData3();

		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		String topic3 = result.getTopic3();		
		
		TimeSeries series1 = new TimeSeries(topic1);
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series1.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series1);
		
		TimeSeries series2 = new TimeSeries(topic2);
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series2.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series2);
		
		TimeSeries series3 = new TimeSeries(topic3);
		for(Map.Entry<String, Double> set: data3.entrySet()) {
			Integer key = Integer.valueOf(set.getKey());
			series3.add(new Year(key), set.getValue());
		}
		dataset.addSeries(series3);
	}

	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);
	}
	
	public String toString() {
		return "Scatter Chart";
	}


}
