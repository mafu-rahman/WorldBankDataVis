package results;

import java.util.HashMap;

public class ThreeSeriesResult extends Result{
	public String resultType;

	private String title;

	private String topic1;
	private String topic2;
	private String topic3;
	
	private HashMap<String, Double> data1;
	private HashMap<String, Double> data2;
	private HashMap<String, Double> data3;
	

	
	/**
	 * Constructor Method
	 */
	public ThreeSeriesResult() {
		data1 = new HashMap<>();
		data2 = new HashMap<>();
		data3 = new HashMap<>();
	}
	
	/*
	 * Setters and Getters
	 */
	
	public void addType(String s) {
		this.resultType = s;
	}
	
	public void addTitle(String s) {
		this.title = s;
	}
	
	public void addTopic1(String s) {
		topic1 = s;
	}
	
	public void addTopic2(String s) {
		topic2 = s;
	}
	
	public void addTopic3(String s) {
		topic3 = s;
	}
	
	public void addData1(HashMap<String, Double> data) {
		this.data1 = data;
	}
	
	public void addData2(HashMap<String, Double> data) {
		this.data2 = data;
	}
	
	public void addData3(HashMap<String, Double> data) {
		this.data3 = data;
	}
	
	public String getType() {
		return this.resultType;
	}
	/**
	 * Get a title
	 * @return title String value of title
	 */
	public String getTitle() {
		return this.title;
	}
	
	public String getTopic1() {
		return this.topic1;
	}
	
	public String getTopic2() {
		return this.topic2;
	}
	
	public String getTopic3() {
		return this.topic3;
	}
	
	public HashMap<String, Double> getData1(){
		return this.data1;	
	}

	public HashMap<String, Double> getData2(){
		return this.data2;		
	}
	
	public HashMap<String, Double> getData3(){
		return this.data3;		
	}
}