package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;

import analysers.Analyser;
import analysers.AnalysisAgriVsForest;
import analysers.AnalysisCoalvsRenewable;
import analysers.IAnalyser;
import client.UserSelection;
import dataFetchers.Fetcher;
import jsonDataParser.DATAPARSER;
import jsonDataParser.JsonParseCountries;
import jsonDataParser.JsonParseYears;
import jsonDataParser.JsonParser;
import viewers.BarChart;
import viewers.IViewer;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Viewer;

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
import java.util.List;
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

public class mainUI extends JFrame{

	public static void main(String[] args) {
		Viewer v = new Viewer();
		v.addViewer(new BarChart());
		v.addViewer(new PieChart());
		v.addViewer(new LineChart());
		
		Analyser a = new Analyser();
		a.addAnalyser(new AnalysisAgriVsForest(null, null));
		a.addAnalyser(new AnalysisCoalvsRenewable(null, null));
		
		new mainUI(v, a);	
	}

	/*
	 * Class Attributes
	 */
	private static JFrame frame;
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel viewPanel;
	
	private JButton addViewButton;
	private JButton removeViewButton;
	private JButton recalculateButton;
	
	JComboBox<String> countriesList;
	JComboBox<Integer> fromYear;
	JComboBox<Integer> toYear;
	private JComboBox<IViewer> viewerList;
	private JComboBox<IAnalyser> analysisList;

	
	private JsonParser jsonParser;
	private Viewer viewers;
	private Analyser analysers;
	private UserSelection userSelection;

	
	/**
	 * Constructor Method
	 */
	public mainUI(Viewer v, Analyser a) {
		frame = new JFrame();
		frame.setTitle("Country Statistics");
		frame.setSize(1200, 800);
		
		this.jsonParser = new JsonParser();
		this.userSelection = new UserSelection();
		 
		this.viewers = v;
		this.analysers = a;
		
		this.topPanel = new JPanel();
		this.bottomPanel = new JPanel();
		this.viewPanel = new JPanel();
		
		this.setupPanel();
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(viewPanel, BorderLayout.WEST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setupPanel() {
		this.setupCountryMenu();
		this.setupFromYears();
		this.setupToYears();
		this.setupAvailableViews();
		this.setupAddViewButton();
		this.setupRemoveViewButton();
		this.setupAnalysisMethods();
		this.setupRecalculateButton();
	}
	
	/**
	 * Sets up the countries menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupCountryMenu() {
		jsonParser.setParser(new JsonParseCountries());
		Vector<String> countriesNames = (Vector<String>) jsonParser.parse();
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		countriesList = new JComboBox<String>(countriesNames);
		
		topPanel.add(chooseCountryLabel);
		topPanel.add(countriesList);
	}
	
	/**
	 * Sets up the from years menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupFromYears() {
		jsonParser.setParser(new JsonParseYears());
		Vector<Integer> years = (Vector<Integer>) jsonParser.parse();
		
		JLabel from = new JLabel("From");
		this.fromYear = new JComboBox<Integer>(years);
		
		topPanel.add(from);
		topPanel.add(fromYear);
	}
	
	/**
	 * Sets up the to years menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupToYears() {
		jsonParser.setParser(new JsonParseYears());
		Vector<Integer> years = (Vector<Integer>) jsonParser.parse();
		
		JLabel to = new JLabel("To");
		toYear = new JComboBox<Integer>(years);
		
		topPanel.add(to);
		topPanel.add(toYear);
	}
	
	/**
	 * Sets up the viewers in the bottom panel
	 */
	private void setupAvailableViews() {
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<IViewer> viewsNames = this.viewers.getViewers();	
		this.viewerList = new JComboBox<>(viewsNames);
		
		bottomPanel.add(viewsLabel);
		bottomPanel.add(viewerList);
	}
	
	/**
	 * Add button for viewers
	 */
	private void setupAddViewButton() {
		this.addViewButton = new JButton("+");
		bottomPanel.add(addViewButton);
		
		addViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userSelection.addViewer((IViewer) viewerList.getSelectedItem());
			}
		});
	}
	
	/**
	 * Remove button for Viewers
	 */
	private void setupRemoveViewButton() {
		this.removeViewButton = new JButton("-");
		bottomPanel.add(removeViewButton);
		
		removeViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userSelection.removeViewer((IViewer) viewerList.getSelectedItem());
			}
		});
	}
	
	/**
	 * Sets up the analysis methods in the bottom panel
	 */
	private void setupAnalysisMethods() {
		JLabel methodLabel = new JLabel("\t\tChoose analysis method: ");
		
		Vector<IAnalyser> analysisNames = this.analysers.getAnalysers();	
		this.analysisList = new JComboBox<>(analysisNames);
		
		bottomPanel.add(methodLabel);
		bottomPanel.add(analysisList);
	}
	
	/**
	 * Sets up the recalculate button in the bottom panel
	 */
	private void setupRecalculateButton() {
		this.recalculateButton = new JButton("Recalculate");
		bottomPanel.add(recalculateButton);

		recalculateButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			userSelection.setCountryCode((String) countriesList.getSelectedItem());
			userSelection.setFromYear((long) fromYear.getSelectedItem());
			userSelection.setToYear((long) toYear.getSelectedItem());
			userSelection.setAnalysis((IAnalyser) analysisList.getSelectedItem());
			userSelection.analyse();
			}
		});
		

		frame.invalidate();
		frame.validate();
		frame.repaint();	
	}
	
}
