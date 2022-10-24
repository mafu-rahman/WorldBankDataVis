package dataFetchers;

import java.awt.BorderLayout;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
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
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class DATAPARSER {
	
	public static void main(String args[]) {
		
	}
	
	public DATAPARSER() {}
	
	/*
	 * Parses topic
	 */
	
	public static ArrayList<String> getTopic(ArrayList<JsonArray> retrievedJsonArray) {
		ArrayList<String> output = new ArrayList<>();
		
		for(int i=0; i<retrievedJsonArray.size(); i++) {
			String topic = retrievedJsonArray.get(i).get(1).getAsJsonArray().get(0).getAsJsonObject().get("indicator").getAsJsonObject().get("value").getAsString();
			
			output.add(topic);
		}
		
		
		return output;
	}
	
	/**
	 * Method used to parse from the retrieved JSON file and extract only the years
	 * @param retrievedJsonArray contents of the retrieved file
	 * @return outerArray ArrayList of all available years
	 */
	public static ArrayList<ArrayList<Integer>> parseRetrievedJSONDataYears(ArrayList<JsonArray> retrievedJsonArray){
		ArrayList<ArrayList<Integer>> outerArray = new ArrayList<>();
		
		for(int i=0; i<retrievedJsonArray.size(); i++) {
			JsonArray jsonArray = retrievedJsonArray.get(i).get(1).getAsJsonArray();
			ArrayList<Integer> innerArray = new ArrayList<>();
			
			for(int j=0; j<jsonArray.size(); j++) {
				int years = jsonArray.get(j).getAsJsonObject().get("date").getAsInt();
				innerArray.add(years);
			}
			outerArray.add(innerArray);
		}
		return outerArray;
	}
	
	/**
	 * Method used to parse from the retrieved JSON file and extracts only the years
	 * @param retrievedJsonArray contents of the retrieved file
	 * @return outerArray ArrayList of all available years
	 */
	public static ArrayList<ArrayList<Double>> parseRetrievedJSONDataValues(ArrayList<JsonArray> retrievedJsonArray){
		
		ArrayList<ArrayList<Double>> outerArray = new ArrayList<>();
		
		for(int i=0; i<retrievedJsonArray.size(); i++) {
			JsonArray jsonArray = retrievedJsonArray.get(i).get(1).getAsJsonArray();
			ArrayList<Double> innerArray = new ArrayList<>();
			
			for(int j=0; j<jsonArray.size(); j++) {
				double value=0.0;
				if(jsonArray.get(j).getAsJsonObject().get("value") != JsonNull.INSTANCE) {
					value = jsonArray.get(j).getAsJsonObject().get("value").getAsDouble();
				}
				innerArray.add(value);
			}
			outerArray.add(innerArray);
		}
		return outerArray;
	} 
	
	
	/**
	 * Parses from countries.json file to get available countries
	 * @return countries List of all available countries 
	 */
	public static Vector<String> getCountryList() {
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
	
	/**
	 * Parses from years.json to get available years
	 * @return years List of all available years
	 */
	public static Vector<Integer> getYears(){
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

	
	/**
	 * Parses from analysis.json file to display options
	 * @return list List of all available options
	 */
	public static Vector<String> getAnalysisList(){
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


	/**
	 * This method parses only the analysis type codes from analysis.json of a given index
	 * @param analyseIndex Index of analysis option 
	 * @return analysisTypeCodes List of all available analysis type codes
	 */
	public static ArrayList<String> getAnalysisCodes(int analyseIndex){
		
		JsonParser jsonParser = new JsonParser();
		
		ArrayList<String> analysisTypeCodes = new ArrayList<>();
		
        try{
        	FileReader reader = new FileReader("analysis.json");
        	JsonArray jsonArray = (JsonArray) jsonParser.parse(reader);
        	
        	JsonArray analysisCodes = jsonArray.get(analyseIndex).getAsJsonObject().get("codes").getAsJsonArray();
        	
        	for(int i=0; i< analysisCodes.size(); i++) {
        		analysisTypeCodes.add(analysisCodes.get(i).getAsString());
        	}     	
        	
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return analysisTypeCodes;
	}


	/**
	 * This method parses only the banned visuals of a specific index from analysis.json
	 * @param analyseIndex Index of analysis option
	 * @return bannedVisual List of all banned visualizations for the given analyisIndex 
	 */
	public static ArrayList<String> getBannedVisuals(int analyseIndex) {
		
		ArrayList<String> bannedVisual = new ArrayList<>();
		JsonParser jsonParser = new JsonParser();
				
        try{
        	FileReader reader = new FileReader("analysis.json");
        	JsonArray jsonArray = (JsonArray) jsonParser.parse(reader);
        	
        	
        	JsonArray bannedVisualJSON = jsonArray.get(analyseIndex).getAsJsonObject().get("bannedVisualizations").getAsJsonArray();
        	
        	for(int i=0; i< bannedVisualJSON.size(); i++) {
        		bannedVisual.add(bannedVisualJSON.get(i).getAsString());
        	}        	
        	
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return bannedVisual;
	}

}
