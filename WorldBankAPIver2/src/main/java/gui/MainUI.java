package gui;

import java.awt.BorderLayout;
import adapters.WorldBankAdapter;
import analysers.Analyser;
import analysers.AnalysisAgriVsForest;
import analysers.AnalysisCoalvsRenewable;
import analysers.IAnalyser;
import client.UserSelection;
import jsonDataParser.JsonParseCountries;
import jsonDataParser.JsonParseYears;
import jsonDataParser.JsonParser;
import viewers.BarChart;
import viewers.IViewer;
import viewers.LineChart;
import viewers.PieChart;
import viewers.Report;
import viewers.ScatterChart;
import viewers.Viewer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI extends JFrame{

	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		Viewer v = new Viewer();
		v.addViewer(new BarChart());
		v.addViewer(new PieChart());
		v.addViewer(new LineChart());
		v.addViewer(new ScatterChart());
		v.addViewer(new Report());
		
		Analyser a = new Analyser();
		a.addAnalyser(new AnalysisAgriVsForest(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisCoalvsRenewable(new WorldBankAdapter()));
		
		new MainUI(v, a);	
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
	
	private JComboBox<String> countriesList;
	private JComboBox<Integer> fromYear;
	private JComboBox<Integer> toYear;
	private JComboBox<IViewer> viewerList;
	private JComboBox<IAnalyser> analysisList;

	
	private JsonParser jsonParser;
	private Viewer viewers;
	private Analyser analysers;
	private UserSelection userSelection;

	
	public MainUI() {
		Viewer v = new Viewer();
		v.addViewer(new BarChart());
		v.addViewer(new PieChart());
		v.addViewer(new LineChart());
		v.addViewer(new ScatterChart());
		v.addViewer(new Report());
		
		Analyser a = new Analyser();
		a.addAnalyser(new AnalysisAgriVsForest(new WorldBankAdapter()));
		a.addAnalyser(new AnalysisCoalvsRenewable(new WorldBankAdapter()));
		
		new MainUI(v, a);
	}
	
	/**
	 * Constructor Method
	 * @param v : This parameter contains all the Viewer objects inside the Viewer class
	 * @param a : This parameter contains all the analyser objects inside the Analyser class
	 */
	public MainUI(Viewer v, Analyser a) {
		frame = new JFrame();
		frame.setTitle("Country Statistics");
		frame.setSize(1200, 800);
		
		this.topPanel = new JPanel();
		this.bottomPanel = new JPanel();
		this.viewPanel = new JPanel();
		
		this.viewPanel.setLayout(new GridLayout(2, 0));
		
		this.jsonParser = new JsonParser();
		this.userSelection = new UserSelection();
		this.userSelection.setViewPanel(viewPanel);
		 
		this.viewers = v;
		this.analysers = a;
		
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
		jsonParser.setParser(new JsonParseCountries("countries.json"));
		Vector<String> countriesNames = (Vector<String>) jsonParser.parse();
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		countriesList = new JComboBox<String>(countriesNames);
		
		topPanel.add(chooseCountryLabel);
		topPanel.add(countriesList);
	}
	
	/**
	 * Sets up the 'from' years menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupFromYears() {
		jsonParser.setParser(new JsonParseYears("years.json"));
		Vector<Integer> years = (Vector<Integer>) jsonParser.parse();
		
		JLabel fromLabel = new JLabel("From");
		this.fromYear = new JComboBox<Integer>(years);
		
		topPanel.add(fromLabel);
		topPanel.add(fromYear);
	}
	
	/**
	 * Sets up the 'to' years menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupToYears() {
		jsonParser.setParser(new JsonParseYears("years.json"));
		Vector<Integer> years = (Vector<Integer>) jsonParser.parse();
		
		JLabel toLabel = new JLabel("To");
		toYear = new JComboBox<Integer>(years);
		
		topPanel.add(toLabel);
		topPanel.add(toYear);
	}
	
	/**
	 * Sets up the 'viewers' in the bottom panel
	 */
	private void setupAvailableViews() {
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<IViewer> viewsNames = this.viewers.getViewers();	
		this.viewerList = new JComboBox<>(viewsNames);
		
		bottomPanel.add(viewsLabel);
		bottomPanel.add(viewerList);
	}
	
	/**
	 * 'Add' button (+) for viewers
	 */
	private void setupAddViewButton() {
		this.addViewButton = new JButton("+");
		bottomPanel.add(addViewButton);
		
		addViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userSelection.addViewer((IViewer) viewerList.getSelectedItem());
				
				refreshFrame();	
			}
		});

	}
	
	/**
	 * 'Remove' button (-) for Viewers
	 */
	private void setupRemoveViewButton() {
		this.removeViewButton = new JButton("-");
		bottomPanel.add(removeViewButton);
		
		removeViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userSelection.removeViewer((IViewer) viewerList.getSelectedItem());				
				
				refreshFrame();	
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
			userSelection.draw();
			refreshFrame();	
			}
		});
	}
	
	/*
	 * Helper methods
	 */
	private void refreshFrame() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
}