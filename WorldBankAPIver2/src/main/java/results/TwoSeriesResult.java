package results;

import java.util.HashMap;

import com.google.gson.JsonArray;

public class TwoSeriesResult extends Result{
	private HashMap<String, Double> data1;
	private HashMap<String, Double> data2;
	
	public TwoSeriesResult() {
		data1 = new HashMap<>();
		data2 = new HashMap<>();
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


}
