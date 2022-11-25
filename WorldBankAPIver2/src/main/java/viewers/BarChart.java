package viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

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
	private JPanel viewerPanel;
	private ChartPanel chartPanel;
	private JFreeChart barChart;
	private DefaultCategoryDataset dataset;
	
	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using BarChart Viewer");
		
		this.viewerPanel = viewPanel;
		this.dataset = new DefaultCategoryDataset();

		CategoryPlot plot = new CategoryPlot();
		plot.setDomainAxis(new CategoryAxis("Year"));
		plot.setRangeAxis(new NumberAxis("Value"));
			
		BarRenderer barrenderer = new BarRenderer();
			
		plot.setDataset(dataset);
		plot.setRenderer(barrenderer);
			
		barChart = new JFreeChart("Bar Chart Title", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);		
	
		chartPanel = new ChartPanel(barChart);	
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.viewerPanel.add(chartPanel);
	}
	
	public void draw(Result result) {
		System.out.println("Drawing using BarChart Viewer");

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
		
	}
	
	private void drawTwoSeries() {
		
	}
	
	private void drawThreeSeries() {
		
	}
	
	
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);		
	}
	
	public String toString() {
		return "Bar Chart";
	}

}