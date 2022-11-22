package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Viewers.LineChart;
import Viewers.Viewer;
import dataFetchers.DATAPARSER;
import dataFetchers.Fetcher;
import dataParser.JsonParseYears;
import dataParser.JsonParser;

public class bottomPanel implements IuiPanel{
	
	private JFrame frame;
	private JPanel bottomPanel;
	private JsonParser jsonParser;
	
	private JButton addView;
	private JButton removeView;
	private JButton recalculate;
	
	private JComboBox<String> viewsList;
	
	private HashMap<String, Viewer> views;
	
	public bottomPanel(JFrame frame) {
		this.frame = frame;
		this.bottomPanel = new JPanel();
		this.jsonParser = new JsonParser();
		setupPanel();	
	}
	
	public void setupPanel() {
		this.setupAvailableViews();
		this.setupAnalysisMethods();
		this.addButton();
		this.minusButton();
		this.calculateButton();
		
		frame.add(bottomPanel, BorderLayout.SOUTH);
		//west = new JPanel();
		//west.setLayout(new GridLayout(2, 0));
	}
	
	private void setupAvailableViews() {
		JLabel viewsLabel = new JLabel("Available Views: ");
		
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Pie Chart");
		viewsNames.add("Report");	
		
		this.viewsList = new JComboBox<String>(viewsNames);
		
		
//		this.viewsList = new JComboBox<Viewer>();
//		viewsList.addItem(new LineChart());
		
		bottomPanel.add(viewsLabel);
		bottomPanel.add(viewsList);
	}
	
	
	private void setupAnalysisMethods() {
		JLabel methodLabel = new JLabel("        Choose analysis method: ");
		
		jsonParser.setParser(new JsonParseYears());
		Vector<String> methodsNames = new Vector<String>();
		methodsNames = DATAPARSER.getAnalysisList();
		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);
		
		bottomPanel.add(methodLabel);
		bottomPanel.add(methodsList);
	}
	
	private void addButton() {
		this.addView = new JButton("+");
		bottomPanel.add(addView);
			addView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String visualType = (String) viewsList.getSelectedItem();
				views.put(visualType, new LineChart());
			}
		});
	}
	
	private void minusButton() {
		this.removeView = new JButton("-");
		bottomPanel.add(removeView);
		removeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				west.remove(index);
//				index--;
				//implement max index of 6
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
					
			}
		});
	}
	
	private void calculateButton() {
		recalculate = new JButton("Recalculate");
		bottomPanel.add(recalculate);
		
		// On pressing calculate button
		recalculate.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			calculateButton();
			}
		});
		
//		frame.add(bottomPanel, BorderLayout.SOUTH);
//		frame.add(west, BorderLayout.WEST);
//				
//		west.setVisible(true);
//		String country = (String) mainUI.countriesList.getSelectedItem();
//		long fromYear = (long) fromList.getSelectedItem();
//		long toYear = (long) toList.getSelectedItem();
//		String visualType = (String) viewsList.getSelectedItem();
//		int analyseIndex = methodsList.getSelectedIndex();
//		retrievedJsonArray = new ArrayList<>();
//						
//		ArrayList<String> analyseTypeCodes = DATAPARSER.getAnalysisCodes(analyseIndex);
//		ArrayList<String> bannedVisuals = DATAPARSER.getBannedVisuals(analyseIndex);
//		
//		for(String s: analyseTypeCodes) {
//			Fetcher fetcher = new Fetcher(country, (int)fromYear, (int)toYear);
//			retrievedJsonArray.add(fetcher.fetchData(s));
//		}
//				
//		Visualization v = new Visualization(visualType, retrievedJsonArray);
//		v.drawChart(west);			
//		
//		index++;
		frame.invalidate();
		frame.validate();
		frame.repaint();	
	}

}
