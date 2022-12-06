package adapters;

import analysers.AnalysisAgriVsForest;
import analysers.AnalysisCoalEnergyvsRenewableOutput;

public class AdapterFactory {
	
	public IAdapter createAdapter(String source) {
		if(source.equals("World Bank")) {
			return new WorldBankAdapter();
		}
		
		else if(source.equals("OpenCovid")) {
			return new OpenCovidAdapter();
		}
		
		return null;
	}

}
