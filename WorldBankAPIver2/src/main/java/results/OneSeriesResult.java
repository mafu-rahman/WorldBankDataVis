package results;

import java.util.HashMap;

import com.google.gson.JsonArray;

public class OneSeriesResult extends Result{
	
	private HashMap<String, Double> data1;
	
	public OneSeriesResult() {
		this.data1 = new HashMap<>();
	}
	
	public void addData1(HashMap<String, Double> data1) {
		this.data1 = data1;
	}
	
	public HashMap<String, Double> getData1(){
		return this.data1;
		
	}

}
