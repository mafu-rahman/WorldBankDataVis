package client;

import java.util.ArrayList;
import java.util.List;

import analysers.IAnalyser;
import viewers.IViewer;

public class UserSelection {
	
	private List<IViewer> viewers;
	private String countryCode;
	private long fromYear;
	private long toYear;
	private IAnalyser analyser;
	
	public UserSelection() {
		this.viewers = new ArrayList<>();
	}
	
	public void analyse() {
		analyser.calculate();
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
	
	public void addViewer(IViewer v) {
		if(this.viewers.contains(v)) {
			System.out.println("Viewer already selected");
			return;
		}
		System.out.println("Viewer selected");
		this.viewers.add(v);
		v.draw();
	}
	
	public void removeViewer(IViewer v) {
		if(this.viewers.contains(v)) {
			this.viewers.remove(v);
			System.out.println("Viewer removed");
			return;
		}
		System.out.println("Viewer not selected");
	}
	
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