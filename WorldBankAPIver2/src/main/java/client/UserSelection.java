package client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import analysers.IAnalyser;
import results.Result;
import viewers.IViewer;

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
	 * Draws Viewers
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
	
	/**
	 * Add a viewer (visualizer)
	 * @param v Visualizer to delete
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
	 * Remove a viewer (visualizer)
	 * @param v Visualizer to delete
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