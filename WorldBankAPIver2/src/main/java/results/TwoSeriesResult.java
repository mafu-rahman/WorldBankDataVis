package results;

import java.util.HashMap;

public class TwoSeriesResult extends Result{
	private HashMap<String, Double> data1;
	private HashMap<String, Double> data2;
	
	private String topic1;
	private String topic2;
	
	/**
	 * Constructor Method
	 */
	public TwoSeriesResult() {
		data1 = new HashMap<>();
		data2 = new HashMap<>();
	}
	
	/*
	 * Setters and Getters
	 */
	public void addTopic1(String s) {
		topic1 = s;
	}
	
	public void addTopic2(String s) {
		topic2 = s;
	}
	
	public void addData1(HashMap<String, Double> data1) {
		this.data1 = data1;
	}
	
	public void addData2(HashMap<String, Double> data2) {
		this.data2 = data2;
	}
	
	public HashMap<String, Double> getData1(){
		return this.data1;
		
	}
	
	public HashMap<String, Double> getData2(){
		return this.data2;
		
	}
	
	public String getTopic1() {
		return this.topic1;
	}
	
	public String getTopic2() {
		return this.topic2;
	}
}
