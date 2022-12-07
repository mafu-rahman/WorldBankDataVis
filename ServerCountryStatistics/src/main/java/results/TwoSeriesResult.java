package results;

import java.util.HashMap;

public class TwoSeriesResult extends Result{
	public String resultType;
	
	private String title;
	
	private String topic1;
	private String topic2;
	
	private HashMap<String, Double> data1;
	private HashMap<String, Double> data2;
	
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
	
	public void addType(String s) {
		this.resultType = s;
	}
	
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
	
	public void addTitle(String s) {
		this.title = s;
	}
	
	public String getType() {
		return this.resultType;
	}
	
	public String getTitle() {
		return this.title;
	}
	public String getTopic1() {
		return this.topic1;
	}
	
	public String getTopic2() {
		return this.topic2;
	}
	
	public HashMap<String, Double> getData1(){
		return this.data1;
	}
	
	public HashMap<String, Double> getData2(){
		return this.data2;
		
	}
	
}
