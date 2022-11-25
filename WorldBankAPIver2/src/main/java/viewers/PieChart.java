package viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class PieChart implements IViewer{
	private ChartPanel chartPanel;
	private JPanel viewerPanel;

	public void initialize(JPanel viewPanel) {
		this.viewerPanel = viewPanel;
		System.out.println("Initializing using PieChart Viewer");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		JFreeChart pieChart = ChartFactory.createMultiplePieChart("title", dataset,
				TableOrder.BY_COLUMN, true, true, false);
		 
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		viewPanel.add(chartPanel);
	}
	
	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);		
	}
	
	public String toString() {
		return "Pie Chart";
	}

	public void draw(Result result) {
		System.out.println("Drawing using Pie Chart Viewer");

		if(result instanceof TwoSeriesResult) {
			
		}
		
		else {
			System.out.println("View not supported");
		}
	}
}
