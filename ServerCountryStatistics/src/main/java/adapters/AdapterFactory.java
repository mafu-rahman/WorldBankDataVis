package adapters;

public class AdapterFactory {
	
	public IAdapter createAdapter(String source) {
		if(source.equals("WorldBank")) {
			return new WorldBankAdapter();
		}
		
		else if(source.equals("OpenCovid")) {
			return new OpenCovidAdapter();
		}
		
		return null;
	}
}