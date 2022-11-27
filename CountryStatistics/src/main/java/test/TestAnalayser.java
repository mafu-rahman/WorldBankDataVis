package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import analysers.Analyser;

public class TestAnalayser {
	
	
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

}
