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

public class PieChart implements IViewer{
	private ChartPanel chartPanel;

	public void draw(JPanel viewPanel) {
		System.out.println("Drawing using PieChart Viewer");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		
			//dataset.addValue(v1.get(j), topic1, y1.get(j));
			//dataset.addValue(v2.get(j), topic2, y1.get(j));
		
		
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
}
