package tests;
import login.*;
import dataFetchers.*;
import analyser.*;
import gui.DATAPARSER;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

public class TestCases {

	
	/**
	 *  Test if system allows login when credentials are incorrect
	 */
	@Test 
	public void test_login_01() {
		loginUI loginui = new loginUI();
		loginui.setUsername("mafurrrr");
		loginui.setPassword("wrongpassword");
		loginui.setUsername("wrong username");
		loginui.setPassword("wrong password");
		loginui.setUsername("wrong username");
		loginui.setPassword("wrong password");
		String path = "credentials.csv";
		String line = "";
		boolean condition = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(loginui.getUsername().equals(values[0]) && loginui.getPassword().equals(values[1])) {
					condition = true;
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		assertFalse(condition);

	}
	
	/**
	 *  Test if system allows login when credentials are correct
	 */
	@Test
	public void test_login_02() {
		loginUI loginui = new loginUI();
		loginui.setUsername("admin");
		loginui.setPassword("admin");
		String path = "credentials.csv";
		String line = "";
		boolean condition = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(loginui.getUsername().equals(values[0]) && loginui.getPassword().equals(values[1])) {
					condition = true;
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		assertTrue(condition);
	}
	
	/**
	 *  Test if system allows login when credentials are not provided
	 */
	@Test(expected = NullPointerException.class)
	public void test_login_03() {
		loginUI loginui = new loginUI();
		String path = "credentials.csv";
		String line = "";
		
		assertEquals("Error: No username entered!", loginui.getUsername());
	}
	
	/**
	 *  Test if system allows login when username has an empty character at the end
	 */
	@Test 
	public void test_login_04() {
		loginUI loginui = new loginUI();
		loginui.setUsername("mafu ");
		loginui.setPassword("password");
		String path = "credentials.csv";
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				assertFalse(loginui.getUsername().equals(values[0]) && loginui.getPassword().equals(values[1]));
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 *  Test analyser
	 */
	@Test 
	public void test_analyser_01() {
		Analyser analyser = new Analyser();
		double avg = analyser.calcMean(10, 2);
		assertEquals(5, avg, 0);
	}
	
	@Test 
	public void test_analyser_02() {
		Analyser analyser = new Analyser();
		double avg = analyser.calcMean(100, 2);
		assertEquals(50, avg, 0);
		assertEquals(50, analyser.getMean(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_analyser_03() {
		Analyser analyser = new Analyser();
		double avg = analyser.calcMean(0, 2);
	}

	@Test
	public void test_analyser_04() {
		Analyser analyser = new Analyser();
		double avg = analyser.calcMean(50, 2);
		assertEquals(25, avg, 0);
		assertEquals(25, analyser.getMean(), 0);
	}

	/**
	 * Test Line Chart
	 */
	@Test
	public void test_visualizer_01() {
		fail("Umimplemented");
		
	}
	
	/**
	 * Test Bar Chart
	 */
	@Test
	public void test_visualizer_02() {
		fail("Umimplemented");
		
	}
	
	/**
	 * Test Report
	 */
	@Test
	public void test_visualizer_03() {
		fail("Umimplemented");
		
	}
	
	/**
	 * Test Pie Chart
	 */
	@Test
	public void test_visualizer_04() {
		fail("Umimplemented");
		
	}
	
	/**
	 * Test Fetching for CAN and then check if an invalid country code was set
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_fetcher_01() {
		Fetcher fetcher = new Fetcher("CAN", 2000, 2001);
		assertEquals("CAN", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2001, fetcher.getEndYear());
		fetcher.setCountry("NOPE");
	}
	
	/**
	 * Test Fetching for USA and then check if a valid country code was set
	 */
	@Test
	public void test_fetcher_02() {
		Fetcher fetcher = new Fetcher("USA", 2000, 2011);
		assertEquals("USA", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2011, fetcher.getEndYear());
		fetcher.setCountry("BRA");
		assertEquals("BRA", fetcher.getCountry());
	}
	
	
	/**
	 * Test Fetching for BRA 
	 */
	@Test
	public void test_fetcher_03() {
		Fetcher fetcher = new Fetcher("BRA", 2000, 2011);
		fetcher.setAnalysisType(2);
		assertEquals("BRA", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2011, fetcher.getEndYear());
		DATAPARSER dp = new DATAPARSER();
		Vector<String> countryList = dp.getCountryList();
		boolean condition = false;
		for(String x : countryList) {
			if(x.equals(fetcher.getCountry())) {
				condition = true;
			}
		}
		assertTrue(condition);
	}
	
	/**
	 * Test Fetching for CN
	 */
	@Test
	public void test_fetcher_04() {
		Fetcher fetcher = new Fetcher("CN", 2000, 2008);
		fetcher.setAnalysisType(8);
		assertEquals("CN", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2008, fetcher.getEndYear());
		DATAPARSER dp = new DATAPARSER();
		ArrayList<String> x = dp.getAnalysisCodes(8);
		boolean condition = false;
		if(x.get(0).equals("PV.EST") && x.get(1).equals("NY.GDP.MKTP.KD.ZG")) {
			condition = true;
		}
		assertTrue(condition);
	}
	
}
