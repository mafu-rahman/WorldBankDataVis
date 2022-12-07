package viewers;

import java.awt.Color;

import java.awt.Dimension;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import observers.Observer;
import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;

public class Report implements IViewer{
	private JPanel viewPanel;
	private JScrollPane outputScrollPane;
	private JTextArea report;
	private String reportMessage;
	private Result result;
	private ArrayList<Observer> observers;

	@Override
	public void initialize(JPanel viewPanel) {
		System.out.println("Initializing using Report Viewer");

		this.viewPanel = viewPanel;
		this.observers = new ArrayList<Observer>();

		report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		reportMessage = "";
		
		reportMessage = "Report Title" + "\n" + "==========\n";
		
		report.setText(reportMessage);
		outputScrollPane = new JScrollPane(report);
		this.viewPanel.add(outputScrollPane);		
	}

	@Override
	public void draw(Result result) {
		
		System.out.println("Drawing using Report Viewer");
		this.result = result;
		
		String title = result.getTitle();
		
		reportMessage = title + "\n" + "==========\n";

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
		reportMessage += topic1 + ":\n";

		for(Map.Entry<String, Double> set: data1.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		report.setText(reportMessage);

	}

	private void drawTwoSeries() {
		TwoSeriesResult result = (TwoSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		
		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		
		reportMessage += topic1 + ":\n";
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		
		reportMessage += topic2 + ":\n";
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		
		report.setText(reportMessage);

	}

	private void drawThreeSeries() {
		ThreeSeriesResult result = (ThreeSeriesResult) this.result;
		
		HashMap<String, Double> data1 = result.getData1();
		HashMap<String, Double> data2 = result.getData2();
		HashMap<String, Double> data3 = result.getData3();

		String topic1 = result.getTopic1();
		String topic2 = result.getTopic2();
		String topic3 = result.getTopic3();

		reportMessage += topic1 + ":\n";
		for(Map.Entry<String, Double> set: data1.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		
		reportMessage += topic2 + ":\n";
		for(Map.Entry<String, Double> set: data2.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		
		reportMessage += topic3 + ":\n";

		for(Map.Entry<String, Double> set: data3.entrySet()) {
			reportMessage += "\tYear: " + set.getKey() + ": " + set.getValue() + "\n";
		}
		
		report.setText(reportMessage);

	}

	@Override
	public void remove(JPanel viewPanel) {
		viewPanel.remove(outputScrollPane);
	}
	
	public String toString() {
		return "Report";
	}
	
	@Override
	public void register(Observer o) {
		observers.add(o);
	}

	@Override
	public void unregister(Observer o) {
		int observerIndex = observers.indexOf(o);
		System.out.println("Observer " + (observerIndex+1) + " deleted.");
		observers.remove(observerIndex);
	}

	@Override
	public void notifyRegister() {
		for(Observer o : observers) {
			o.update(this);
		}
	}
}