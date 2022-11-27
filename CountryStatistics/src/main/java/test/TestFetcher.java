package test;

import java.util.ArrayList;

import org.junit.Test;

import dataFetchers.Fetcher;

public class TestFetcher {

	
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
		Fetcher fetcher = new Fetcher("CN", 2000, 2008);
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
