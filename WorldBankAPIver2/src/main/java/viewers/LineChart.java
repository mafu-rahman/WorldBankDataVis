package viewers;

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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class LineChart implements IViewer{
	
	ChartPanel chartPanel;
	
	public LineChart() {
	}

	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using LineChart Viewer");
		
		XYSeriesCollection dataset = new XYSeriesCollection();;
		
		
		XYSeries series = new XYSeries("topic");
			
		//series.add(y.get(j), v.get(j));
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("visual type", "Year", "", dataset,
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
				new TextTitle("visual type", new Font("Serif", java.awt.Font.BOLD, 18)));

		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		viewPanel.add(chartPanel);
	}
	
	public void draw(Result result) {
		System.out.println("Drawing using Line Chart Viewer");

		if(result instanceof OneSeriesResult) {
			
		}
		
		else if(result instanceof TwoSeriesResult) {
			
		}
		
		else if(result instanceof ThreeSeriesResult) {
			
		}
		
		else {
			System.out.println("View not supported");
		}
	}
	
	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(chartPanel);
		
	}
	
	public String toString() {
		return "Line Chart";
	}

}
	
