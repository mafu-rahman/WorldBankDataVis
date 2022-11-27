package analysers;

import java.util.Vector;

public class Analyser {
	
	private Vector<IAnalyser> analysers;
	
	/**
	 * Constructor Method
	 */
	public Analyser() {
		this.analysers = new Vector<>();
	}
	
	/**
	 * Add an analyser
	 * @param a analyser type
	 */
	public void addAnalyser(IAnalyser a) {
		this.analysers.add(a);
	}
	
	/**
	 * Remove an analyser
	 * @param a analyser type
	 */
	public void removeAnalyser(IAnalyser a) {
		this.analysers.remove(a);
	}
	
	/**
	 * Get all analysers
	 * @return vector of analysers
	 */
	public Vector<IAnalyser> getAnalysers() {
		return this.analysers;
	}
}