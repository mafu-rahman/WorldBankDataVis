package dataFetchers;

import client.UserSelection;

public interface Fetcher {
	
	/**
	 * Method used to fetch data from API 
	 * @param analysisTypeCode code from which data is to be fetched, for example, "SP.POP.TOTL" 
	 * @return it returns an object of appropriate type depending on the implemented class, exampl, JsonArray
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode);	
}
