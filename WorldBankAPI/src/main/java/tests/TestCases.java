package tests;
import login.*;
import dataFetchers.*;
import analyser.*;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class TestCases {

	
	// Test if system allows login when credentials are incorrect
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
	
	// Test if system allows login when credentials are correct
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
	
	// Test if system allows login when credentials are not provided
	@Test
	public void test_login_03() {
		loginUI loginui = new loginUI();
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
	
	// Test if system allows login when username has an empty character at the end
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
	
	@Test 
	public void test_analyser_01() {
		fail("Umimplemented");
		
	}
	
	@Test 
	public void test_analyser_02() {
		fail("Umimplemented");
		
	}
	
	@Test
	public void test_analyser_03() {
		fail("Umimplemented");
		
	}

	@Test
	public void test_analyser_04() {
		fail("Umimplemented");
		
	}

	/*
	 * Test Line Chart
	 */
	@Test
	public void test_visualizer_01() {
		fail("Umimplemented");
		
	}
	
	/*
	 * Test Bar Chart
	 */
	@Test
	public void test_visualizer_02() {
		fail("Umimplemented");
		
	}
	
	/*
	 * Test Report
	 */
	@Test
	public void test_visualizer_03() {
		fail("Umimplemented");
		
	}
	
	/*
	 * Test Pie Chart
	 */
	@Test
	public void test_visualizer_04() {
		fail("Umimplemented");
		
	}
	
	/*
	 * Test Fetching for CAN
	 */
	@Test
	public void test_fetcher_01() {
		fail("Umimplemented");
	}
	
	/*
	 * Test Fetching for USA
	 */
	@Test
	public void test_fetcher_02() {
		fail("Umimplemented");
	}
	
	
	/*
	 * Test Fetching for BRA
	 */
	@Test
	public void test_fetcher_03() {
		fail("Umimplemented");
	}
	
	/*
	 * Test Fetching for CN
	 */
	@Test
	public void test_fetcher_04() {
		fail("Umimplemented");
	}
	
}
