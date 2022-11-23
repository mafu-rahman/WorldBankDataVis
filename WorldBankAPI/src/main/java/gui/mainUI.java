package gui;

import dataFetchers.DATAPARSER;
import java.awt.BorderLayout;
import java.awt.Color;

import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import dataFetchers.Fetcher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import login.loginUI;

public class mainUI extends JFrame{

	public static void main(String[] args) {
		new mainUI();	
	}

	/*
	 * Class Attributes
	 */
	private static JFrame frame;
	//private static JPanel panel;
	
	private static JComboBox<String> countriesList;
	private static JComboBox<Integer> fromList;
	private static JComboBox<Integer> toList;
	
	private static JComboBox<String> methodsList;
	private static JComboBox<String> viewsList;
	
	private static JButton addView;
	private static JButton removeView;
	private static JButton recalculate;
	
	private ArrayList<JsonArray> retrievedJsonArray;
	private ArrayList<Visualization> visual;
	
	private JPanel west;
	private int index;

	/**
	 * Constructor Method
	 */
	public mainUI() {
		frame = new JFrame();
		//panel = new JPanel();
		//panel.setLayout(null);
		frame.setTitle("Country Statistics");
		
		topPanel();
		bottomPanel();
		index = -1;
		
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Create elements (buttons, drop-down menus) on the top panel
	 * of the program window.
	 */
	public void topPanel() {
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		
		Vector<String> countriesNames = DATAPARSER.getCountryList();
		this.countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<Integer> years = DATAPARSER.getYears();
		fromList = new JComboBox<Integer>(years);
		toList = new JComboBox<Integer>(years);

		JPanel north = new JPanel();		
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		frame.add(north, BorderLayout.NORTH);
	}
	
	/**
	 * Create elements (buttons, drop-down menus) on the bottom panel
	 * of the program window.
	 */
	public void bottomPanel() {
		recalculate = new JButton("Recalculate");
		JLabel viewsLabel = new JLabel("Available Views: ");
		
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Pie Chart");
		viewsNames.add("Report");		
		viewsList = new JComboBox<String>(viewsNames);
		
		this.addView = new JButton("+");
		this.removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");
		Vector<String> methodsNames = new Vector<String>();
		methodsNames = DATAPARSER.getAnalysisList();
		methodsList = new JComboBox<String>(methodsNames);
		
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);
		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		
		// On pressing calculate button
		recalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculateButton();
			}
		});
		frame.add(south, BorderLayout.SOUTH);
		frame.add(west, BorderLayout.WEST);
		
		addView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculateButton();	
			}
		});
		
		
		removeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				west.remove(index);
				index--;
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
					
			}
		});
	}
	
	private void calculateButton() {
		west.setVisible(true);
		String country = (String) mainUI.countriesList.getSelectedItem();
		long fromYear = (long) fromList.getSelectedItem();
		long toYear = (long) toList.getSelectedItem();
		String visualType = (String) viewsList.getSelectedItem();
		int analyseIndex = methodsList.getSelectedIndex();
		retrievedJsonArray = new ArrayList<>();
						
		ArrayList<String> analyseTypeCodes = DATAPARSER.getAnalysisCodes(analyseIndex);
		ArrayList<String> bannedVisuals = DATAPARSER.getBannedVisuals(analyseIndex);
		
		for(String s: analyseTypeCodes) {
			Fetcher fetcher = new Fetcher(country, (int)fromYear, (int)toYear);
			retrievedJsonArray.add(fetcher.fetchData(s));
		}
				
		Visualization v = new Visualization(visualType, retrievedJsonArray);
		v.drawChart(west);			
		
		index++;
		frame.invalidate();
		frame.validate();
		frame.repaint();	
	}	
}