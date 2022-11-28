package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import adapters.IAdapter;
import adapters.UnitedNationsAdapter;

import analysers.AnalysisAgriVsForest;
import analysers.IAnalyser;
import client.UserSelection;

public class TestAnalayser {
	
	
	@Test 
	public void test_analyser_01() {
		
		UserSelection selection = new UserSelection();
		selection.setCountryCode("CAN");
		selection.setFromYear(2000);
		selection.setToYear(2001);
		
		IAdapter UN = new UnitedNationsAdapter();
		IAnalyser agrivsforest = new AnalysisAgriVsForest(UN);
		
		assertNotNull(agrivsforest.calculate(selection));
		
		
	}
	

}
