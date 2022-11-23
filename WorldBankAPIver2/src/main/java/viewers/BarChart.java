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

public class BarChart implements IViewer{
	private ChartPanel chartPanel;
	
	@Override
	public void draw(JPanel viewPanel) {
		System.out.println("Drawing using BarChart Viewer");
		
		CategoryPlot plot = new CategoryPlot();
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);

		JFreeChart barChart = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//dataset.addValue(v.get(j), topic , y.get(j));
		
		BarRenderer barrenderer = new BarRenderer();
			
		plot.setDataset(dataset);
		plot.setRenderer(barrenderer);
			
		plot.setRangeAxis(new NumberAxis("value"));

		//plot.mapDatasetToRangeAxis(i, i);
		
		barChart = new JFreeChart("visual type",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);


		chartPanel = new ChartPanel(barChart);
		
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
		return "Bar Chart";
	}


}