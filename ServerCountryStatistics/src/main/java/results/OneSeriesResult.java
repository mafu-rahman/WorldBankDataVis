package results;

import java.util.HashMap;

public class OneSeriesResult extends Result{
	public String resultType;
	
	private String title;
	private String topic1;
	private HashMap<String, Double> data1;
	
	/**
	 * Constructor Method
	 */
	public OneSeriesResult() {
		this.data1 = new HashMap<>();
	}
	
	public void addType(String s) {
		this.resultType = s;
	}
	
	/**
	 * Add data to the hash map 
	 * @param data1 data to add
	 */
	public void addData1(HashMap<String, Double> data1) {
		this.data1 = data1;
	}
	
	/**
	 * Return data
	 * @return data1 returned data
	 */
	public HashMap<String, Double> getData1(){
		return this.data1;
		
	}
	
	public String getType() {
		return this.resultType;
	}
	
	/**
	 * Add a topic
	 * @param s String value of topic
	 */
	public void addTopic1(String s) {
		topic1 = s;
	}
		
	/**
	 * Add a title
	 * @param s String value of title 
	 */
	public void addTitle(String s) {
		this.title = s;
	}
	
	/**
	 * Get a title
	 * @return title String value of title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Get a topic
	 * @return topic1 String value of a topic
	 */
	public String getTopic1() {
		return this.topic1;
	}

}
