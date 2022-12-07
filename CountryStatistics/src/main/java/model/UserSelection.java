package model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;

import results.OneSeriesResult;
import results.Result;
import results.ThreeSeriesResult;
import results.TwoSeriesResult;
import viewers.IViewer;
import viewers.ViewerFactory;

/**
 * This class contains various sets of data that is selected by the user.
 * @author mafu
 */
public class UserSelection {
	
	/*
	 * Class Attributes
	 */
	private HashMap<String, IViewer> viewers;
	private JPanel viewPanel;
	private ViewerFactory viewerFactory;
	private Result result;
	
	private String countryCode;
	private String analysis;
	private String source;
	private long fromYear;
	private long toYear;
	
	
	/**
	 * Constructor Method
	 */
	public UserSelection() {
		this.viewerFactory = new ViewerFactory();
		this.viewers = new HashMap<>();
	}
	
	/**
	 * Analyse Method
	 */
	public void analyse() {
		//Call http client
		HttpClient httpClient = new HttpClient();
		String httpData = httpClient.call(this);
		
		String type = new JsonParser().parse(httpData).getAsJsonObject().get("resultType").getAsString();
		
		Gson gson = new Gson();
		
		if(type.equals("One Series")) {
			this.result = gson.fromJson(httpData, OneSeriesResult.class);
		}
		
		else if(type.equals("Two Series")) {
			this.result = gson.fromJson(httpData, TwoSeriesResult.class);
		}
		
		else if(type.equals("Three Series")) {
			this.result = gson.fromJson(httpData, ThreeSeriesResult.class);
		}
	}
	
	/**
	 * The method iterates through all the viewers selected by the user and added to
	 * the IViewr class attribute
	 */
	public void draw() {
		for (Map.Entry<String, IViewer> entry : viewers.entrySet()) {
		    IViewer value = entry.getValue();
		    value.draw(result);
		}
	}
	
	/**
	 * This method adds the selected viewer by the user and stores into a List
	 * @param v : the viewer object selected by the user
	 */
	public void addViewer(String viewerName) {
		if(this.viewers.containsKey(viewerName)) {
			System.out.println("Viewer already selected");
			return;
		}
		
		System.out.println("Viewer selected");
		IViewer v = viewerFactory.createViewer(viewerName);
		v.initialize(this.viewPanel);
		viewers.put(viewerName, v);
	}
	
	/**
	 * This method removes the selected viewer by the user and stores into a List
	 * @param v : the viewer object selected by the user
	 */
	public void removeViewer(String viewerName) {
		if(this.viewers.containsKey(viewerName)) {
			System.out.println("Viewer removed");
			
			this.viewers.get(viewerName).remove(viewPanel);
			
			this.viewers.remove(viewerName);
			
			return;
		}
		System.out.println("Viewer not selected");
	}
	
	/*
	 * Setters
	 */
	
	public void setAnalysis(String analysis) {
		this.analysis = analysis.replaceAll(" ", "");
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setCountryCode(String s) {
		this.countryCode = s;
	}
	
	public void setFromYear(long year) {
		this.fromYear = year;
	}
	
	public void setToYear(long year) {
		this.toYear = year;
	}

	public void setViewPanel(JPanel viewPanel) {
		this.viewPanel = viewPanel;
		
	}
	
	/**
	 * Add a viewer panel
	 * @param viewPanel panel to add 
	 */
	public void addViewPanel(JPanel viewPanel) {

		this.viewPanel = viewPanel;
	}
	
	/*
	 * Getters
	 */
	
	public String getAnalysis() {
		return this.analysis;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public long getFromYear() {
		return this.fromYear;
	}
	
	public long getToYear() {
		return this.toYear;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}
}