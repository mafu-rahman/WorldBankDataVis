package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import analysers.Analyser;

import gui.LoginUI;

import viewers.Viewer;

public class TestLoginUI {

	
	/**
	 *  Test if system allows login when credentials are incorrect
	 */
	@Test
	public void test_login_01() {
		Viewer v = null;
		Analyser a  = null;
		LoginUI l = new LoginUI(v, a);
		
		l.setUsername("Atiq");
		l.setPassword("incorrectpassword");
		String path = "credentials.csv";
		String line = "";
		boolean condition = false;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(l.getUsername().equals(values[0]) && l.getPassword().equals(values[1])) {
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
		Viewer v = null;
		Analyser a  = null;
		LoginUI l = new LoginUI(v, a);
		
		l.setUsername("Atiq");
		l.setPassword("incorrectpassword");
		String path = "credentials.csv";
		String line = "";
		boolean condition = false;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(l.getUsername().equals(values[0]) && l.getPassword().equals(values[1])) {
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
		Viewer v = null;
		Analyser a  = null;
		LoginUI l = new LoginUI(v, a);
		
		
		assertEquals("Error: No username entered!", l.getUsername());
	}
	
	/**
	 *  Test if system allows login when username has an empty character at the end
	 */
	@Test 
	public void test_login_04() {
		Viewer v = null;
		Analyser a  = null;
		LoginUI l = new LoginUI(v, a);
		
		l.setUsername("Atiq");
		l.setPassword("incorrectpassword");
		String path = "credentials.csv";
		String line = "";
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader((path)));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				assertFalse(l.getUsername().equals(values[0]) && l.getPassword().equals(values[1]));
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
