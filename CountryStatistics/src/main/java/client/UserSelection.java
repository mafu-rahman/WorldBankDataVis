package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import adapters.AdapterFactory;
import adapters.IAdapter;
import analysers.AnalysisFactory;
import analysers.IAnalyser;
import results.Result;
import viewers.IViewer;
import viewers.ViewerFactory;

/**
 * This class contains various sets of data that is selected by the user.
 * 
 * @author mafu
 *
 */
public class UserSelection {
	
	/*
	 * Class Attributes
	 */
	//private List<IViewer> viewers;
	private HashMap<String, IViewer> viewers;
	private IAnalyser analyser;
	private JPanel viewPanel;
	private AnalysisFactory analysisFactory;
	private ViewerFactory viewerFactory;
	private AdapterFactory adapterFactory;
	private Result result;
	
	private String countryCode;
	private long fromYear;
	private long toYear;
	
	
	/**
	 * Constructor Method
	 */
	public UserSelection() {
		this.analysisFactory = new AnalysisFactory();
		this.viewerFactory = new ViewerFactory();
		this.adapterFactory = new AdapterFactory();
		this.viewers = new HashMap<>();
		
	}
	
	/**
	 * Analyse Method
	 */
	public void analyse() {
		this.result = analyser.calculate(this);
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
	
	/**
	 * Set an analysis type
	 * @param a analysis type to add
	 */
	public void setAnalysis(String analyser, String source) {
		IAdapter adapter = adapterFactory.createAdapter(source);
		
		this.analyser = analysisFactory.createAnalyser(analyser, adapter);
	}
	
	/*
	 * Getters
	 */
	
	public long getFromYear() {
		return this.fromYear;
	}
	
	public long getToYear() {
		return this.toYear;
	}
	
	
	public IAnalyser getAnalyser() {
		return this.analyser;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}
}