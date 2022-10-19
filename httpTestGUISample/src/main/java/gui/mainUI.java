package gui;

import java.awt.BorderLayout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.loginUI;

public class mainUI extends JFrame{

	public static void main(String[] args) {
		
		new mainUI();
		
	}

	/*
	 * Class Attributes
	 */
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel usernameLabel;
	private static JLabel passwordLabel;
	private static JLabel statusLabel;
	private static JTextField usernameText;
	private static JPasswordField passwordText;
	
	private static JPanel panel1, panel2, panel3, panel4, panel5;
	
	public mainUI() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		frame.setTitle("Country Statistics");
		frame.setSize(1024, 720);
		
		topPanel();
		bottomPanel();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void topPanel() {
		
		
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = getCountryList();
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<Integer> years = getYears();
		
		
		JComboBox<Integer> fromList = new JComboBox<Integer>(years);
		JComboBox<Integer> toList = new JComboBox<Integer>(years);

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
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP");
		methodsNames.add("Unemployment");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		frame.add(south, BorderLayout.SOUTH);
	}
	
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

	
	
	
	
}
