package results;

import java.util.HashMap;

import com.google.gson.JsonArray;

public class ThreeSeriesResult extends Result{
	private HashMap<String, Double> data1;
	private HashMap<String, Double> data2;
	private HashMap<String, Double> data3;

	
	public ThreeSeriesResult() {
		data1 = new HashMap<>();
		data2 = new HashMap<>();
		data3 = new HashMap<>();

	}
	
	public void addData1(JsonArray j) {
		
	}
	
	public void addData2(JsonArray j) {
		
	}
	
	public void addData3(JsonArray j) {
		
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
