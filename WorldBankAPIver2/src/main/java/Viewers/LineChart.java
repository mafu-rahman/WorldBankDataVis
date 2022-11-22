package Viewers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

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

public class LineChart implements Viewer{
	
	public LineChart() {
		
	}
	
	
	/**
	 * Method used to draw a line chart
	 */
	public void view(JPanel west) {
//		
//		XYSeriesCollection dataset = new XYSeriesCollection();;
//		
//		for(int i=0; i<years.size(); i++) {
//			ArrayList<Integer> y = years.get(i);
//			ArrayList<Double>  v = values.get(i);
//			String topic = this.topic.get(i);
//			XYSeries series = new XYSeries(topic);
//			for(int j=0; j<y.size(); j++) {
//				series.add(y.get(j), v.get(j));
//			}
//			dataset.addSeries(series);
//		}
//		
//		JFreeChart chart = ChartFactory.createXYLineChart(visualType, "Year", "", dataset,
//				PlotOrientation.VERTICAL, true, true, false);
//
//		XYPlot plot = chart.getXYPlot();
//
//		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		renderer.setSeriesPaint(0, Color.RED);
//		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//
//		plot.setRenderer(renderer);
//		plot.setBackgroundPaint(Color.white);
//
//		plot.setRangeGridlinesVisible(true);
//		plot.setRangeGridlinePaint(Color.BLACK);
//
//		plot.setDomainGridlinesVisible(true);
//		plot.setDomainGridlinePaint(Color.BLACK);
//
//		chart.getLegend().setFrame(BlockBorder.NONE);
//
//		chart.setTitle(
//				new TextTitle(visualType, new Font("Serif", java.awt.Font.BOLD, 18)));
//
//		ChartPanel chartPanel = new ChartPanel(chart);
//		chartPanel.setPreferredSize(new Dimension(400, 300));
//		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//		chartPanel.setBackground(Color.white);
//		west.add(chartPanel);
//
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		
	}

	public String toString() {
		return "Line Chart";
	}
}
