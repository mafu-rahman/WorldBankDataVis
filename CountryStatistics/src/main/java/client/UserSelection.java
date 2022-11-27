package client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import analysers.IAnalyser;
import results.Result;
import viewers.IViewer;

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
	private List<IViewer> viewers;
	private IAnalyser analyser;
	private JPanel viewPanel;
	private Result result;
	
	private String countryCode;
	private long fromYear;
	private long toYear;
	
	
	/**
	 * Constructor Method
	 */
	public UserSelection() {
		this.viewers = new ArrayList<>();
	}
	
	/**
	 * Analyse Method
	 */
	public void analyse() {
		this.result = analyser.calculate(this);
	}
	
	/**
	 * This method adds the selected viewer by the user and stores into a List
	 * @param v : the viewer object selected by the user
	 */
	public void addViewer(IViewer v) {
		if(this.viewers.contains(v)) {
			System.out.println("Viewer already selected");
			return;
		}
		
		System.out.println("Viewer selected");
		this.viewers.add(v);
		v.initialize(this.viewPanel);
	}
	
	/**
	 * This method removes the selected viewer by the user and stores into a List
	 * @param v : the viewer object selected by the user
	 */
	public void removeViewer(IViewer v) {
		if(this.viewers.contains(v)) {
			System.out.println("Viewer removed");
			v.remove(viewPanel);
			this.viewers.remove(v);
			return;
		}
		System.out.println("Viewer not selected");
	}
	
	/**
	 * The method iterates through all the viewers selected by the user and added to
	 * the IViewr class attribute
	 */
	public void draw() {
		for(IViewer v : viewers) {
			v.draw(result);
		}
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
	public void setAnalysis(IAnalyser a) {
		this.analyser = a;
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
	
	public List<IViewer> getViewer() {
		return this.viewers;
	}
	
	public IAnalyser getAnalyser() {
		return this.analyser;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}
}