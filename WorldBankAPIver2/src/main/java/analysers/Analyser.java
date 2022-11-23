package analysers;

import java.util.HashMap;
import java.util.Vector;

public class Analyser {
	
	private Vector<IAnalyser> analysers;
	
	public Analyser() {
		this.analysers = new Vector<>();
	}
	
	public void addAnalyser(IAnalyser a) {
		this.analysers.add(a);
	}
	
	public void removeAnalyser(IAnalyser a) {
		this.analysers.remove(a);
	}
	
	public Vector<IAnalyser> getAnalysers() {
		return this.analysers;
	}
}
