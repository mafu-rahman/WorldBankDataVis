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
	
}
