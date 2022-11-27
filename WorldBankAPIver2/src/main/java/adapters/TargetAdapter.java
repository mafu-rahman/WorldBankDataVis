package adapters;

import client.UserSelection;

public interface TargetAdapter {
	
	/**
	 * Fetch data based on user provided data selection and analysis type
	 * @param selection data composed of country code, start year and end year 
	 * @param analysisTypeCode type of analysis
	 * @return Object data object returned using the fetchData method
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode);
	

}
