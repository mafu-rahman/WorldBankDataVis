package dataFetchers;

import client.UserSelection;

public interface Fetcher {
	
	
	/**
	 * Method used to fetch data from API 
	 * @param analysisTypeCode code from which data is to be fetched, for example, "SP.POP.TOTL" 
	 * @return retrievedJsonArray JSONArray of data that has been fetched from the API 
	 */
	public Object fetchData(UserSelection selection, String analysisTypeCode);	
}
