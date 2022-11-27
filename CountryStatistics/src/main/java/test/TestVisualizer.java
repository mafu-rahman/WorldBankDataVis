package test;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonArray;

import dataFetchers.Fetcher;

public class TestVisualizer {

	/**
	 * Test Line Chart
	 */
	@Test
	public void test_visualizer_01() {
		Fetcher fetcher = new Fetcher("CAN", 0, 2000, 2001);
		ArrayList<JsonArray> jsonarr = new ArrayList<JsonArray>();
		jsonarr.add(fetcher.fetchData("SP.POP.TOTL"));
		Visualization vis = new Visualization("Line Chart", jsonarr);
	}
	
	/**
	 * Test Bar Chart
	 */
	@Test
	public void test_visualizer_02() {
		Fetcher fetcher = new Fetcher("CAN", 0, 2000, 2001);
		ArrayList<JsonArray> jsonarr = new ArrayList<JsonArray>();
		jsonarr.add(fetcher.fetchData("SP.POP.TOTL"));
		Visualization vis = new Visualization("Bar Chart", jsonarr);
	}
	
	/**
	 * Test Report
	 */
	@Test
	public void test_visualizer_03() {
		Fetcher fetcher = new Fetcher("CAN", 0, 2000, 2001);
		ArrayList<JsonArray> jsonarr = new ArrayList<JsonArray>();
		jsonarr.add(fetcher.fetchData("SP.POP.TOTL"));
		Visualization vis = new Visualization("Report", jsonarr);
		
		
	}
	
	/**
	 * Test Pie Chart
	 */
	@Test
	public void test_visualizer_04() {
		Fetcher fetcher = new Fetcher("CAN", 0, 2000, 2001);
		ArrayList<JsonArray> jsonarr = new ArrayList<JsonArray>();
		jsonarr.add(fetcher.fetchData("SP.POP.TOTL"));
		Visualization vis = new Visualization("Pie Chart", jsonarr);
	}
	
}
