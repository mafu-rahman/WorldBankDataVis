package analysers;

import java.util.Vector;

/**
 * @author mafu
 *
 * This class stores a list of Analyser objects
 * 
 */
public class Analyser {
	
	private Vector<IAnalyser> analysers;
	
	/**
	 * Constructor Method
	 */
	public Analyser() {
		this.analysers = new Vector<>();
	}
	
	/**
	 * Add an analyzer
	 * @param a : analyzer type
	 */
	public void addAnalyser(IAnalyser a) {
		this.analysers.add(a);
	}
	
	/**
	 * Remove an analyzer
	 * @param a : analyzer type
	 */
	public void removeAnalyser(IAnalyser a) {
		this.analysers.remove(a);
	}
	
	/**
	 * Get all analyzers
	 * @return returns vector of analyzers
	 */
	public Vector<IAnalyser> getAnalysers() {
		return this.analysers;
	}
}