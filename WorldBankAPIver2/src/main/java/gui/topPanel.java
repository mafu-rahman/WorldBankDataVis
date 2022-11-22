package gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dataParser.JsonParseCountries;
import dataParser.JsonParseYears;
import dataParser.JsonParser;

public class topPanel implements IuiPanel{
	
	private JFrame frame;
	private JPanel topPanel;
	private JsonParser jsonParser;
	
	
	public topPanel(JFrame frame) {
		this.frame = frame;
		topPanel = new JPanel();
		this.jsonParser = new JsonParser();
		setupPanel();	
	}
	
	/**
	 * Create elements (buttons, drop-down menus) on the top panel
	 * of the program window.
	 */
	public void setupPanel() {
		
		setupCountryMenu();
		setupFromYears();
		setupToYears();

		frame.add(topPanel, BorderLayout.NORTH);
		
//		frame.invalidate();
//		frame.validate();
//		frame.repaint();
	}
	
	/**
	 * Sets up the countries menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupCountryMenu() {
		jsonParser.setParser(new JsonParseCountries());
		Vector<String> countriesNames = (Vector<String>) jsonParser.parse();
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);
		
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
		JComboBox<Integer> fromList = new JComboBox<Integer>(years);
		
		topPanel.add(from);
		topPanel.add(fromList);
	}
	
	/**
	 * Sets up the to years menu in the top panel
	 */
	@SuppressWarnings("unchecked")
	private void setupToYears() {
		jsonParser.setParser(new JsonParseYears());
		Vector<Integer> years = (Vector<Integer>) jsonParser.parse();
		
		JLabel to = new JLabel("To");
		JComboBox<Integer> toList = new JComboBox<Integer>(years);
		
		topPanel.add(to);
		topPanel.add(toList);
	}
}
