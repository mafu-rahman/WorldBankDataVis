package results;

import java.util.HashMap;

public class OneSeriesResult extends Result{
	
	private HashMap<String, Double> data1;
	private String topic1;
	
	public OneSeriesResult() {
		this.data1 = new HashMap<>();
	}
	
	public void addData1(HashMap<String, Double> data1) {
		this.data1 = data1;
	}
	
	public HashMap<String, Double> getData1(){
		return this.data1;
		
	}
	
	public void addTopic1(String s) {
		topic1 = s;
	}
	
	public String getTopic1() {
		return this.topic1;
	}

}
