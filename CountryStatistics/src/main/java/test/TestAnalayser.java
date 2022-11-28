package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adapters.IAdapter;
import adapters.UnitedNationsAdapter;
import analysers.Analyser;
import analysers.AnalysisAgriVsForest;
import analysers.IAnalyser;

public class TestAnalayser {
	
	
	@Test 
	public void test_analyser_01() {
		Analyser analyser = new Analyser();
		IAdapter UN = new UnitedNationsAdapter();
		IAnalyser agrivsforest = new AnalysisAgriVsForest(UN);
		analyser.addAnalyser(agrivsforest);
	}
	
	@Test 
	public void test_analyser_02() {
		Analyser analyser = new Analyser();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_analyser_03() {
		Analyser analyser = new Analyser();
		
	}

	@Test
	public void test_analyser_04() {
		Analyser analyser = new Analyser();
		
	}

}
