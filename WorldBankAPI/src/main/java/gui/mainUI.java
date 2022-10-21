package gui;




import java.awt.BorderLayout;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import dataFetchers.Fetcher;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import login.loginUI;

public class mainUI extends JFrame{

	public static void main(String[] args) {
		
		new mainUI();
		
		
	}
	
	/*
	 * This method returns list of all countries
	 */
	public static Vector<String> getAllCountries() {
	    Vector<String> countries = new Vector<>();
	    String[] countryCodes = Locale.getISOCountries();
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale obj = new Locale("", countryCodes[i]);
	        countries.add(obj.getDisplayCountry());
	    }
	    return countries;
	 }

	/*
	 * Class Attributes
	 */
	private static JFrame frame;
	private static JPanel panel;
	private static JComboBox<String> countriesList;
	private static JComboBox<Integer> fromList;
	private static JComboBox<Integer> toList;
	private static JComboBox<String> methodsList;

	
		
	public mainUI() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		frame.setTitle("Country Statistics");
		frame.setSize(1200, 720);
		
		topPanel();
		bottomPanel();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void topPanel() {
		
		
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		//country list
		Vector<String> countriesNames = getCountryList();
		//Vector<String> countriesNames = getAllCountries();
		
		this.countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<Integer> years = getYears();
		
		
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
	
	public void bottomPanel() {
		JButton recalculate = new JButton("Recalculate");
			
		JLabel viewsLabel = new JLabel("Available Views: ");
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		
		
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames = getAnalysisList();
		methodsList = new JComboBox<String>(methodsNames);

		
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);
		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		/*
		 * On pressing calculate button
		 */
		recalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String country = (String) mainUI.countriesList.getSelectedItem();
				long fromYear = (long) fromList.getSelectedItem();
				long toyear = (long) toList.getSelectedItem();
				int analysisIndex = methodsList.getSelectedIndex();
				Fetcher fetcher = new Fetcher(country, analysisIndex, (int)fromYear, (int)toyear);
				
			}
		});
		frame.add(south, BorderLayout.SOUTH);
	}
	
	/*
	 * Parses from countries.json file to get available years
	 */
	public Vector<String> getCountryList() {
		JSONParser jsonParser = new JSONParser();
		ArrayList<String> tempCountries = new ArrayList<>();
		Vector<String> countries = null;
        try{
        	FileReader reader = new FileReader("countries.json");
        	JSONObject countriesJSON = (JSONObject) jsonParser.parse(reader);
        	
        	tempCountries = (ArrayList<String>) countriesJSON.get("countries");
        	countries = new Vector<>(tempCountries);
     		
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return countries;
	}
	
	/*
	 * parses from years.json to get available years
	 */
	public Vector<Integer> getYears(){
		JSONParser jsonParser = new JSONParser();
		ArrayList<Integer> tempYears = new ArrayList<>();
		Vector<Integer> years = null;
        try{
        	FileReader reader = new FileReader("years.json");
        	JSONObject yearsJSON = (JSONObject) jsonParser.parse(reader);
        	
        	tempYears = (ArrayList<Integer>) yearsJSON.get("years");
        	years = new Vector<Integer>(tempYears);
     		
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return years;
	}
	
	/*
	 * Parses from analysis.json file to display options
	 */
	public Vector<String> getAnalysisList(){
		JsonParser jsonParser = new JsonParser();
		
		Vector<String> list = new Vector<>();
		
        try{
        	FileReader reader = new FileReader("analysis.json");
        	
        	JsonArray jsonArray = (JsonArray) jsonParser.parse(reader);
        	
        	for(int i=0; i<jsonArray.size(); i++) {
        		list.add(jsonArray.get(i).getAsJsonObject().get("type").getAsString());
        	}
        	
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return list;
	}
}
