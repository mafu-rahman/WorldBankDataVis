package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonArray;

import adapters.IAdapter;
import adapters.WorldBankAdapter;
import analysers.AnalysisAgriVsForest;
import analysers.IAnalyser;
import client.UserSelection;
import dataFetchers.Fetcher;
import dataFetchers.WorldBankFetcher;

public class TestFetcher {

	
	/**
	 * Test Fetching for USA
	 */
	@Test
	public void test_fetcher_02() {
		Fetcher fetcher = new WorldBankFetcher();
		UserSelection selection = new UserSelection();
		selection.setCountryCode("USA");
		selection.setFromYear(2001);
		selection.setToYear(2010);
	
		JsonArray retrievedJsonArray = (JsonArray) fetcher.fetchData(selection, "SP.POP.TOTL");
		JsonArray checkArray = null;
		
		assertEquals(retrievedJsonArray, checkArray);
	}
	
	/**
	 * Test Fetching for BRA 
	 */
	@Test
	public void test_fetcher_03() {
		Fetcher fetcher = new WorldBankFetcher();
		UserSelection selection = new UserSelection();
		selection.setCountryCode("BRA");
		selection.setFromYear(2000);
		selection.setToYear(2011);
		
		assertEquals("BRA", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2011, fetcher.getEndYear());
		Vector<String> countryList = DATAPARSER.getCountryList();
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
		Fetcher fetcher = new WorldBankFetcher();
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CN");
		selection.setFromYear(2000);
		selection.setToYear(2008);
		fetcher.setAnalysisType(8);
		assertEquals("CN", fetcher.getCountry());
		assertEquals(2000, fetcher.getStartYear());
		assertEquals(2008, fetcher.getEndYear());

		ArrayList<String> x = DATAPARSER.getAnalysisCodes(7);
		boolean condition = false;
		if(x.get(0).equals("PV.EST") && x.get(1).equals("NY.GDP.MKTP.KD.ZG")) {
			condition = true;
		}
		assertTrue(condition);
	}
}
